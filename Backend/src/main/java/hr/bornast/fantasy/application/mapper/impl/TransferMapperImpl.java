package hr.bornast.fantasy.application.mapper.impl;

import hr.bornast.fantasy.application.command.transfer.SaveTransferCommand;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.transfer.TransferDto;
import hr.bornast.fantasy.application.mapper.TransferMapper;
import hr.bornast.fantasy.application.repository.PlayerRepository;
import hr.bornast.fantasy.application.repository.TeamRepository;
import hr.bornast.fantasy.common.exception.EntityNotFoundException;
import hr.bornast.fantasy.domain.model.Transfer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferMapperImpl implements TransferMapper {

    private final ModelMapper mapper;
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    @Override
    public TransferDto map(Transfer transfer) {
        return mapper.map(transfer, TransferDto.class);
    }

    @Override
    public void map(SaveTransferCommand command, Transfer transfer) {
        mapper.map(command, transfer);
        setRelatedData(command, transfer);
    }

    @Override
    public Transfer map(SaveTransferCommand command) {
        var result = mapper.map(command, Transfer.class);
        setRelatedData(command, result);
        return result;
    }

    private void setRelatedData(SaveTransferCommand command, Transfer transfer) {
        var player = playerRepository.findById(command.getPlayerId())
            .orElseThrow(EntityNotFoundException::new);

        var fromTeam = teamRepository.findById(command.getFromTeamId());

        var toTeam = teamRepository.findById(command.getToTeamId())
            .orElseThrow(EntityNotFoundException::new);

        transfer.setPlayer(player);
        fromTeam.ifPresent(transfer::setFromTeam);
        transfer.setToTeam(toTeam);
    }

    @Override
    public RecordNameDto mapRecordName(Transfer transfer) {
        var recordName = new RecordNameDto();
        recordName.setId(transfer.getId());
        recordName.setName(transfer.getPlayer().getName() + " transfer to " + transfer.getToTeam().getName());
        return recordName;
    }
}
