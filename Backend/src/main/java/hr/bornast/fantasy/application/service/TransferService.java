package hr.bornast.fantasy.application.service;

import java.util.List;

import hr.bornast.fantasy.application.command.transfer.SaveTransferCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.transfer.TransferDto;
import org.springframework.data.domain.Pageable;

public interface TransferService {
    PagedListDto<TransferDto> findAll(Pageable pageable, String name);
    List<RecordNameDto> findAllRecordNames();
    TransferDto findOne(int id);
    TransferDto create(SaveTransferCommand command);
    TransferDto update(int id, SaveTransferCommand command);
    void delete(int id);
}
