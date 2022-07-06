package hr.bornast.fantasy.application.service;

import java.util.List;

import hr.bornast.fantasy.application.command.referee.CreateRefereeCommand;
import hr.bornast.fantasy.application.command.referee.UpdateRefereeCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.referee.RefereeDto;
import org.springframework.data.domain.Pageable;

public interface RefereeService {
    PagedListDto<RefereeDto> findAll(Pageable pageable, String name);
    List<RecordNameDto> findAllRecordNames();
    RefereeDto findOne(int id);
    RefereeDto create(CreateRefereeCommand command);
    RefereeDto update(int id, UpdateRefereeCommand command);
    void delete(int id);
}
