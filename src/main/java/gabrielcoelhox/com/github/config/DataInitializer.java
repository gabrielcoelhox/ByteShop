package gabrielcoelhox.com.github.config;

import gabrielcoelhox.com.github.model.*;
import gabrielcoelhox.com.github.repository.OrderItemRepository;
import gabrielcoelhox.com.github.repository.OrderRepository;
import gabrielcoelhox.com.github.repository.ProductRepository;
import gabrielcoelhox.com.github.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        if (userRepository.count() == 0) {
            initializeData();
        }
    }

    private void initializeData() {
        log.info("Initializing data...");

        // Cria o usuário administrador
        User admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .name("Administrator")
                .email("admin@byteshop.com")
                .role(UserRole.ADMIN)
                .build();
        userRepository.save(admin);
        log.info("Admin user created");

        // Create regular user
        User user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("user"))
                .name("Regular User")
                .email("user@byteshop.com")
                .role(UserRole.USER)
                .build();
        userRepository.save(user);
        log.info("Regular user created");

        // Create products
        Product monitor = new Product();
        monitor.setName("Monitor LG 27'");
        monitor.setDescription("Monitor LG 27' 4K UHD");
        monitor.setPrice(new BigDecimal("1999.99"));
        monitor.setCategory("Monitores");
        monitor.setStockQuantity(10);
        productRepository.save(monitor);

        Product mouse = new Product();
        mouse.setName("Mouse Logitech G502");
        mouse.setDescription("Mouse Gamer Logitech G502 HERO");
        mouse.setPrice(new BigDecimal("299.99"));
        mouse.setCategory("Periféricos");
        mouse.setStockQuantity(20);
        productRepository.save(mouse);

        Product keyboard = new Product();
        keyboard.setName("Teclado Mecânico Redragon");
        keyboard.setDescription("Teclado Mecânico Redragon Kumara RGB");
        keyboard.setPrice(new BigDecimal("399.99"));
        keyboard.setCategory("Periféricos");
        keyboard.setStockQuantity(15);
        productRepository.save(keyboard);

        Product headset = new Product();
        headset.setName("Headset HyperX Cloud II");
        headset.setDescription("Headset Gamer HyperX Cloud II 7.1");
        headset.setPrice(new BigDecimal("699.99"));
        headset.setCategory("Áudio");
        headset.setStockQuantity(8);
        productRepository.save(headset);

        Product ssd = new Product();
        ssd.setName("SSD Samsung 1TB");
        ssd.setDescription("SSD Samsung 970 EVO Plus NVMe M.2");
        ssd.setPrice(new BigDecimal("899.99"));
        ssd.setCategory("Armazenamento");
        ssd.setStockQuantity(12);
        productRepository.save(ssd);
        log.info("Products created");

        // Cria um pedido para o usuário regular
        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());
        order.setTotalAmount(BigDecimal.ZERO);
        orderRepository.save(order);

        // Adiciona o monitor ao pedido
        OrderItem monitorItem = new OrderItem();
        monitorItem.setOrder(order);
        monitorItem.setProduct(monitor);
        monitorItem.setQuantity(1);
        monitorItem.setPrice(monitor.getPrice());
        order.addItem(monitorItem);

        // Adiciona o mouse ao pedido
        OrderItem mouseItem = new OrderItem();
        mouseItem.setOrder(order);
        mouseItem.setProduct(mouse);
        mouseItem.setQuantity(1);
        mouseItem.setPrice(mouse.getPrice());
        order.addItem(mouseItem);

        orderRepository.save(order);
        log.info("Order created");

        log.info("Data initialization completed");
    }
}