package org.example.test_run.manager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class ManagerConfiging {
    @Bean
    CommandLineRunner commandLineRunner(
            ManagersServices repository) {
        return args -> {
            Manager mariam = new Manager(
                    "BOOOOO",
                    "this.is.different@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 5)
            );
            Manager alex = new Manager(
                    "jodddd",
                    "boo@gmail.com",
                    LocalDate.of(2020, Month.JANUARY, 5)
            );
            repository.saveAll(
                    List.of(mariam,alex)
            );
        };
    }
}
