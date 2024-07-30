package ru.infotecs.rammap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RammapApplication {

    public static void main(String[] args) {
        SpringApplication.run(RammapApplication.class, args);
    }

}
