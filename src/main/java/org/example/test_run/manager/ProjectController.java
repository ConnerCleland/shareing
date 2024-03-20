package org.example.test_run.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/project")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public Iterable<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{projectId}")
    public Project getProjectById(@PathVariable Long projectId) {
        return projectService.getProjectById(projectId)
                .orElseThrow(() -> new IllegalStateException("Project with id " + projectId + " not found"));
    }

    @GetMapping("/{projectId}/foodIds")
    public Set<Long> getFoodIdsForProject(@PathVariable Long projectId) {
        return projectService.getFoodIdsForProject(projectId);
    }

    @PostMapping
    public void addNewProject(@RequestBody ProjectRequest projectRequest) {
        projectService.addNewProject(projectRequest);
    }

    @DeleteMapping("/{projectId}")
    public void deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
    }

    @PutMapping("/{projectId}")
    public void updateProject(
            @PathVariable Long projectId,
            @RequestBody ProjectRequest projectRequest) {
        projectService.updateProject(projectId, projectRequest);
    }

    // Add food to project
    @PostMapping("/{projectId}/addFood/{foodId}")
    public void addFoodToProject(@PathVariable Long projectId, @PathVariable Long foodId) {
        projectService.addFoodToProject(projectId, foodId);
    }
}
