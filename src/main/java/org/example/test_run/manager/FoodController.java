package org.example.test_run.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/food")
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping
    public List<Food> getFoods() {
        return foodService.getAllFoods();
    }

    @GetMapping("/{foodId}")
    public Food getFoodById(@PathVariable Long foodId) {
        return foodService.getFoodById(foodId)
                .orElseThrow(() -> new IllegalStateException("Food with id " + foodId + " not found"));
    }

    @PostMapping
    public void addNewFood(@RequestBody Food food) {
        foodService.addNewFood(food);
    }

    @DeleteMapping("/{foodId}")
    public void deleteFood(@PathVariable Long foodId) {
        foodService.deleteFood(foodId);
    }

    @PutMapping("/{foodId}")
    public void updateFood(
            @PathVariable Long foodId,
            @RequestParam(required = false) String name) {
        foodService.updateFood(foodId, name);
    }
}
