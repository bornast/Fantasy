package hr.bornast.fantasy.application.mapper.impl;

import java.time.OffsetDateTime;
import java.util.List;

import hr.bornast.fantasy.application.command.media.UploadMediaCommand;
import hr.bornast.fantasy.application.dto.media.EntityMediaDto;
import hr.bornast.fantasy.application.dto.media.MediaDetailDto;
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
    private final EntityTypeRepository entityTypeRepository;
    private final MediaTypeRepository mediaTypeRepository;

    @Override
    public Media map(UploadMediaCommand command, int mediaTypeId, int userId) {
        var result = mapper.map(command, Media.class);

        var entityType = entityTypeRepository
            .findById(command.getEntityTypeId())
            .orElseThrow(EntityNotFoundException::new);

        var mediaType = mediaTypeRepository
            .findById(mediaTypeId)
            .orElseThrow(EntityNotFoundException::new);

        result.setEntityType(entityType);
        result.setMediaType(mediaType);
        result.setCreatedAt(OffsetDateTime.now());
        result.setUploadedByUserId(userId);

        return result;
    }

    @Override
    public MediaDetailDto map(Media media) {
        var result = new MediaDetailDto();
        result.setId(media.getId());
        result.setEntityTypeId(media.getEntityType().getId());
        result.setMediaTypeId(media.getMediaType().getId());
        result.setUrl(media.getUrl());
        result.setIsMain(media.isMain());
        result.setApproved(media.isApproved());
        result.setUploadedByUserId(media.getUploadedByUserId());
        return result;
    }

    @Override
    public EntityMediaDto mapEntityMedia(List<Media> media) {
        var result = new EntityMediaDto();
        for (var m : media) {
            if (m.isMain()) {
                result.setMainMedia(m.getUrl());
            }
            var mediaToAdd = new MediaDto();
            mediaToAdd.setId(m.getId());
            mediaToAdd.setUrl(m.getUrl());
            mediaToAdd.setIsMain(m.isMain());
            mediaToAdd.setMediaTypeId(m.getMediaType().getId());
            result.addMedia(mediaToAdd);
        }
        return result;
    }

}
