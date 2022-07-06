package hr.bornast.fantasy.application.service.impl;

import java.util.List;

import hr.bornast.fantasy.application.command.president.CreatePresidentCommand;
import hr.bornast.fantasy.application.command.president.UpdatePresidentCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.president.PresidentDto;
import hr.bornast.fantasy.application.repository.PresidentRepository;
import hr.bornast.fantasy.application.service.PresidentService;
import hr.bornast.fantasy.common.exception.EntityNotFoundException;
import hr.bornast.fantasy.common.exception.ValidationException;
import hr.bornast.fantasy.domain.model.President;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PresidentServiceImpl implements PresidentService {

    private final PresidentRepository presidentRepository;
    private final ModelMapper mapper;

    @Override
    public PagedListDto<PresidentDto> findAll(Pageable paging, String name) {
        if (name == null) {
            return new PagedListDto<PresidentDto>().getPagedResult(
                presidentRepository.findAll(paging)
                    .map(x -> mapper.map(x, PresidentDto.class)));
        }

        return new PagedListDto<PresidentDto>().getPagedResult(
            presidentRepository.findByName(name, paging)
                .map(x -> mapper.map(x, PresidentDto.class)));
    }

    @Override
    public List<RecordNameDto> findAllRecordNames() {
        return presidentRepository.findAll().stream().map(x -> mapper.map(x, RecordNameDto.class)).toList();
    }

    @Override
    public PresidentDto findOne(int id) {
        return presidentRepository.findById(id)
            .map(x -> mapper.map(x, PresidentDto.class))
            .orElseThrow(EntityNotFoundException::new);
    }

    public PresidentDto create(CreatePresidentCommand command) {
        presidentRepository.findByName(command.getName())
            .ifPresent(r -> {throw new ValidationException("President", "president " + command.getName() + " already exists");});

        return mapper.map(presidentRepository.create(mapper.map(command, President.class)), PresidentDto.class);
    }

    @Override
    public PresidentDto update(int id, UpdatePresidentCommand command) {
        var president = presidentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        mapper.map(command, president);
        return mapper.map(presidentRepository.update(president), PresidentDto.class);
    }

    @Override
    public void delete(int id) {
        presidentRepository.delete(id);
    }

}
