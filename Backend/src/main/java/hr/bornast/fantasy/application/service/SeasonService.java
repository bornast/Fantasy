package hr.bornast.fantasy.application.service;

import java.util.List;

import hr.bornast.fantasy.application.command.season.CreateSeasonCommand;
import hr.bornast.fantasy.application.command.season.UpdateSeasonCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.season.SeasonDto;
import org.springframework.data.domain.Pageable;

public interface SeasonService {
    PagedListDto<SeasonDto> findAll(Pageable pageable, String name);
    List<RecordNameDto> findAllRecordNames();
    SeasonDto findOne(int id);
    SeasonDto create(CreateSeasonCommand command);
    SeasonDto update(int id, UpdateSeasonCommand command);
    void delete(int id);
}
