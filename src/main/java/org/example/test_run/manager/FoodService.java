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
        return foodRepository.findAll();
    }

    public Optional<Food> getFoodById(Long foodId) {
        return foodRepository.findById(foodId);
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
}
