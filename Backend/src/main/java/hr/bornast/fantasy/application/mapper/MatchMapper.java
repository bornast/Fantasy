package hr.bornast.fantasy.application.mapper;

import hr.bornast.fantasy.application.command.match.SaveMatchCommand;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.match.MatchDto;
import hr.bornast.fantasy.domain.model.Match;

public interface MatchMapper {
    MatchDto map(Match match);
    void map(SaveMatchCommand command, Match match);
    Match map(SaveMatchCommand command);
    RecordNameDto mapRecordName(Match match);
}
