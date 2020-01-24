package pl.codeconcept.e2d.e2dmasterdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("pl.codeconcept.e2d.e2dmasterdata.config")
@EnableDiscoveryClient
public class E2dMasterdataApplication {

    public static void main(String[] args) {
        SpringApplication.run(E2dMasterdataApplication.class, args);
    }

}
