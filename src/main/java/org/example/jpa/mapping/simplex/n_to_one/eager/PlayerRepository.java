package org.example.jpa.mapping.simplex.n_to_one.eager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findPlayersByTeam_Id(Long teamId);

    Page<Player> getPlayersByName(String name, Pageable pageable);

    Slice<Player> readPlayersByName(String name, Pageable pageable);

    Collection<PlayerDto> findPlayersByName(String name);
}
