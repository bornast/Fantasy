package hr.bornast.fantasy.application.service;

import hr.bornast.fantasy.application.command.media.UploadMediaCommand;
import hr.bornast.fantasy.application.dto.media.MediaDto;

public interface MediaService {
    MediaDto upload(UploadMediaCommand command);
//    void delete(int id);
//    void setMain(int id);
}
