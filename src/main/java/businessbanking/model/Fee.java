package businessbanking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document("fee")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fee {
    @Id
    private String idFee;
    private Double maintenanceCommission;
    private Integer monthlyMovement;
    private LocalDateTime date;
}
