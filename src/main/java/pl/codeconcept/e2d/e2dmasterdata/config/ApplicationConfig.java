package pl.codeconcept.e2d.e2dmasterdata.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScans({
        @ComponentScan("pl.codeconcept.e2d.e2dmasterdata.service"),
        @ComponentScan("pl.codeconcept.e2d.e2dmasterdata.database"),
        @ComponentScan("pl.codeconcept.e2d.e2dmasterdata.api")}
)
public class ApplicationConfig {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}