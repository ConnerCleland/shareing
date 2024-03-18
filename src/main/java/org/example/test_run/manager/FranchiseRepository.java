package org.example.test_run.manager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Long> {
    // You can add custom query methods here if needed
}
