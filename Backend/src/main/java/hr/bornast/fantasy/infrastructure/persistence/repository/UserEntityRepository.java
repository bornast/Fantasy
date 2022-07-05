package hr.bornast.fantasy.infrastructure.persistence.repository;

import java.util.Optional;

import hr.bornast.fantasy.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsername(String username);
    Page<UserEntity> findByUsernameContainingIgnoreCase(String username, Pageable pageable);
}
