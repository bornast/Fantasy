package hr.bornast.fantasy.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.infrastructure.persistence.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleEntityRepository extends JpaRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByName(String name);
    List<RoleEntity> findByIdIn(List<Integer> ids);
    Page<RoleEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
