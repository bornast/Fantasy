package hr.bornast.fantasy.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.infrastructure.persistence.entity.SeasonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonEntityRepository extends JpaRepository<SeasonEntity, Integer> {
    Optional<SeasonEntity> findByName(String name);
    List<SeasonEntity> findByIdIn(List<Integer> ids);
    Page<SeasonEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
