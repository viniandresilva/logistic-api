package logistic.api.dto.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import logistic.api.model.Client;
import lombok.Data;

@Data
public class ClientSaveDTO {

    @NotBlank
    @Size(max = 60)
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(max = 20)
    private String cellphone;

    public Client toEntity() {
        Client client = new Client();

        client.setName(this.name);
        client.setEmail(this.email);
        client.setCellphone(this.cellphone);

        return client;
    }
}
