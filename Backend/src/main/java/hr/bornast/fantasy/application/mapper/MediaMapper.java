package hr.bornast.fantasy.application.mapper;

import hr.bornast.fantasy.application.command.media.UploadMediaCommand;
import hr.bornast.fantasy.application.dto.media.MediaDto;
import hr.bornast.fantasy.domain.model.Media;

public interface MediaMapper {
    Media map(UploadMediaCommand command);
    MediaDto map(Media media);
}
