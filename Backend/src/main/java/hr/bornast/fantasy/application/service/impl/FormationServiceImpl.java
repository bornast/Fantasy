package hr.bornast.fantasy.application.service.impl;

import java.util.List;

import hr.bornast.fantasy.application.command.formation.CreateFormationCommand;
import hr.bornast.fantasy.application.command.formation.UpdateFormationCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.formation.FormationDto;
import hr.bornast.fantasy.application.repository.FormationRepository;
import hr.bornast.fantasy.application.service.FormationService;
import hr.bornast.fantasy.common.exception.EntityNotFoundException;
import hr.bornast.fantasy.common.exception.ValidationException;
import hr.bornast.fantasy.domain.model.Formation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FormationServiceImpl implements FormationService {

    private final FormationRepository formationRepository;
    private final ModelMapper mapper;

    @Override
    public PagedListDto<FormationDto> findAll(Pageable paging, String name) {
        if (name == null) {
            return new PagedListDto<FormationDto>().getPagedResult(
                formationRepository.findAll(paging)
                    .map(x -> mapper.map(x, FormationDto.class)));
        }

        return new PagedListDto<FormationDto>().getPagedResult(
            formationRepository.findByName(name, paging)
                .map(x -> mapper.map(x, FormationDto.class)));
    }

    @Override
    public List<RecordNameDto> findAllRecordNames() {
        return formationRepository.findAll().stream().map(x -> mapper.map(x, RecordNameDto.class)).toList();
    }

    @Override
    public FormationDto findOne(int id) {
        return formationRepository.findById(id)
            .map(x -> mapper.map(x, FormationDto.class))
            .orElseThrow(EntityNotFoundException::new);
    }

    public FormationDto create(CreateFormationCommand command) {
        formationRepository.findByName(command.getName())
            .ifPresent(r -> {throw new ValidationException("Formation", "formation " + command.getName() + " already exists");});

        return mapper.map(formationRepository.create(mapper.map(command, Formation.class)), FormationDto.class);
    }

    @Override
    public FormationDto update(int id, UpdateFormationCommand command) {
        var formation = formationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        mapper.map(command, formation);
        return mapper.map(formationRepository.update(formation), FormationDto.class);
    }

    @Override
    public void delete(int id) {
        formationRepository.delete(id);
    }

}
