package hr.bornast.fantasy.application.repository;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.domain.model.Media;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MediaRepository {
    Optional<Media> findByEntityAndIsMain(int entityId, int entityTypeId, boolean isMain);
    List<Media> findByEntity(int entityId, int entityTypeId);
    Optional<Media> findById(int id);
    Media create(Media media);
    Media update(Media media);
    void delete(int id);
    Page<Media> findApprovedMedia(int entityId, int entityTypeId, Pageable paging);
    List<Media> findPersonalMedia(int userId, int entityTypeId);
    Page<Media> findAllMedia(Pageable paging);
}
