package hr.bornast.fantasy.application.service;

import java.util.List;

import hr.bornast.fantasy.application.command.league.SaveLeagueCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.league.LeagueDto;
import org.springframework.data.domain.Pageable;

public interface LeagueService {
    PagedListDto<LeagueDto> findAll(Pageable pageable, String name);
    List<RecordNameDto> findAllRecordNames();
    LeagueDto findOne(int id);
    LeagueDto create(SaveLeagueCommand command);
    LeagueDto update(int id, SaveLeagueCommand command);
    void delete(int id);
}
