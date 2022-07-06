package hr.bornast.fantasy.infrastructure.persistence.repository;

import java.util.Optional;

import hr.bornast.fantasy.infrastructure.persistence.entity.PresidentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresidentEntityRepository extends JpaRepository<PresidentEntity, Integer> {
    Optional<PresidentEntity> findByName(String name);
    Page<PresidentEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
