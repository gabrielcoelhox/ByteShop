package gabrielcoelhox.com.github.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Este componente carrega dados programaticamente quando a aplicação inicia.
 * Geralmente é usado para inserir dados que requerem lógica mais complexa
 * que não pode ser facilmente expressada em SQL.
 * 
 * Marcado com @Profile("dev") para executar apenas em ambiente de desenvolvimento.
 */
@Component
@Profile("dev")
public class DataLoader implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);
    
    @Override
    public void run(String... args) throws Exception {
        logger.info("DataLoader inicializado - pronto para inserir dados adicionais se necessário");
        
        // Aqui você pode adicionar código para inserir dados adicionais
        // que requerem lógica Java mais complexa.
        
        // Exemplo:
        // userRepository.findByUsername("admin").ifPresent(admin -> {
        //     // Realizar operações específicas para o usuário admin
        // });
        
        logger.info("DataLoader concluído");
    }
} 