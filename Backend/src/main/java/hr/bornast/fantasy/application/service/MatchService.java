package hr.bornast.fantasy.application.service;

import java.util.List;

import hr.bornast.fantasy.application.command.match.SaveMatchCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.match.MatchDto;
import org.springframework.data.domain.Pageable;

public interface MatchService {
    PagedListDto<MatchDto> findAll(Pageable pageable, String name);
    List<RecordNameDto> findAllRecordNames();
    MatchDto findOne(int id);
    MatchDto create(SaveMatchCommand command);
    MatchDto update(int id, SaveMatchCommand command);
    void delete(int id);
}
