package org.example.jpa.mapping.simplex.one_to_n.projection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query("select c from Company c")
    List<CompanyProjection> findAllProjection();
}

