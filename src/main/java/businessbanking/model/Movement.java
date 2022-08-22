package businessbanking.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("movement")
@Data
@NoArgsConstructor
public class Movement {
    @Id
    private String idMovement;
    private Double balance;
    private Double movement;
    private Account account;
    private Credit credit;
}
