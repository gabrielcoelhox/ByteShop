package gabrielcoelhox.com.github.service;

import gabrielcoelhox.com.github.dto.analytics.AverageOrderValueDTO;
import gabrielcoelhox.com.github.dto.analytics.MonthlyRevenueDTO;
import gabrielcoelhox.com.github.dto.analytics.TopUserDTO;
import gabrielcoelhox.com.github.repository.OrderRepository;
import gabrielcoelhox.com.github.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    // Retorna os 5 usuários que mais gastaram
    // Retorna o nome, email, quantidade de pedidos e valor total gasto
    // Se não houver usuários, retorna uma lista vazia
    // Se houver menos de 5 usuários, retorna a quantidade de usuários existentes
    public List<TopUserDTO> getTop5UsersByTotalSpent() {
        List<Object[]> results = userRepository.findTop5UsersByTotalSpent();
        List<TopUserDTO> topUsers = new ArrayList<>();

        for (Object[] result : results) {
            UUID userId = (UUID) result[0];
            String name = (String) result[1];
            String email = (String) result[2];
            Long orderCount = (Long) result[3];
            BigDecimal totalSpent = (BigDecimal) result[4];

            topUsers.add(new TopUserDTO(userId, name, email, orderCount, totalSpent));
        }
        return topUsers;
    }

    // Retorna o valor médio dos pedidos por usuário
    // Retorna o nome, email e valor médio dos pedidos
    // Se não houver usuários, retorna uma lista vazia
    public List<AverageOrderValueDTO> getAverageOrderValuePerUser() {
        List<Object[]> results = userRepository.findAverageOrderValuePerUser();
        List<AverageOrderValueDTO> averageValues = new ArrayList<>();

        for (Object[] result : results) {
            UUID userId = (UUID) result[0];
            String name = (String) result[1];
            String email = (String) result[2];
            BigDecimal averageOrderValue = (BigDecimal) result[3];

            averageValues.add(new AverageOrderValueDTO(userId, name, email, averageOrderValue));
        }
        return averageValues;
    }

    // Retorna o valor total de vendas para um determinado mês
    // Retorna o ano, mês e valor total de vendas
    // Se não houver vendas, retorna um valor zero
    public MonthlyRevenueDTO getTotalRevenueForMonth(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDateTime startDate = yearMonth.atDay(1).atStartOfDay();
        LocalDateTime endDate = yearMonth.atEndOfMonth().atTime(23, 59, 59);

        BigDecimal totalRevenue = orderRepository.getTotalRevenueForPeriod(startDate, endDate);

        if (totalRevenue == null) {
            totalRevenue = BigDecimal.ZERO;
        }
        return new MonthlyRevenueDTO(year, month, totalRevenue);
    }
}