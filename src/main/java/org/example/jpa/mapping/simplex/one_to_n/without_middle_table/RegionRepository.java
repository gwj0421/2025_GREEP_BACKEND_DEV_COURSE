package org.example.jpa.mapping.simplex.one_to_n.without_middle_table;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Long> {
    Page<Region> findAll(Pageable pageable);

    @Query("select r from Region r where r.nationId in :nationIds")
    List<Region> findRegionsByNationsIds(@Param("nationIds") List<Long> nationIds);

}