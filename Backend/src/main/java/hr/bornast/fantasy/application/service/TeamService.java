package hr.bornast.fantasy.application.service;

import java.util.List;

import hr.bornast.fantasy.application.command.team.SaveTeamCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.league.LeagueDto;
import hr.bornast.fantasy.application.dto.team.TeamDto;
import hr.bornast.fantasy.application.dto.team.TeamPlayerDto;
import hr.bornast.fantasy.application.dto.team.TeamResultDto;
import hr.bornast.fantasy.application.dto.team.TeamTableDto;
import hr.bornast.fantasy.application.dto.team.TeamTransferDto;
import org.springframework.data.domain.Pageable;

public interface TeamService {
    PagedListDto<TeamDto> findAll(Pageable paging, String name);
    List<RecordNameDto> findAllRecordNames();
    List<TeamPlayerDto> findTeamPlayers(int id);
    TeamDto findOne(int id);
    TeamDto create(SaveTeamCommand command);
    TeamDto update(int id, SaveTeamCommand command);
    void delete(int id);
    void setFavourite(int teamId);
    void setUnfavored(int teamId);
    PagedListDto<TeamDto> findFavourites(Pageable paging, String name);
    PagedListDto<TeamDto> findUnfavored(Pageable paging, String name);
    PagedListDto<TeamResultDto> findTeamResults(int teamId, Pageable paging);
    PagedListDto<TeamTransferDto> findTeamTransfers(int teamId, Pageable paging);
    List<TeamTableDto> getTeamTable(int leagueId);
    List<LeagueDto> getTeamLeagues(int id);
}
