package org.example.test_run.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {

    private final ManagersServices managersServices;
    private final FranchiseRepository franchiseRepository; // Add FranchiseRepository dependency

    @Autowired
    public ManagerService(ManagersServices managersServices, FranchiseRepository franchiseRepository) {
        this.managersServices = managersServices;
        this.franchiseRepository = franchiseRepository; // Initialize FranchiseRepository
    }

    public List<Manager> getStudents() {
        return managersServices.findAll();
    }

    public Optional<Manager> getStudentById(Long id) {
        return managersServices.findById(id);
    }

    public void addNewStudent(Manager manager) {
        Optional<Manager> studentOptional = managersServices.findStudentByEmail(manager.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email is already taken");
        }
        managersServices.save(manager);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = managersServices.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("Student with ID " + studentId + " does not exist");
        }
        managersServices.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Manager manager = managersServices.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Manager with ID " + studentId + " does not exist"));

        if (name != null && !name.isEmpty()) {
            manager.setFirstName(name);
        }

        if (email != null && !email.isEmpty()) {
            Optional<Manager> studentOptional = managersServices.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Email is already taken");
            }
            manager.setEmail(email);
        }
    }

    // Add logic to manage franchises related to the manager
    @Transactional
    public void addFranchiseToManager(Long managerId, Franchise franchise) {
        Manager manager = managersServices.findById(managerId)
                .orElseThrow(() -> new IllegalStateException("Manager with ID " + managerId + " does not exist"));

        franchise.setId(managerId); // Set the franchise ID to be the same as manager ID
        franchise.setManager(manager); // Set the manager for the franchise
        franchiseRepository.save(franchise); // Save the franchise
    }
}
