package hr.bornast.fantasy.infrastructure.persistence.repository;

import java.util.Optional;

import hr.bornast.fantasy.infrastructure.persistence.entity.RefereeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefereeEntityRepository extends JpaRepository<RefereeEntity, Integer> {
    Optional<RefereeEntity> findByName(String name);
    Page<RefereeEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
