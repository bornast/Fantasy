package hr.bornast.fantasy.infrastructure.persistence.repository;

import java.util.Optional;

import hr.bornast.fantasy.infrastructure.persistence.entity.PositionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionEntityRepository extends JpaRepository<PositionEntity, Integer> {
    Optional<PositionEntity> findByName(String name);
    Page<PositionEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
