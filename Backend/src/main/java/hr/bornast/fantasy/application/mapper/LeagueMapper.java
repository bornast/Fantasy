package hr.bornast.fantasy.application.mapper;

import hr.bornast.fantasy.application.command.league.SaveLeagueCommand;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.league.LeagueDto;
import hr.bornast.fantasy.domain.model.League;

public interface LeagueMapper {
    LeagueDto map(League league);
    void map(SaveLeagueCommand command, League league);
    League map(SaveLeagueCommand command);
    RecordNameDto mapRecordName(League league);
}
