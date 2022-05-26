package logistic.api.services;

import jakarta.transaction.Transactional;
import logistic.api.model.Client;
import logistic.api.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClientService {
    ClientRepository repository;

    @Transactional
    public Client save(Client client) {
        return repository.saveAndFlush(client);
    }

    public List<Client> list(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public Optional<Client> get(UUID id) {
        return repository.findById(id);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public boolean existById(UUID id) {
        return repository.existsById(id);
    }
}
