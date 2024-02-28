package org.example.test_run.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class CustomerController {

    private final ManagerService managerService;

    @Autowired
    public CustomerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping
    public List<Manager> getStudents() {
        return managerService.getStudents();
    }

    @GetMapping("/{studentID}")
    public Manager getStudentById(@PathVariable Long studentID) {
        return managerService.getStudentById(studentID)
                .orElseThrow(() -> new IllegalStateException("Manager with id " + studentID + " not found"));
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Manager manager) {
        managerService.addNewStudent(manager);
    }

    @DeleteMapping(path = "{studentID}")
    public void deleteStudent(@PathVariable("studentID") Long studentId) {
        managerService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentID}")
    public void updateStudent(
            @PathVariable("studentID") Long studentID,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String email) {
        managerService.updateStudent(studentID, firstName, email);
    }
}
