package org.example.jpa.mapping.simplex.one_to_n.without_middle_table;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NationRepository extends JpaRepository<Nation, Long> {
    @Query("select n from Nation n join fetch n.regions")
    List<Nation> findAllWithFetchJoin();

    @Query("select n from Nation n join fetch n.regions")
    Page<Nation> findAllWithFetchJoin(Pageable pageable);

    @EntityGraph(attributePaths = {"regions"})
    @Query("select n from Nation n")
    List<Nation> findAllWithEntityGraph();

    @EntityGraph(attributePaths = {"regions"})
    @Query("select n from Nation n")
    Page<Nation> findAllWithEntityGraph(Pageable pageable);

    @Query("select distinct n from Nation n join fetch n.regions")
    List<Nation> findAllWithDistinctFetchJoin();

    @Query("select n.id,n from Nation n")
    List<Long> findAllNationIds();

    @Query(value = "select * from Nation n join Region r on n.id = r.id;", nativeQuery = true)
    List<Nation> findAllWithJPQL();
}
