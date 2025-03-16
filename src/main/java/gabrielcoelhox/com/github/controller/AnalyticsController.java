package gabrielcoelhox.com.github.controller;

import gabrielcoelhox.com.github.dto.analytics.AverageOrderValueDTO;
import gabrielcoelhox.com.github.dto.analytics.MonthlyRevenueDTO;
import gabrielcoelhox.com.github.dto.analytics.TopUserDTO;
import gabrielcoelhox.com.github.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("/top-users")
    public ResponseEntity<List<TopUserDTO>> getTop5UsersByTotalSpent() {
        return ResponseEntity.ok(analyticsService.getTop5UsersByTotalSpent());
    }

    @GetMapping("/average-order-value")
    public ResponseEntity<List<AverageOrderValueDTO>> getAverageOrderValuePerUser() {
        return ResponseEntity.ok(analyticsService.getAverageOrderValuePerUser());
    }

    @GetMapping("/monthly-revenue")
    public ResponseEntity<MonthlyRevenueDTO> getTotalRevenueForMonth(
            @RequestParam int year,
            @RequestParam int month) {
        return ResponseEntity.ok(analyticsService.getTotalRevenueForMonth(year, month));
    }
}