package hr.bornast.fantasy.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.infrastructure.persistence.entity.LeagueEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LeagueEntityRepository extends JpaRepository<LeagueEntity, Integer> {
    Optional<LeagueEntity> findByName(String name);
    Page<LeagueEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @Query(value = "select l.* from leagues l inner join league_teams lt on (l.id = lt.league_id) where lt.team_id = ?1", nativeQuery = true)
    List<LeagueEntity> findByTeamId(int teamId);
}
