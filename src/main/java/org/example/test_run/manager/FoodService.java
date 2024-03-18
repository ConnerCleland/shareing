package org.example.test_run.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<Food> foods = foodRepository.findAll();
        foods.forEach(this::incrementProjectCount); // Increment project count for each food item
        return foods;
    }

    public Optional<Food> getFoodById(Long foodId) {
        Optional<Food> food = foodRepository.findById(foodId);
        food.ifPresent(this::incrementProjectCount); // Increment project count if food exists
        return food;
    }

    public void addNewFood(Food food) {
        foodRepository.save(food);
    }

    public void deleteFood(Long foodId) {
        if (!foodRepository.existsById(foodId)) {
            throw new IllegalStateException("Food with ID " + foodId + " does not exist");
        }
        foodRepository.deleteById(foodId);
    }

    public void updateFood(Long foodId, String name) {
        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new IllegalStateException("Food with ID " + foodId + " does not exist"));

        if (name != null && !name.isEmpty()) {
            food.setName(name);
            foodRepository.save(food);
        }
    }

    // Method to increment project count for a given food item
    private void incrementProjectCount(Food food) {
        food.setProjectCount(food.getProjectCount() + 1); // Increment project count by one
    }
}
