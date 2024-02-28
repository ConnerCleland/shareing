package org.example.test_run.manager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagersServices
        extends JpaRepository<Manager, Long> {

    @Query("SELECT s FROM Manager s WHERE s.email = ?1")
    Optional<Manager> findStudentByEmail(String email);

}
