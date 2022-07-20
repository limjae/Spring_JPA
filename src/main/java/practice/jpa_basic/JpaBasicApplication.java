package practice.jpa_basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import practice.jpa_basic.entity.EntityRule;
import practice.jpa_basic.repository.EntityRuleRepository;

@SpringBootApplication
public class JpaBasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaBasicApplication.class, args);
    }

}
