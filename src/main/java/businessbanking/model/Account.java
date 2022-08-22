package businessbanking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    private String idAccount;
    private String accountNumber;
    private String accountType;
    private Client client;
    private List<String> titulares;
    private List<String> fimasautorizadas;
}
