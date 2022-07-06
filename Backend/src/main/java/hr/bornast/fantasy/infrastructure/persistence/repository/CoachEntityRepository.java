package hr.bornast.fantasy.infrastructure.persistence.repository;

import java.util.Optional;

import hr.bornast.fantasy.infrastructure.persistence.entity.CoachEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachEntityRepository extends JpaRepository<CoachEntity, Integer> {
    Optional<CoachEntity> findByName(String name);
    Page<CoachEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
