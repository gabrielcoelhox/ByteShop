package gabrielcoelhox.com.github.controller;

import gabrielcoelhox.com.github.dto.analytics.AverageOrderValueDTO;
import gabrielcoelhox.com.github.dto.analytics.MonthlyRevenueDTO;
import gabrielcoelhox.com.github.dto.analytics.TopUserDTO;
import gabrielcoelhox.com.github.service.AnalyticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Consultas Otimizadas", description = "Endpoint para análise de dados e relatórios")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("/top-users")
    @Operation(
        summary = "Top 5 usuários por valor gasto",
        description = "Retorna os 5 usuários que mais gastaram na plataforma, ordenados pelo valor total"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Consulta realizada com sucesso",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = TopUserDTO.class))
    )
    public ResponseEntity<List<TopUserDTO>> getTop5UsersByTotalSpent() {
        return ResponseEntity.ok(analyticsService.getTop5UsersByTotalSpent());
    }

    @GetMapping("/average-order-value")
    @Operation(
        summary = "Valor médio de pedidos por usuário",
        description = "Retorna o valor médio de pedidos para cada usuário da plataforma"
    )
    @ApiResponse(
        responseCode = "200", 
        description = "Consulta realizada com sucesso",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = AverageOrderValueDTO.class))
    )
    public ResponseEntity<List<AverageOrderValueDTO>> getAverageOrderValuePerUser() {
        return ResponseEntity.ok(analyticsService.getAverageOrderValuePerUser());
    }

    @GetMapping("/monthly-revenue")
    @Operation(
        summary = "Faturamento mensal",
        description = "Retorna o faturamento total para um determinado mês e ano"
    )
    @ApiResponse(
        responseCode = "200", 
        description = "Consulta realizada com sucesso",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = MonthlyRevenueDTO.class))
    )
    public ResponseEntity<MonthlyRevenueDTO> getTotalRevenueForMonth(
            @Parameter(description = "Ano de referência", example = "2023") @RequestParam int year,
            @Parameter(description = "Mês de referência (1-12)", example = "3") @RequestParam int month) {
        return ResponseEntity.ok(analyticsService.getTotalRevenueForMonth(year, month));
    }
}