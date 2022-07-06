package hr.bornast.fantasy.application.service;

import java.util.List;

import hr.bornast.fantasy.application.command.president.CreatePresidentCommand;
import hr.bornast.fantasy.application.command.president.UpdatePresidentCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.president.PresidentDto;
import org.springframework.data.domain.Pageable;

public interface PresidentService {
    PagedListDto<PresidentDto> findAll(Pageable pageable, String name);
    List<RecordNameDto> findAllRecordNames();
    PresidentDto findOne(int id);
    PresidentDto create(CreatePresidentCommand command);
    PresidentDto update(int id, UpdatePresidentCommand command);
    void delete(int id);
}
