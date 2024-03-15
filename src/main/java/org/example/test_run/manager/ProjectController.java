package org.example.test_run.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/project")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{projectId}")
    public Project getProjectById(@PathVariable Long projectId) {
        return projectService.getProjectById(projectId)
                .orElseThrow(() -> new IllegalStateException("Project with id " + projectId + " not found"));
    }

    @PostMapping
    public void addNewProject(@RequestBody Project project) {
        projectService.addNewProject(project);
    }

    @DeleteMapping("/{projectId}")
    public void deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
    }

    @PutMapping("/{projectId}")
    public void updateProject(
            @PathVariable Long projectId,
            @RequestParam(required = false) String name) {
        projectService.updateProject(projectId, name);
    }
}
