package businessbanking.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Builder;

@Document("client")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    private String idClient;
    private String clientType;
    private String name;
    private String documentNumber;
    private String documentType;
}
