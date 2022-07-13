package hr.bornast.fantasy.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.infrastructure.persistence.entity.MediaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaEntityRepository extends JpaRepository<MediaEntity, Integer> {
    @Query("SELECT m from MediaEntity m where m.entityId = ?1 and m.entityType.id = ?2 and m.isMain = ?3")
    Optional<MediaEntity> findByEntityAndIsMain(int entityId, int entityTypeId, boolean isMain);

    @Query("SELECT m from MediaEntity m where m.entityId = ?1 and m.entityType.id = ?2")
    List<MediaEntity> findByEntity(int entityId, int entityTypeId);

    @Query(value = "select m.* from media m where m.entity_id = ?1 and m.entity_type_id = ?2 and m.is_approved = true", nativeQuery = true)
    Page<MediaEntity> findApprovedMedia(int entityId, int entityTypeId, Pageable paging);

    @Query(value = "select m.* from media m where m.uploaded_by_user_id = ?1 and m.entity_type_id = ?2", nativeQuery = true)
    List<MediaEntity> findUserMedia(int userId, int entityTypeId);

    @Query(value = "select m.* from media m order by is_approved asc, created_at desc", nativeQuery = true)
    Page<MediaEntity> findAllMedia(Pageable paging);
}