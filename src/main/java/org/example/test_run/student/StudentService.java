package org.example.test_run.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentsServices studentsServices;

    @Autowired
    public StudentService(StudentsServices studentsServices) {
        this.studentsServices = studentsServices;
    }

    public List<Student> getStudents(){
        return studentsServices.findAll();

        }
    public Optional<Student> getStudentById(Long id) {
        return studentsServices.findById(id);
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentsServices
                .findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email Taken");
        }
        studentsServices.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentsServices.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException(
                    "student with ID " + studentId + "does not exists");
        }
        studentsServices.deleteById(studentId);
    }
    @Transactional
    public void updateStudent(Long studentID, String name, String email) {
        Student student = studentsServices.findById(studentID)
                .orElseThrow(() -> new IllegalStateException(
                        "Student with id " + studentID + " does not exist"));

        if (name != null && !name.isEmpty() && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && !email.isEmpty() && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentsServices.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Email is already taken");
            }
            student.setEmail(email);
        }
    }

}
