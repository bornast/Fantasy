package hr.bornast.fantasy.application.mapper.impl;

import java.time.OffsetDateTime;

import hr.bornast.fantasy.application.command.media.UploadMediaCommand;
import hr.bornast.fantasy.application.dto.media.EntityMediaDto;
import hr.bornast.fantasy.application.dto.media.MediaDto;
import hr.bornast.fantasy.application.mapper.MediaMapper;
import hr.bornast.fantasy.application.repository.EntityTypeRepository;
import hr.bornast.fantasy.application.repository.MediaTypeRepository;
import hr.bornast.fantasy.common.exception.EntityNotFoundException;
import hr.bornast.fantasy.domain.model.Media;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediaMapperImpl implements MediaMapper {

    private final ModelMapper mapper;
    private final MediaTypeRepository mediaTypeRepository;
    private final EntityTypeRepository entityTypeRepository;

    @Override
    public Media map(UploadMediaCommand command) {
        var result = mapper.map(command, Media.class);

        var mediaType = mediaTypeRepository
            .findById(command.getMediaTypeId())
            .orElseThrow(EntityNotFoundException::new);

        var entityType = entityTypeRepository
            .findById(command.getEntityTypeId())
            .orElseThrow(EntityNotFoundException::new);

        result.setMediaType(mediaType);
        result.setEntityType(entityType);
        result.setCreatedAt(OffsetDateTime.now());

        return result;
    }

    @Override
    public MediaDto map(Media media) {
        var result = new MediaDto();
        result.setId(media.getId());
        result.setEntityTypeId(media.getEntityType().getId());
        result.setMediaTypeId(media.getMediaType().getId());
        result.setUrl(media.getUrl());
        result.setIsMain(media.isMain());
        return result;
    }

    @Override
    public EntityMediaDto mapEntityMedia(Media media) {
        var result = new EntityMediaDto();
        result.setId(media.getId());
        result.setUrl(media.getUrl());
        result.setIsMain(media.isMain());
        return result;
    }

}
