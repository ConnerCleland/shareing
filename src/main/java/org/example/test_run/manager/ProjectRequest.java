package org.example.test_run.manager;

import java.util.Set;

public class ProjectRequest {

    private String name;
    private Set<Long> foodIds;

    // Constructors, getters, and setters

    public ProjectRequest() {
    }

    public ProjectRequest(String name, Set<Long> foodIds) {
        this.name = name;
        this.foodIds = foodIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Long> getFoodIds() {
        return foodIds;
    }

    public void setFoodIds(Set<Long> foodIds) {
        this.foodIds = foodIds;
    }
}
