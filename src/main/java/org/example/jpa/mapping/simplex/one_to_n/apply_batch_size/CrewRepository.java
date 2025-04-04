package org.example.jpa.mapping.simplex.one_to_n.apply_batch_size;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewRepository extends JpaRepository<Crew, Long> {
    Page<Crew> findAll(Pageable pageable);
}
