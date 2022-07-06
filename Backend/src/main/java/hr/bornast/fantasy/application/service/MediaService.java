package hr.bornast.fantasy.application.service;

import java.util.List;

import hr.bornast.fantasy.application.command.media.SetMainMediaCommand;
import hr.bornast.fantasy.application.command.media.UploadMediaCommand;
import hr.bornast.fantasy.application.dto.media.EntityMediaDto;
import hr.bornast.fantasy.application.dto.media.MediaDto;

public interface MediaService {
    List<EntityMediaDto> getEntityMedia(int entityId, int entityTypeId);
    MediaDto upload(UploadMediaCommand command);
    void delete(int id);
    void setMain(int id, SetMainMediaCommand command);
}
