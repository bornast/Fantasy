package hr.bornast.fantasy.application.service;

import java.util.List;

import hr.bornast.fantasy.application.command.team.SaveTeamCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.team.TeamDto;
import org.springframework.data.domain.Pageable;

public interface TeamService {
    PagedListDto<TeamDto> findAll(Pageable pageable, String name);
    List<RecordNameDto> findAllRecordNames();
    List<RecordNameDto> findTeamPlayers(int id);
    TeamDto findOne(int id);
    TeamDto create(SaveTeamCommand command);
    TeamDto update(int id, SaveTeamCommand command);
    void delete(int id);
    void setFavourite(int teamId);
    void setUnfavored(int teamId);
    PagedListDto<TeamDto> findFavourites(Pageable pageable);
    PagedListDto<TeamDto> findUnfavored(Pageable pageable);
}
