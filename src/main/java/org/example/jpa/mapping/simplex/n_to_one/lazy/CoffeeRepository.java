package org.example.jpa.mapping.simplex.n_to_one.lazy;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
