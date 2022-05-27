package logistic.api.controller;

import jakarta.validation.Valid;
import logistic.api.dto.client.ClientListDTO;
import logistic.api.dto.client.ClientSaveDTO;
import logistic.api.dto.client.ClientUpdateDTO;
import logistic.api.model.Client;
import logistic.api.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
public record ClientController(ClientService service) {


    @GetMapping
    public List<ClientListDTO> list(@RequestParam(value = "search", required = false, defaultValue = "") String name) {
        var clients = service.list(name);
        return clients.stream().map(ClientListDTO::fromEntity).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> get(@PathVariable UUID id) {
        return service.get(id).map(client -> ResponseEntity.ok(client)).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client save(@Valid @RequestBody ClientSaveDTO dto) {
        return service.save(dto.toEntity());
    }

    @PutMapping
    public ResponseEntity<ClientUpdateDTO> update(@Valid @RequestBody ClientUpdateDTO dto) {
        if(!service.existById(dto.getId())) return ResponseEntity.notFound().build();
        service.save(dto.toEntity());
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (!service.existById(id)) return ResponseEntity.notFound().build();
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
