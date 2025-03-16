package gabrielcoelhox.com.github.repository;

import gabrielcoelhox.com.github.model.Order;
import gabrielcoelhox.com.github.model.OrderItem;
import gabrielcoelhox.com.github.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {

    List<OrderItem> findByOrder(Order order);

    List<OrderItem> findByProduct(Product product);
}