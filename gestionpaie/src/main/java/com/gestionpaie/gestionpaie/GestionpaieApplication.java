package com.gestionpaie.gestionpaie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.gestionpaie.gestionpaie.repository")
@SpringBootApplication
public class GestionpaieApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionpaieApplication.class, args);
    }

}
