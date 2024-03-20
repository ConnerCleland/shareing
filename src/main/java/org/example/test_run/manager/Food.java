package org.example.test_run.manager;

import jakarta.persistence.*;

@Entity
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String project; // Changed projects to a String
    @ManyToOne(fetch = FetchType.EAGER) // Fetch manager eagerly
    @JoinColumn(name = "manager_id")
    private Manager manager; 
    public Food() {
    }

    public Food(String name) {
        this.name = name;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    // toString method
    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", project='" + (project != null ? project : "") + '\'' + // Check for null
                ", manager='" + (manager != null ? manager.getFirstName() : "") + '\'' + // Get manager's first name
                '}';
    }
}
