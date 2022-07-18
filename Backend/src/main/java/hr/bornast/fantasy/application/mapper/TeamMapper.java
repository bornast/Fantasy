package hr.bornast.fantasy.application.mapper;

import java.util.List;

import hr.bornast.fantasy.application.command.team.SaveTeamCommand;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.league.LeagueDto;
import hr.bornast.fantasy.application.dto.team.TeamDto;
import hr.bornast.fantasy.application.dto.team.TeamPlayerDto;
import hr.bornast.fantasy.application.dto.team.TeamResultDto;
import hr.bornast.fantasy.application.dto.team.TeamTableDto;
import hr.bornast.fantasy.application.dto.team.TeamTransferDto;
import hr.bornast.fantasy.domain.model.League;
import hr.bornast.fantasy.domain.model.Match;
import hr.bornast.fantasy.domain.model.Player;
import hr.bornast.fantasy.domain.model.Team;
import hr.bornast.fantasy.domain.model.Transfer;

public interface TeamMapper {
    TeamDto map(Team team);
    void map(SaveTeamCommand command, Team team);
    Team map(SaveTeamCommand command);
    RecordNameDto mapRecordName(Team team);
    TeamResultDto map(Match match);
    TeamTransferDto map(Transfer transfer);
    TeamPlayerDto map(Player player);
    List<TeamTableDto> map(League league);
    LeagueDto mapTeamLeague(League league);
}
