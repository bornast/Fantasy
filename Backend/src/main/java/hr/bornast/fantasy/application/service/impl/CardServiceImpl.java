package hr.bornast.fantasy.application.service.impl;

import java.util.List;

import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.repository.CardRepository;
import hr.bornast.fantasy.application.service.CardService;
import hr.bornast.fantasy.common.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final ModelMapper mapper;

    @Override
    public List<RecordNameDto> findAllRecordNames() {
        return cardRepository.findAll().stream().map(x -> mapper.map(x, RecordNameDto.class)).toList();
    }

    @Override
    public RecordNameDto findOne(int id) {
        return cardRepository.findById(id)
            .map(x -> mapper.map(x, RecordNameDto.class))
            .orElseThrow(EntityNotFoundException::new);
    }

}
