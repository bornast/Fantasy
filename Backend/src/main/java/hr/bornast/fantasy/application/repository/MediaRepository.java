package hr.bornast.fantasy.application.repository;

import java.util.Optional;

import hr.bornast.fantasy.domain.model.Media;

public interface MediaRepository {
//    Page<Media> findAll(Pageable paging);
//    Page<Media> findByMediaTypeAndEntityAndIsMain(int entityId, int entityTypeId, int mediaTypeId, boolean isMain, Pageable paging);
    Optional<Media> findByEntityAndIsMain(int entityId, int entityTypeId, int mediaTypeId, boolean isMain);
//    Optional<Media> findById(int id);
    Media create(Media media);
//    Media setMain(Media media);
//    void delete(int id);
}
