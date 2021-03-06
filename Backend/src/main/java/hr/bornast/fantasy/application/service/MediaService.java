package hr.bornast.fantasy.application.service;

import java.util.List;

import hr.bornast.fantasy.application.command.media.SetMainMediaCommand;
import hr.bornast.fantasy.application.command.media.UploadMediaCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.media.EntityMediaDto;
import hr.bornast.fantasy.application.dto.media.MediaDetailDto;
import org.springframework.data.domain.Pageable;

public interface MediaService {
    EntityMediaDto getEntityMedia(int entityId, int entityTypeId);
    MediaDetailDto upload(UploadMediaCommand command);
    void delete(int id);
    void setMain(int id, SetMainMediaCommand command);
    void approve(int id);
    void disapprove(int id);
    PagedListDto<MediaDetailDto> findApprovedMemories(Pageable paging, int matchId);
    List<MediaDetailDto> findPersonalMemories(int entityId);
    PagedListDto<MediaDetailDto> findAllMedia(Pageable paging);
}
