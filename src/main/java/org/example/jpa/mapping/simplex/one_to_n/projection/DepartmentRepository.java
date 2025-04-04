package org.example.jpa.mapping.simplex.one_to_n.projection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query("select d from Department d")
    List<DepartmentProjection> findAllProjection();
}
