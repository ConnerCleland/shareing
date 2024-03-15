package org.example.test_run.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final TransactionTemplate transactionTemplate;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, TransactionTemplate transactionTemplate) {
        this.projectRepository = projectRepository;
        this.transactionTemplate = transactionTemplate;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public void addNewProject(Project project) {
        projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public void updateProject(Long id, String name) {
        transactionTemplate.execute(status -> {
            Project project = projectRepository.findById(id)
                    .orElseThrow(() -> new IllegalStateException("Project with id " + id + " not found"));

            if (name != null && !name.isEmpty()) {
                project.setName(name);
                projectRepository.save(project);
            }
            return null; // or any other value if needed
        });
    }
}
