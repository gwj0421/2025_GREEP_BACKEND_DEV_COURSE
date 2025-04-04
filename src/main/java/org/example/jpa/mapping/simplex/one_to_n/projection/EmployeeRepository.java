package org.example.jpa.mapping.simplex.one_to_n.projection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select e from Employee e")
    List<EmployeeProjection> findAllProjection();
}

