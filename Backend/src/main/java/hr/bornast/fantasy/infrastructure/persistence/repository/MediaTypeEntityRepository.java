package hr.bornast.fantasy.infrastructure.persistence.repository;

import hr.bornast.fantasy.infrastructure.persistence.entity.MediaTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaTypeEntityRepository extends JpaRepository<MediaTypeEntity, Integer> {
}
