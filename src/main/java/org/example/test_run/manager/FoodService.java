package org.example.test_run.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final ManagerRepository managerRepository; // Autowire ManagerRepository

    @Autowired
    public FoodService(FoodRepository foodRepository, ManagerRepository managerRepository) {
        this.foodRepository = foodRepository;
        this.managerRepository = managerRepository; // Initialize ManagerRepository
    }

    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    public Optional<Food> getFoodById(Long foodId) {
        return foodRepository.findById(foodId);
    }

    @Transactional
    public void addNewFood(Food food) {
        foodRepository.save(food);
    }

    @Transactional
    public void deleteFood(Long foodId) {
        if (!foodRepository.existsById(foodId)) {
            throw new IllegalStateException("Food with ID " + foodId + " does not exist");
        }
        foodRepository.deleteById(foodId);
    }

    @Transactional
    public void updateFoodProjectAndManager(Long foodId, String project, String managerName) {
        // Retrieve the food entity by ID
        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new IllegalStateException("Food with ID " + foodId + " does not exist"));

        // Update the project if provided
        if (project != null && !project.isEmpty()) {
            food.setProject(project);
        }

        // Update the manager name if provided
        if (managerName != null && !managerName.isEmpty()) {
            // Find the manager entity by name
            Optional<Manager> managerOptional = managerRepository.findByFirstName(managerName);

            // Check if manager exists
            if (managerOptional.isPresent()) {
                // If manager exists, set it for the food
                food.setManager(managerOptional.get());
            } else {
                // If manager does not exist, you may throw an exception or handle it as per your requirements
                throw new IllegalArgumentException("Manager with name " + managerName + " does not exist");
            }
        }

        // Save the updated food entity
        foodRepository.save(food);
    }
}
