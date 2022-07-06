package hr.bornast.fantasy.application.mapper;

import java.util.List;

import hr.bornast.fantasy.application.command.media.UploadMediaCommand;
import hr.bornast.fantasy.application.dto.media.EntityMediaDto;
import hr.bornast.fantasy.application.dto.media.MediaDetailDto;
import hr.bornast.fantasy.domain.model.Media;

public interface MediaMapper {
    Media map(UploadMediaCommand command);
    MediaDetailDto map(Media media);
    EntityMediaDto mapEntityMedia(List<Media> media);
}
