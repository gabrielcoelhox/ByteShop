package gabrielcoelhox.com.github.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @NotBlank(message = "Descrição é obrigatória")
    private String description;

    @NotNull(message = "Preço é obrigatório")
    @Positive(message = "Preço deve ser positivo")
    private BigDecimal price;

    @NotBlank(message = "Categoria é obrigatória")
    private String category;

    @NotNull(message = "Quantidade em estoque é obrigatória")
    @PositiveOrZero(message = "Quantidade em estoque deve ser zero ou positivo")
    private Integer stockQuantity;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}