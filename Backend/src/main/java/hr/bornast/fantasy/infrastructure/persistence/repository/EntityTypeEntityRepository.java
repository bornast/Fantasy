package hr.bornast.fantasy.infrastructure.persistence.repository;

import hr.bornast.fantasy.infrastructure.persistence.entity.EntityTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityTypeEntityRepository extends JpaRepository<EntityTypeEntity, Integer> {
}
