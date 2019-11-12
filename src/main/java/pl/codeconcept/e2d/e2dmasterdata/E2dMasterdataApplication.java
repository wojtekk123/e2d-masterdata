package pl.codeconcept.e2d.e2dmasterdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("pl.codeconcept.e2d.e2dmasterdata.config")
public class E2dMasterdataApplication {

    public static void main(String[] args) {
        SpringApplication.run(E2dMasterdataApplication.class, args);
    }

}
