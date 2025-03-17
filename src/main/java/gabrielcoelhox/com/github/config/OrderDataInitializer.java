package gabrielcoelhox.com.github.config;

import gabrielcoelhox.com.github.model.OrderItem;
import gabrielcoelhox.com.github.model.OrderStatus;
import gabrielcoelhox.com.github.model.Product;
import gabrielcoelhox.com.github.model.User;
import gabrielcoelhox.com.github.repository.OrderItemRepository;
import gabrielcoelhox.com.github.repository.OrderRepository;
import gabrielcoelhox.com.github.repository.ProductRepository;
import gabrielcoelhox.com.github.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(2) // Executa após o DataInitializer (que tem @Order(1))
public class OrderDataInitializer implements CommandLineRunner {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        // Verifica se já existem pedidos no banco
        if (orderRepository.count() > 0) {
            log.info("Pedidos já existem no banco. Pulando inicialização.");
            return;
        }

        // Verifica se existem usuários e produtos no banco
        if (userRepository.count() == 0 || productRepository.count() == 0) {
            log.info("Usuários ou produtos não encontrados. Pulando inicialização de pedidos.");
            return;
        }

        log.info("Inicializando pedidos...");

        // Busca usuários para associar aos pedidos
        User admin = userRepository.findByUsername("admin").orElseThrow();
        User user = userRepository.findByUsername("user").orElseThrow();
        
        // Verificar se existem usuários adicionais ou criar novos
        User arrascaeta = userRepository.findByUsername("arrascaeta")
            .orElseGet(() -> createUser("arrascaeta", "Arrascaeta", "arrascaeta@flamengo.com"));
        
        User pedro = userRepository.findByUsername("pedro")
            .orElseGet(() -> createUser("pedro", "Pedro", "pedro@flamengo.com"));

        // Busca alguns produtos para adicionar aos pedidos
        List<Product> availableProducts = productRepository.findAll();
        
        if (availableProducts.size() < 8) {
            log.warn("Menos de 8 produtos disponíveis. Alguns pedidos podem não ser criados.");
        }

        // Histórico de pedidos para admin (pedidos PAGOS)
        createCompletedOrder(admin, List.of(
                new ProductQuantity(availableProducts.get(0 % availableProducts.size()), 2),
                new ProductQuantity(availableProducts.get(1 % availableProducts.size()), 1)
        ));
        
        createCompletedOrder(admin, List.of(
                new ProductQuantity(availableProducts.get(2 % availableProducts.size()), 1),
                new ProductQuantity(availableProducts.get(3 % availableProducts.size()), 3)
        ));

        // Histórico de pedidos para user regular
        createCompletedOrder(user, List.of(
                new ProductQuantity(availableProducts.get(2 % availableProducts.size()), 3),
                new ProductQuantity(availableProducts.get(3 % availableProducts.size()), 1)
        ));

        // Histórico de pedidos para arrascaeta
        createCompletedOrder(arrascaeta, List.of(
                new ProductQuantity(availableProducts.get(4 % availableProducts.size()), 1),
                new ProductQuantity(availableProducts.get(5 % availableProducts.size()), 2)
        ));
        
        createCompletedOrder(arrascaeta, List.of(
                new ProductQuantity(availableProducts.get(0 % availableProducts.size()), 1)
        ));

        // Histórico de pedidos para pedro
        createCompletedOrder(pedro, List.of(
                new ProductQuantity(availableProducts.get(1 % availableProducts.size()), 2),
                new ProductQuantity(availableProducts.get(3 % availableProducts.size()), 1)
        ));

        // Pedidos pendentes para vários usuários
        createPendingOrder(admin, List.of(
                new ProductQuantity(availableProducts.get(4 % availableProducts.size()), 1),
                new ProductQuantity(availableProducts.get(5 % availableProducts.size()), 2)
        ));
        
        createPendingOrder(user, List.of(
                new ProductQuantity(availableProducts.get(0 % availableProducts.size()), 1),
                new ProductQuantity(availableProducts.get(1 % availableProducts.size()), 1)
        ));
        
        createPendingOrder(pedro, List.of(
                new ProductQuantity(availableProducts.get(2 % availableProducts.size()), 2)
        ));

        log.info("Pedidos inicializados com sucesso - {} pedidos criados", orderRepository.count());
    }

    private gabrielcoelhox.com.github.model.Order createCompletedOrder(User user, List<ProductQuantity> productQuantities) {
        LocalDateTime now = LocalDateTime.now();
        gabrielcoelhox.com.github.model.Order order = new gabrielcoelhox.com.github.model.Order();
        order.setUser(user);
        order.setCreatedAt(now.minusDays((long) (Math.random() * 30)));
        order.setStatus(OrderStatus.PAID);
        order.setPaidAt(now);
        
        BigDecimal total = BigDecimal.ZERO;
        for (ProductQuantity pq : productQuantities) {
            total = total.add(pq.product.getPrice().multiply(BigDecimal.valueOf(pq.quantity)));
        }
        order.setTotalAmount(total);
        
        gabrielcoelhox.com.github.model.Order savedOrder = orderRepository.save(order);
        
        List<OrderItem> orderItems = new ArrayList<>();
        for (ProductQuantity pq : productQuantities) {
            OrderItem item = new OrderItem();
            item.setOrder(savedOrder);
            item.setProduct(pq.product);
            item.setQuantity(pq.quantity);
            item.setPrice(pq.product.getPrice());
            orderItems.add(item);
        }
        
        orderItemRepository.saveAll(orderItems);
        
        return savedOrder;
    }

    private gabrielcoelhox.com.github.model.Order createPendingOrder(User user, List<ProductQuantity> productQuantities) {
        LocalDateTime now = LocalDateTime.now();
        gabrielcoelhox.com.github.model.Order order = new gabrielcoelhox.com.github.model.Order();
        order.setUser(user);
        order.setCreatedAt(now);
        order.setStatus(OrderStatus.PENDING);
        
        BigDecimal total = BigDecimal.ZERO;
        for (ProductQuantity pq : productQuantities) {
            total = total.add(pq.product.getPrice().multiply(BigDecimal.valueOf(pq.quantity)));
        }
        order.setTotalAmount(total);
        
        gabrielcoelhox.com.github.model.Order savedOrder = orderRepository.save(order);
        
        List<OrderItem> orderItems = new ArrayList<>();
        for (ProductQuantity pq : productQuantities) {
            OrderItem item = new OrderItem();
            item.setOrder(savedOrder);
            item.setProduct(pq.product);
            item.setQuantity(pq.quantity);
            item.setPrice(pq.product.getPrice());
            orderItems.add(item);
        }
        
        orderItemRepository.saveAll(orderItems);
        
        return savedOrder;
    }

    // Método auxiliar para criar usuários adicionais se não existirem
    private User createUser(String username, String name, String email) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword("$2a$10$5f.FqRsYm10ljH3iWbSSM.vMt8xzfRZU32JsFSm7Hw/8LVCHzJzv."); // "123456" codificada
        newUser.setRole(gabrielcoelhox.com.github.model.UserRole.USER);
        return userRepository.save(newUser);
    }

    // Classe auxiliar para gerenciar pares de produto e quantidade
    private static class ProductQuantity {
        private final Product product;
        private final int quantity;

        public ProductQuantity(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }
    }
} 