package hr.bornast.fantasy.infrastructure.persistence.repository;

import java.util.Optional;

import hr.bornast.fantasy.infrastructure.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleEntityRepository extends JpaRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByName(String name);
}
