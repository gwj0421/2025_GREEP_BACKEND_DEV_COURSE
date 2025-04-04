package org.example.jpa.mapping.simplex.one_to_n.multiple_bag.list_to_set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface A1Repository extends JpaRepository<A1, Long> {
    @Query("select a from A1 a join fetch a.b1s join fetch a.c1s")
    List<A1> findAllFetchJoin();

}
