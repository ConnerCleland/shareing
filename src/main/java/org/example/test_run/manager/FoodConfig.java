package org.example.test_run.manager;

import org.example.test_run.manager.Food;
import org.example.test_run.manager.FoodRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class FoodConfig {

    @Bean
    CommandLineRunner foodCommandLineRunner(FoodRepository repository) {
        return args -> {
            Food food1 = new Food("Pizza");
            Food food2 = new Food("Burger");
            Food food3 = new Food("Sushi");

            repository.saveAll(List.of(food1, food2, food3));
        };
    }
}
