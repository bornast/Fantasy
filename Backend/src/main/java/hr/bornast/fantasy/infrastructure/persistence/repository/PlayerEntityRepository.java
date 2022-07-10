package hr.bornast.fantasy.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import hr.bornast.fantasy.infrastructure.persistence.entity.PlayerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerEntityRepository extends JpaRepository<PlayerEntity, Integer> {
    Optional<PlayerEntity> findByName(String name);
    Page<PlayerEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
    List<PlayerEntity> findByIdIn(Set<Integer> ids);
}
