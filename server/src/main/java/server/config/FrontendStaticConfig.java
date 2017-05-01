package server.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.*;
import server.utils.FrontendStaticResourceResolver;

@Configuration
@Profile("production")
public class FrontendStaticConfig extends WebMvcConfigurerAdapter {
    private static Logger LOG = LoggerFactory.getLogger(FrontendStaticConfig.class);

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        LOG.info("test");
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .resourceChain(false)
                .addResolver(new FrontendStaticResourceResolver());
    }
}
