package hr.bornast.fantasy.application.service.impl;

import hr.bornast.fantasy.application.command.media.SetMainMediaCommand;
import hr.bornast.fantasy.application.command.media.UploadMediaCommand;
import hr.bornast.fantasy.application.dto.media.EntityMediaDto;
import hr.bornast.fantasy.application.dto.media.MediaDetailDto;
import hr.bornast.fantasy.application.mapper.MediaMapper;
import hr.bornast.fantasy.application.repository.MediaRepository;
import hr.bornast.fantasy.application.service.CloudinaryService;
import hr.bornast.fantasy.application.service.MediaService;
import hr.bornast.fantasy.common.enums.MediaType;
import hr.bornast.fantasy.common.exception.EntityNotFoundException;
import hr.bornast.fantasy.common.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;
    private final CloudinaryService cloudinaryService;
    private final MediaMapper mapper;

    @Override
    public EntityMediaDto getEntityMedia(int entityId, int entityTypeId) {
        return mapper.mapEntityMedia(mediaRepository.findByEntity(entityId, entityTypeId));
    }

    @Override
    public MediaDetailDto upload(UploadMediaCommand command) {
        var uploadResult = cloudinaryService.upload(command.getFile());

        var media = mapper.map(command, uploadResult.getMediaType().getValue());

        media.setUrl(uploadResult.getUrl());
        media.setPublicId(uploadResult.getPublicId());

        var isMain = mediaRepository.findByEntityAndIsMain(
            command.getEntityId(), command.getEntityTypeId(), true);

        if (isMain.isEmpty() && uploadResult.getMediaType() == MediaType.IMAGE) {
            media.setMain(true);
        }

        return mapper.map(mediaRepository.create(media));
    }

    @Override
    public void setMain(int id, SetMainMediaCommand command) {
        var photoToUpdate = mediaRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        if (photoToUpdate.getMediaType().getId() == MediaType.VIDEO.getValue()) {
            throw new ValidationException("Media", "Cannot set Video media type as main media");
        }

        var maybeMainMedia = mediaRepository.findByEntityAndIsMain(
            command.getEntityId(), command.getEntityTypeId(), true);

        if (maybeMainMedia.isPresent()) {
            var mainMedia = maybeMainMedia.get();
            mainMedia.setMain(false);
            mediaRepository.update(mainMedia);
        }

        photoToUpdate.setMain(true);
        mediaRepository.update(photoToUpdate);
    }

    @Override
    public void delete(int id) {
        mediaRepository.delete(id);
    }

}
