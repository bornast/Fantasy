package hr.bornast.fantasy.infrastructure.persistence.repository;

import java.util.Optional;

import hr.bornast.fantasy.infrastructure.persistence.entity.StadiumEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StadiumEntityRepository extends JpaRepository<StadiumEntity, Integer> {
    Optional<StadiumEntity> findByName(String name);
    Page<StadiumEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
