package co.com.bancolombia.contracttesting.provider.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("clients")
public class Client {

    @Id
    private String id;
    @NotEmpty
    private int clientId;
    @NotNull
    private String nameClient;
    @NotNull
    private String birthdate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date requestDate;

    public Client(int clientId, String nameClient, String birthdate) {
        this.clientId = clientId;
        this.nameClient = nameClient;
        this.birthdate = birthdate;
    }
}
