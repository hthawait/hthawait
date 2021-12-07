package com.hot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

@SpringBootApplication
public class StartBookApplication {

    // start everything
    public static void main(String[] args) {
        // https://hot.com/spring-boot/spring-rest-spring-security-example/
        SpringApplication.run(StartBookApplication.class, args);
    }

    /*
    @Profile("demo")
    @Bean
    CommandLineRunner initDatabase(BookRepository repository) {
        return args -> {
            repository.save(new Book("Pitch any thing", "Santideva", new BigDecimal("15.41")));
            repository.save(new Book("Think and grow rich", "Marie Kondo", new BigDecimal("9.69")));
            repository.save(new Book("Rich dad poor data", "Martin Fowler", new BigDecimal("47.99")));
        };
    }
    */
}