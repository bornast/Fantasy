package hr.bornast.fantasy.application.repository;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.domain.model.Media;

public interface MediaRepository {
    Optional<Media> findByEntityAndIsMain(int entityId, int entityTypeId, int mediaTypeId, boolean isMain);
    List<Media> findByEntity(int entityId, int entityTypeId);
    Optional<Media> findById(int id);
    Media create(Media media);
    Media update(Media media);
    void delete(int id);
}
