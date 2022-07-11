package hr.bornast.fantasy.application.service;

import java.util.List;

import hr.bornast.fantasy.application.dto.common.RecordNameDto;

public interface CardService {
    List<RecordNameDto> findAllRecordNames();
    RecordNameDto findOne(int id);
}
