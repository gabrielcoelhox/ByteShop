package gabrielcoelhox.com.github.repository;

import gabrielcoelhox.com.github.model.Order;
import gabrielcoelhox.com.github.model.OrderStatus;
import gabrielcoelhox.com.github.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByUser(User user);

    List<Order> findByUserAndStatus(User user, OrderStatus status);

    // Consulta para calcular o total de receita para um período específico
    // Seleciona o total de receita de pedidos pagos (status = 'PAID')
    // Filtra pelo período entre startDate e endDate
    // nativeQuery = true: indica que a consulta é uma consulta SQL nativa
    // retorna um BigDecimal, que é o total de receita para o período
    @Query(value = "SELECT SUM(o.total_amount) FROM orders o WHERE o.status = 'PAID' AND o.paid_at BETWEEN :startDate AND :endDate", nativeQuery = true)
    BigDecimal getTotalRevenueForPeriod(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}