package gabrielcoelhox.com.github.config;

import gabrielcoelhox.com.github.model.Product;
import gabrielcoelhox.com.github.model.User;
import gabrielcoelhox.com.github.model.UserRole;
import gabrielcoelhox.com.github.repository.ProductRepository;
import gabrielcoelhox.com.github.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(1)
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public void run(String... args) {
        log.info("Iniciando carga de dados...");

        // Verificar se já existem dados
        if (userRepository.count() > 0 || productRepository.count() > 0) {
            log.info("Dados já existem no banco. Pulando inicialização.");
            return;
        }

        log.info("Criando usuários...");
        createUsers();

        log.info("Criando produtos...");
        createProducts();

        log.info("Carga de dados concluída com sucesso!");
    }

    private void createUsers() {
        User admin = new User();
        admin.setId(UUID.fromString("8d43e1c5-8edc-4f00-a028-47cfb126621a"));
        admin.setName("Admin User");
        admin.setUsername("admin");
        admin.setEmail("admin@byteshop.com");
        admin.setPassword("$2a$10$yYQaJrHzjOgD5wWCyelp0ev9QZel4FYwlIYWupD.eIGGVaLPHSaIi"); // 123456
        admin.setRole(UserRole.ADMIN);
        userRepository.save(admin);

        User user = new User();
        user.setId(UUID.fromString("7ca7c782-8710-42c0-ad81-4ba50f74dd96"));
        user.setName("Normal User");
        user.setUsername("user");
        user.setEmail("user@byteshop.com");
        user.setPassword("$2a$10$yYQaJrHzjOgD5wWCyelp0ev9QZel4FYwlIYWupD.eIGGVaLPHSaIi"); // 123456
        user.setRole(UserRole.USER);
        userRepository.save(user);

        User johndoe = new User();
        johndoe.setId(UUID.fromString("9d5a6082-6902-4a3f-9682-ddb7a1cee7c7"));
        johndoe.setName("Arrascaeta");
        johndoe.setUsername("johndoe");
        johndoe.setEmail("john@example.com");
        johndoe.setPassword("$2a$10$yYQaJrHzjOgD5wWCyelp0ev9QZel4FYwlIYWupD.eIGGVaLPHSaIi"); // 123456
        johndoe.setRole(UserRole.USER);
        userRepository.save(johndoe);

        User janesmith = new User();
        janesmith.setId(UUID.fromString("c77f3d05-7c95-4b2c-88b7-3b8e7f3b12c8"));
        janesmith.setName("Bruno Henrique");
        janesmith.setUsername("janesmith");
        janesmith.setEmail("jane@example.com");
        janesmith.setPassword("$2a$10$yYQaJrHzjOgD5wWCyelp0ev9QZel4FYwlIYWupD.eIGGVaLPHSaIi"); // 123456
        janesmith.setRole(UserRole.USER);
        userRepository.save(janesmith);

        User manager = new User();
        manager.setId(UUID.fromString("d51ec86b-6c9d-4e94-a5c7-842f13205a7d"));
        manager.setName("Neymar");
        manager.setUsername("manager");
        manager.setEmail("manager@byteshop.com");
        manager.setPassword("$2a$10$yYQaJrHzjOgD5wWCyelp0ev9QZel4FYwlIYWupD.eIGGVaLPHSaIi"); // 123456
        manager.setRole(UserRole.ADMIN);
        userRepository.save(manager);
    }

    private void createProducts() {
        addProduct(UUID.fromString("9878a9c9-fc69-4a65-8d9e-2bcd8a437e52"),
                "Smartphone XYZ",
                "Smartphone de ultima geracao com camera de alta resolucao",
                new BigDecimal("1999.90"),
                "Eletronicos",
                50);

        addProduct(UUID.fromString("3c976294-fc14-4a26-aec8-3f41e1f3cbce"),
                "Notebook Pro",
                "Notebook para uso profissional com processador de alta performance",
                new BigDecimal("4599.90"),
                "Eletronicos",
                25);

        addProduct(UUID.fromString("9a8e3f5d-1c2b-4a0e-b9c7-8d6f5a4e3c2b"),
                "Mouse Gamer RGB",
                "Mouse com iluminacao RGB e alta precisao para jogos",
                new BigDecimal("159.90"),
                "Periféricos",
                100);

        addProduct(UUID.fromString("7b6e9d8c-5a4e-3c2b-1a0f-9e8d7c6b5a4e"),
                "Teclado Mecanico",
                "Teclado mecanico com switches blue",
                new BigDecimal("249.90"),
                "Periféricos",
                75);

        addProduct(UUID.fromString("5e4d3c2b-1a0f-9e8d-7c6b-5a4e3c2b1a0f"),
                "Monitor 27 polegadas",
                "Monitor Full HD com taxa de atualizacao de 144Hz",
                new BigDecimal("1299.90"),
                "Monitores",
                30);

        addProduct(UUID.fromString("1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d"),
                "SSD 500GB",
                "Disco de estado solido para armazenamento rapido",
                new BigDecimal("399.90"),
                "Armazenamento",
                80);

        addProduct(UUID.fromString("f1e2d3c4-b5a6-7c8d-9e0f-1a2b3c4d5e6f"),
                "Headset Gamer",
                "Headset com microfone e cancelamento de ruido",
                new BigDecimal("199.90"),
                "Audio",
                60);

        addProduct(UUID.fromString("a1b2c3d4-e5f6-a7b8-c9d0-e1f2a3b4c5d6"),
                "Webcam HD",
                "Webcam com resolucao Full HD e microfone integrado",
                new BigDecimal("149.90"),
                "Periféricos",
                40);

        addProduct(UUID.fromString("b5c6d7e8-f9a0-b1c2-d3e4-f5a6b7c8d9e0"),
                "Roteador Wi-Fi",
                "Roteador dual band com alta velocidade",
                new BigDecimal("249.90"),
                "Redes",
                35);

        addProduct(UUID.fromString("c9d0e1f2-a3b4-c5d6-e7f8-a9b0c1d2e3f4"),
                "Cadeira Gamer",
                "Cadeira ergonomica para gamers profissionais",
                new BigDecimal("899.90"),
                "Moveis",
                20);
    }

    private void addProduct(UUID id, String name, String description, BigDecimal price, String category, int stockQuantity) {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category);
        product.setStockQuantity(stockQuantity);

        // Configurar timestamps manualmente
        LocalDateTime now = LocalDateTime.now();
        product.setCreatedAt(now);
        product.setUpdatedAt(now);

        productRepository.save(product);
    }
}