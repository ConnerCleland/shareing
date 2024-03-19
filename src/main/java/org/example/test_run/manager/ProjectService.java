package org.example.test_run.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final FoodRepository foodRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, FoodRepository foodRepository) {
        this.projectRepository = projectRepository;
        this.foodRepository = foodRepository;
    }

    public Iterable<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    @Transactional
    public Set<Long> getFoodIdsForProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalStateException("Project with id " + projectId + " not found"));

        Set<Long> foodIds = new HashSet<>();
        project.getFoods().forEach(food -> foodIds.add(food.getId()));
        return foodIds;
    }

    @Transactional
    public void addNewProject(ProjectRequest projectRequest) {
        Project project = new Project(projectRequest.getName());

        // Fetch foods by their IDs from the repository
        Set<Food> foods = new HashSet<>();
        projectRequest.getFoodIds().forEach(foodId -> {
            Food food = foodRepository.findById(foodId)
                    .orElseThrow(() -> new IllegalStateException("Food with ID " + foodId + " not found"));
            foods.add(food);
        });

        // Associate fetched foods with the project
        project.setFoods(foods);

        // Save the project entity
        projectRepository.save(project);
    }

    @Transactional
    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new IllegalStateException("Project with ID " + id + " does not exist");
        }
        projectRepository.deleteById(id);
    }

    @Transactional
    public void updateProject(Long id, ProjectRequest projectRequest) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Project with ID " + id + " not found"));

        project.setName(projectRequest.getName());

        // Fetch foods by their IDs from the repository
        Set<Food> foods = new HashSet<>();
        projectRequest.getFoodIds().forEach(foodId -> {
            Food food = foodRepository.findById(foodId)
                    .orElseThrow(() -> new IllegalStateException("Food with ID " + foodId + " not found"));
            foods.add(food);
        });

        // Associate fetched foods with the project
        project.setFoods(foods);

        // Save the updated project entity
        projectRepository.save(project);
    }
}
