package hr.bornast.fantasy.infrastructure.persistence.repository;

import java.util.Optional;

import hr.bornast.fantasy.infrastructure.persistence.entity.FormationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormationEntityRepository extends JpaRepository<FormationEntity, Integer> {
    Optional<FormationEntity> findByName(String name);
    Page<FormationEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
