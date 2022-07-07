package hr.bornast.fantasy.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.infrastructure.persistence.entity.TeamEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamEntityRepository extends JpaRepository<TeamEntity, Integer> {
    Optional<TeamEntity> findByName(String name);
    Page<TeamEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
    List<TeamEntity> findByIdIn(List<Integer> ids);
}
