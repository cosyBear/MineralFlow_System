package be.kdg.prog6.watersideboundedcontext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "be.kdg.prog6.watersideboundedcontext")
public class WaterSideBoundedContextApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaterSideBoundedContextApplication.class, args);
    }

}
