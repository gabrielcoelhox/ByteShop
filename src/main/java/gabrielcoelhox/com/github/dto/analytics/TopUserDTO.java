package gabrielcoelhox.com.github.dto.analytics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopUserDTO {
    private Long id;
    private String name;
    private String email;
    private Long orderCount;
    private BigDecimal totalSpent;
}