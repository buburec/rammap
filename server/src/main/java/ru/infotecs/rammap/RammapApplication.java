package ru.infotecs.rammap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main class for running the Spring Boot application.
 */
@SpringBootApplication
@EnableScheduling
public class RammapApplication {

    /**
     * Entry point of the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(RammapApplication.class, args);
    }

}
