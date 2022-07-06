package hr.bornast.fantasy.infrastructure.persistence.repository.impl;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.application.repository.MediaRepository;
import hr.bornast.fantasy.domain.model.Media;
import hr.bornast.fantasy.infrastructure.persistence.entity.MediaEntity;
import hr.bornast.fantasy.infrastructure.persistence.repository.MediaEntityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MediaRepositoryImpl implements MediaRepository {

    private final MediaEntityRepository mediaRepository;
    private final ModelMapper mapper;

    @Override
    public Optional<Media> findByEntityAndIsMain(int entityId, int entityTypeId, int mediaTypeId, boolean isMain) {
        return mediaRepository.findByEntityAndIsMain(entityId, entityTypeId, mediaTypeId, isMain)
            .map(x -> mapper.map(x, Media.class));
    }

    @Override
    public List<Media> findByEntity(int entityId, int entityTypeId) {
        return mediaRepository.findByEntity(entityId, entityTypeId).stream()
            .map(x -> mapper.map(x, Media.class)).toList();
    }

    @Override
    public Optional<Media> findById(int id) {
        return mediaRepository.findById(id).map(x -> mapper.map(x, Media.class));
    }

    @Override
    public Media create(Media media) {
        return mapper.map(mediaRepository.save(mapper.map(media, MediaEntity.class)), Media.class);
    }

    @Override
    public Media update(Media media) {
        return mapper.map(mediaRepository.save(mapper.map(media, MediaEntity.class)), Media.class);
    }

    @Override
    public void delete(int id) {
        mediaRepository.deleteById(id);
    }

}
