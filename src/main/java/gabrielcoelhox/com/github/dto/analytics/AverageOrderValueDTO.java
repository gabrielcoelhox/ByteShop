package gabrielcoelhox.com.github.dto.analytics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AverageOrderValueDTO {
    private Long userId;
    private String name;
    private String email;
    private BigDecimal averageOrderValue;
}