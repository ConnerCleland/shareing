package org.example.test_run.manager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProjectConfig {

    @Bean
    CommandLineRunner projectCommandLineRunner(ProjectRepository repository) {
        return args -> {
            Project project1 = new Project("Project A");
            Project project2 = new Project("Project B");
            Project project3 = new Project("Project C");

            repository.saveAll(List.of(project1, project2, project3));
        };
    }
}
