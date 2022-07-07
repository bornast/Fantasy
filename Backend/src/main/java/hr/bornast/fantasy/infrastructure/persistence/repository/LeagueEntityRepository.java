package hr.bornast.fantasy.infrastructure.persistence.repository;

import java.util.Optional;

import hr.bornast.fantasy.infrastructure.persistence.entity.LeagueEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueEntityRepository extends JpaRepository<LeagueEntity, Integer> {
    Optional<LeagueEntity> findByName(String name);
    Page<LeagueEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
