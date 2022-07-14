package hr.bornast.fantasy.infrastructure.persistence.repository;

import java.util.List;

import hr.bornast.fantasy.infrastructure.persistence.entity.MatchEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchEntityRepository extends JpaRepository<MatchEntity, Integer> {
    Page<MatchEntity> findByTeams_Team_NameContainingIgnoreCase(String teamName, Pageable pageable);
    List<MatchEntity> findByIdIn(List<Integer> ids);

    @Query(value = "select m.* from matches m inner join match_teams mt on (m.id = mt.match_id) where mt.team_id = ?1", nativeQuery = true)
    Page<MatchEntity> findByTeamId(int teamId, Pageable paging);

    @Query(value = "select m.* from matches m inner join match_teams mt on (m.id = mt.match_id) where mt.team_id = ?1", nativeQuery = true)
    List<MatchEntity> findByTeamId(int teamId);
}
