package hr.bornast.fantasy.application.mapper;

import hr.bornast.fantasy.application.command.team.SaveTeamCommand;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.team.TeamDto;
import hr.bornast.fantasy.domain.model.Team;

public interface TeamMapper {
    TeamDto map(Team team);
    void map(SaveTeamCommand command, Team team);
    Team map(SaveTeamCommand command);
    RecordNameDto mapRecordName(Team team);
}
