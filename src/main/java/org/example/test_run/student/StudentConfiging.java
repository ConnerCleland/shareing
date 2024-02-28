package org.example.test_run.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfiging {
    @Bean
    CommandLineRunner commandLineRunner(
            StudentsServices repository) {
        return args -> {
            Student mariam = new Student(
                    "BOOOOO",
                    "this.is.different@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 5)
            );
            Student alex = new Student(
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
