package org.example.test_run.manager;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ManagerService {

    private final ManagersServices managersServices;

    @Autowired
    public ManagerService(ManagersServices managersServices) {
        this.managersServices = managersServices;
    }

    public List<Manager> getStudents(){
        return managersServices.findAll();

        }
    public Optional<Manager> getStudentById(Long id) {
        return managersServices.findById(id);
    }

    public void addNewStudent(Manager manager) {
        Optional<Manager> studentOptional = managersServices
                .findStudentByEmail(manager.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email Taken");
        }
        managersServices.save(manager);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = managersServices.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException(
                    "student with ID " + studentId + "does not exists");
        }
        managersServices.deleteById(studentId);
    }
    @Transactional
    public void updateStudent(Long studentID, String name, String email) {
        Manager manager = managersServices.findById(studentID)
                .orElseThrow(() -> new IllegalStateException(
                        "Manager with id " + studentID + " does not exist"));

        if (name != null && !name.isEmpty() && !Objects.equals(manager.getFirstName(), name)) {
            manager.setFirstName(name);
        }

        if (email != null && !email.isEmpty() && !Objects.equals(manager.getEmail(), email)) {
            Optional<Manager> studentOptional = managersServices.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Email is already taken");
            }
            manager.setEmail(email);
        }
    }

}
