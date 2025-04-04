package org.example.jpa.mapping.simplex.one_to_n.multiple_bag.use_batch_size;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface A2Repository extends JpaRepository<A2, Long> {
    @Query("select a from A2 a join fetch a.bs join fetch a.cs")
    List<A2> findAllFetchJoin();

}
