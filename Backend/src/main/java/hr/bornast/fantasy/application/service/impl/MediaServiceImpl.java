package hr.bornast.fantasy.application.service.impl;

import hr.bornast.fantasy.application.command.media.UploadMediaCommand;
import hr.bornast.fantasy.application.dto.media.MediaDto;
import hr.bornast.fantasy.application.mapper.MediaMapper;
import hr.bornast.fantasy.application.repository.MediaRepository;
import hr.bornast.fantasy.application.service.CloudinaryService;
import hr.bornast.fantasy.application.service.MediaService;
import hr.bornast.fantasy.common.enums.MediaType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;
    private final CloudinaryService cloudinaryService;
    private final MediaMapper mapper;

    @Override
    public MediaDto upload(UploadMediaCommand command) {
        var uploadResult = cloudinaryService.upload(command.getFile());

        var media = mapper.map(command);

        media.setUrl(uploadResult.getUrl());
        media.setPublicId(uploadResult.getPublicId());

        var isMain = mediaRepository.findByEntityAndIsMain(
            command.getEntityId(), command.getEntityTypeId(), command.getMediaTypeId(), true);

        if (isMain.isEmpty() && command.getMediaTypeId() == MediaType.IMAGE.getValue()) {
            media.setMain(true);
        }

        return mapper.map(mediaRepository.create(media));
    }

//    @Override
//    public void delete(int id) {
//        mediaRepository.delete(id);
//    }
//
//    @Override
//    public void setMain(int id) {
//
//    }
}
