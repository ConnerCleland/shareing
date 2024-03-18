package org.example.test_run.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/managers")
public class ManagerController {

    private final ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping
    public ResponseEntity<List<Manager>> getAllManagers() {
        List<Manager> managers = managerService.getStudents();
        return new ResponseEntity<>(managers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manager> getManagerById(@PathVariable Long id) {
        Optional<Manager> manager = managerService.getStudentById(id);
        return manager.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add")
    public ResponseEntity<String> addManager(@RequestBody Manager manager) {
        managerService.addNewStudent(manager);
        return new ResponseEntity<>("Manager added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateManager(@PathVariable Long id,
                                                @RequestParam(required = false) String name,
                                                @RequestParam(required = false) String email) {
        managerService.updateStudent(id, name, email);
        return new ResponseEntity<>("Manager updated successfully", HttpStatus.OK);
    }

    @PostMapping("/{managerId}/franchise/add")
    public ResponseEntity<String> addFranchiseToManager(@PathVariable Long managerId,
                                                        @RequestBody Franchise franchise) {
        managerService.addFranchiseToManager(managerId, franchise);
        return new ResponseEntity<>("Franchise added to manager successfully", HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteManager(@PathVariable Long id) {
        managerService.deleteStudent(id);
        return new ResponseEntity<>("Manager deleted successfully", HttpStatus.OK);
    }

}
