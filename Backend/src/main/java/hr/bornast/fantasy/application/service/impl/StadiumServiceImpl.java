package hr.bornast.fantasy.application.service.impl;

import java.util.List;

import hr.bornast.fantasy.application.command.stadium.CreateStadiumCommand;
import hr.bornast.fantasy.application.command.stadium.UpdateStadiumCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.stadium.StadiumDto;
import hr.bornast.fantasy.application.repository.StadiumRepository;
import hr.bornast.fantasy.application.service.StadiumService;
import hr.bornast.fantasy.common.exception.EntityNotFoundException;
import hr.bornast.fantasy.common.exception.ValidationException;
import hr.bornast.fantasy.domain.model.Stadium;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StadiumServiceImpl implements StadiumService {

    private final StadiumRepository stadiumRepository;
    private final ModelMapper mapper;

    @Override
    public PagedListDto<StadiumDto> findAll(Pageable paging, String name) {
        if (name == null) {
            return new PagedListDto<StadiumDto>().getPagedResult(
                stadiumRepository.findAll(paging)
                    .map(x -> mapper.map(x, StadiumDto.class)));
        }

        return new PagedListDto<StadiumDto>().getPagedResult(
            stadiumRepository.findByName(name, paging)
                .map(x -> mapper.map(x, StadiumDto.class)));
    }

    @Override
    public List<RecordNameDto> findAllRecordNames() {
        return stadiumRepository.findAll().stream().map(x -> mapper.map(x, RecordNameDto.class)).toList();
    }

    @Override
    public StadiumDto findOne(int id) {
        return stadiumRepository.findById(id)
            .map(x -> mapper.map(x, StadiumDto.class))
            .orElseThrow(EntityNotFoundException::new);
    }

    public StadiumDto create(CreateStadiumCommand command) {
        stadiumRepository.findByName(command.getName())
            .ifPresent(r -> {throw new ValidationException("Stadium", "stadium " + command.getName() + " already exists");});

        return mapper.map(stadiumRepository.create(mapper.map(command, Stadium.class)), StadiumDto.class);
    }

    @Override
    public StadiumDto update(int id, UpdateStadiumCommand command) {
        var stadium = stadiumRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        mapper.map(command, stadium);
        return mapper.map(stadiumRepository.update(stadium), StadiumDto.class);
    }

    @Override
    public void delete(int id) {
        stadiumRepository.delete(id);
    }

}
