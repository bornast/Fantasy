package hr.bornast.fantasy.application.service;

import hr.bornast.fantasy.application.command.media.SetMainMediaCommand;
import hr.bornast.fantasy.application.command.media.UploadMediaCommand;
import hr.bornast.fantasy.application.dto.media.EntityMediaDto;
import hr.bornast.fantasy.application.dto.media.MediaDetailDto;

public interface MediaService {
    EntityMediaDto getEntityMedia(int entityId, int entityTypeId);
    MediaDetailDto upload(UploadMediaCommand command);
    void delete(int id);
    void setMain(int id, SetMainMediaCommand command);
}
