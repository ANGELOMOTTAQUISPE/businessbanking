package businessbanking.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("account")
@Data
@NoArgsConstructor
public class Account {
    @Id
    private String idAccount;
    private String accountNumber;
    private String accountType;
    private Client client;
}
