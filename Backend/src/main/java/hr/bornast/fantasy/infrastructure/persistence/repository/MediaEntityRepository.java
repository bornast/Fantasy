package hr.bornast.fantasy.infrastructure.persistence.repository;

import java.util.Optional;

import hr.bornast.fantasy.infrastructure.persistence.entity.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaEntityRepository extends JpaRepository<MediaEntity, Integer> {
    @Query("SELECT m from MediaEntity m where m.entityId = ?1 and m.entityType.id = ?2 and m.mediaType.id = ?3 and m.isMain = ?4")
    Optional<MediaEntity> findByEntityAndIsMain(int entityId, int entityTypeId, int mediaTypeId, boolean isMain);

//    List<> findByIdIn(List<Integer> ids);
//    Page<RoleEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
}