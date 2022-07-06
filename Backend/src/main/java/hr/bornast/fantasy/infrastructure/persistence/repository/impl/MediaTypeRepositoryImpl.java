package hr.bornast.fantasy.infrastructure.persistence.repository.impl;

import java.util.Optional;

import hr.bornast.fantasy.application.repository.MediaTypeRepository;
import hr.bornast.fantasy.domain.model.MediaType;
import hr.bornast.fantasy.infrastructure.persistence.entity.MediaTypeEntity;
import hr.bornast.fantasy.infrastructure.persistence.repository.MediaTypeEntityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MediaTypeRepositoryImpl implements MediaTypeRepository {

    private final MediaTypeEntityRepository mediaTypeRepository;
    private final ModelMapper mapper;

    @Override
    public Optional<MediaType> findById(int id) {
        return mediaTypeRepository.findById(id).map(x -> mapper.map(x, MediaType.class));
    }

    @Override
    public MediaType create(MediaType media) {
        return mapper.map(mediaTypeRepository.save(mapper.map(media, MediaTypeEntity.class)), MediaType.class);
    }

}
