package com.gsilverio.simpleapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Locale;

@SpringBootApplication
@EnableJpaAuditing
public class SimpleApiApplication {

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        SpringApplication.run(SimpleApiApplication.class, args);
    }
}
