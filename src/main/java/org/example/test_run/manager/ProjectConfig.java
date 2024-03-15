package org.example.test_run.manager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Random;

@Configuration
public class ProjectConfig {

    @Bean
    CommandLineRunner projectCommandLineRunner(ProjectRepository projectRepository, FoodRepository foodRepository) {
        return args -> {
            // Fetch all foods from the repository
            List<Food> foods = foodRepository.findAll();

            // Fetch all projects from the repository
            List<Project> projects = projectRepository.findAll();

            // Randomly assign foods to projects
            Random random = new Random();
            projects.forEach(project -> {
                int numFoods = random.nextInt(foods.size()); // Random number of foods to assign to each project
                for (int i = 0; i < numFoods; i++) {
                    Food randomFood = foods.get(random.nextInt(foods.size()));
                    project.getFoods().add(randomFood);
                }
            });

            // Save the updated projects back to the repository
            projectRepository.saveAll(projects);
        };
    }
}
