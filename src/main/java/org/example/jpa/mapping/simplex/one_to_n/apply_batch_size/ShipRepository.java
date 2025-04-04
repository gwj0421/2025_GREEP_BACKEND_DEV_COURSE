package org.example.jpa.mapping.simplex.one_to_n.apply_batch_size;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShipRepository extends JpaRepository<Ship, Long> {
    @Query("select s from Ship s")
    List<Ship> findAllWithBatch();

    @Query("select s from Ship s")
    Page<Ship> findAllWithBatch(Pageable pageable);
}
