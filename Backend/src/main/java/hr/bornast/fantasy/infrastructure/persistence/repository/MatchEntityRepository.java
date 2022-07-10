package hr.bornast.fantasy.infrastructure.persistence.repository;

import java.util.List;

import hr.bornast.fantasy.infrastructure.persistence.entity.MatchEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchEntityRepository extends JpaRepository<MatchEntity, Integer> {
    Page<MatchEntity> findByTeams_Team_NameContainingIgnoreCase(String teamName, Pageable pageable);
    List<MatchEntity> findByIdIn(List<Integer> ids);
}
