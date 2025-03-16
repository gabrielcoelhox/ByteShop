package gabrielcoelhox.com.github.dto.analytics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AverageOrderValueDTO {
    private UUID userId;
    private String name;
    private String email;
    private BigDecimal averageOrderValue;
}