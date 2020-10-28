package co.com.bancolombia.contracttesting.consumer.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    private String id;
    @NotEmpty
    private int clientId;
    @NotNull
    private String nameClient;
    @NotNull
    private String birthdate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date requestDate;
}
