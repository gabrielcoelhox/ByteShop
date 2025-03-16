package gabrielcoelhox.com.github.repository;

import gabrielcoelhox.com.github.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    // Consulta para encontrar os 5 usuários que gastaram mais dinheiro
    // Seleciona os usuários, conta o número de pedidos e soma o valor total gasto por cada usuário
    // Filtra apenas os pedidos pagos (status = 'PAID')
    // Agrupa os resultados pelo ID do usuário
    // Ordena os resultados pelo valor total gasto em ordem decrescente
    // Limita o resultado a 5 usuários
    // nativeQuery = true: indica que a consulta é uma consulta SQL nativa
    // retorna uma lista de arrays, onde cada array contém um objeto User e o número de pedidos e o valor total gasto por cada usuário
    @Query(value = "SELECT u.id as id, u.name as name, u.email as email, COUNT(o.id) as order_count, SUM(o.total_amount) as total_spent " +
            "FROM users u " +
            "JOIN orders o ON u.id = o.user_id " +
            "WHERE o.status = 'PAID' " +
            "GROUP BY u.id, u.name, u.email " +
            "ORDER BY total_spent DESC " +
            "LIMIT 5", nativeQuery = true)
    List<Object[]> findTop5UsersByTotalSpent();

    // Consulta para encontrar o valor médio de pedidos por usuário
    // Seleciona os usuários, calcula o valor médio de pedidos pagos por cada usuário
    // Filtra apenas os pedidos pagos (status = 'PAID')
    // Agrupa os resultados pelo ID do usuário
    // nativeQuery = true: indica que a consulta é uma consulta SQL nativa
    // retorna uma lista de arrays, onde cada array contém um objeto User e o valor médio de pedidos pagos por cada usuário
    @Query(value = "SELECT u.id as id, u.name as name, u.email as email, AVG(o.total_amount) as average_order_value " +
            "FROM users u " +
            "JOIN orders o ON u.id = o.user_id " +
            "WHERE o.status = 'PAID' " +
            "GROUP BY u.id, u.name, u.email", nativeQuery = true)
    List<Object[]> findAverageOrderValuePerUser();
}