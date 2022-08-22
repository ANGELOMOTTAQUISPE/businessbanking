package businessbanking.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import reactor.util.annotation.NonNull;

@Document("client")
@Data
@NoArgsConstructor
public class Client {
    @Id
    private String idClient;
    private String clientType;
    private String name;
    private String documentNumber;
    private String documentType;
}
