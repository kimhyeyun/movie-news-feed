package com.example.movienewsfeed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MovieNewsFeedApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieNewsFeedApplication.class, args);
    }

}
