package logistic.api.dto.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import logistic.api.model.Client;
import lombok.Data;

import java.util.UUID;

@Data
public class ClientListDTO {

    @NotNull
    private UUID id;

    @NotBlank
    @Size(max = 60)
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(max = 20)
    private String cellphone;

    public static ClientListDTO fromEntity(Client client) {
        ClientListDTO dto = new ClientListDTO();

        dto.id = client.getId();
        dto.name = client.getName();
        dto.cellphone = client.getCellphone();
        dto.email = client.getEmail();

        return dto;
    }
}
