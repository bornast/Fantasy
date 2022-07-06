package hr.bornast.fantasy.application.service.impl;

import java.util.List;

import hr.bornast.fantasy.application.command.referee.CreateRefereeCommand;
import hr.bornast.fantasy.application.command.referee.UpdateRefereeCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.referee.RefereeDto;
import hr.bornast.fantasy.application.repository.RefereeRepository;
import hr.bornast.fantasy.application.service.RefereeService;
import hr.bornast.fantasy.common.exception.EntityNotFoundException;
import hr.bornast.fantasy.common.exception.ValidationException;
import hr.bornast.fantasy.domain.model.Referee;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefereeServiceImpl implements RefereeService {

    private final RefereeRepository refereeRepository;
    private final ModelMapper mapper;

    @Override
    public PagedListDto<RefereeDto> findAll(Pageable paging, String name) {
        if (name == null) {
            return new PagedListDto<RefereeDto>().getPagedResult(
                refereeRepository.findAll(paging)
                    .map(x -> mapper.map(x, RefereeDto.class)));
        }

        return new PagedListDto<RefereeDto>().getPagedResult(
            refereeRepository.findByName(name, paging)
                .map(x -> mapper.map(x, RefereeDto.class)));
    }

    @Override
    public List<RecordNameDto> findAllRecordNames() {
        return refereeRepository.findAll().stream().map(x -> mapper.map(x, RecordNameDto.class)).toList();
    }

    @Override
    public RefereeDto findOne(int id) {
        return refereeRepository.findById(id)
            .map(x -> mapper.map(x, RefereeDto.class))
            .orElseThrow(EntityNotFoundException::new);
    }

    public RefereeDto create(CreateRefereeCommand command) {
        refereeRepository.findByName(command.getName())
            .ifPresent(r -> {throw new ValidationException("Referee", "referee " + command.getName() + " already exists");});

        return mapper.map(refereeRepository.create(mapper.map(command, Referee.class)), RefereeDto.class);
    }

    @Override
    public RefereeDto update(int id, UpdateRefereeCommand command) {
        var referee = refereeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        mapper.map(command, referee);
        return mapper.map(refereeRepository.update(referee), RefereeDto.class);
    }

    @Override
    public void delete(int id) {
        refereeRepository.delete(id);
    }

}
