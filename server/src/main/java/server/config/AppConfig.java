package server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import server.gateways.GitHubGateway;
import server.services.TodoService;
import server.services.TodoServiceImpl;

@Configuration
public class AppConfig {
    @Bean
    public TodoService todoService() {
        return new TodoServiceImpl();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public GitHubGateway gitHubGateway() {
        return new GitHubGateway(restTemplate());
    }
}
