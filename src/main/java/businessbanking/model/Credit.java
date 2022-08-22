package businessbanking.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("credit")
@Data
@NoArgsConstructor
public class Credit {
    @Id
    private String idCredit;
    private String creditCardNumber;
    private Double creditLine;
    private Client client;
}
