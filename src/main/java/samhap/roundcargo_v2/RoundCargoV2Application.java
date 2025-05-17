package samhap.roundcargo_v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class RoundCargoV2Application {

    public static void main(String[] args) {
        SpringApplication.run(RoundCargoV2Application.class, args);
    }

}
