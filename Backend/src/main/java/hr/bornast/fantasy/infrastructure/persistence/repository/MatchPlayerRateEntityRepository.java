package hr.bornast.fantasy.infrastructure.persistence.repository;

import java.util.Optional;

import hr.bornast.fantasy.infrastructure.persistence.entity.MatchPlayerRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchPlayerRateEntityRepository extends JpaRepository<MatchPlayerRateEntity, Integer> {
    Optional<MatchPlayerRateEntity> findByMatchIdAndPlayerIdAndUserId(int matchId, int playerId, int userId);

    @Query(value = "select coalesce(avg(r.rate), 0.0) from rates r where r.player_id = ?1", nativeQuery = true)
    double findPlayerRate(int playerId);

    @Query(value = "select coalesce(avg(r.rate), 0.0) from rates r where r.player_id = ?1 and r.match_id = ?2", nativeQuery = true)
    double findPlayerMatchRate(int playerId, int matchId);
}
