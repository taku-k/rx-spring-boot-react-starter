package server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import server.services.ITodoService;
import server.services.TodoService;

@Configuration
public class AppConfig {
    @Bean
    public ITodoService todoService() {
        return new TodoService();
    }
}
