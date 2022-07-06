package hr.bornast.fantasy.application.service;

import java.util.List;

import hr.bornast.fantasy.application.command.formation.CreateFormationCommand;
import hr.bornast.fantasy.application.command.formation.UpdateFormationCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.formation.FormationDto;
import org.springframework.data.domain.Pageable;

public interface FormationService {
    PagedListDto<FormationDto> findAll(Pageable pageable, String name);
    List<RecordNameDto> findAllRecordNames();
    FormationDto findOne(int id);
    FormationDto create(CreateFormationCommand command);
    FormationDto update(int id, UpdateFormationCommand command);
    void delete(int id);
}
