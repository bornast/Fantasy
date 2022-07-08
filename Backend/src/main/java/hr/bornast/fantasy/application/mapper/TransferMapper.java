package hr.bornast.fantasy.application.mapper;

import hr.bornast.fantasy.application.command.transfer.SaveTransferCommand;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.transfer.TransferDto;
import hr.bornast.fantasy.domain.model.Transfer;

public interface TransferMapper {
    TransferDto map(Transfer team);
    void map(SaveTransferCommand command, Transfer team);
    Transfer map(SaveTransferCommand command);
    RecordNameDto mapRecordName(Transfer team);
}
