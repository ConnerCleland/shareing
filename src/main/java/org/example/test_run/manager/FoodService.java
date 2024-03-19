package org.example.test_run.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
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
    public void updateFoodProject(Long foodId, String project) {
        // Retrieve the food entity by ID
        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new IllegalStateException("Food with ID " + foodId + " does not exist"));

        // Update the project if provided
        if (project != null && !project.isEmpty()) {
            food.setProject(project);
        }

        // Save the updated food entity
        foodRepository.save(food);
    }

    public void updateFood(Food food) {
    }
}
