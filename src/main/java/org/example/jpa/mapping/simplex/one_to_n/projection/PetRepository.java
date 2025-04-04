package org.example.jpa.mapping.simplex.one_to_n.projection;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    @Query("select p from Pet p")
    List<PetProjection> findAllProjection();
}