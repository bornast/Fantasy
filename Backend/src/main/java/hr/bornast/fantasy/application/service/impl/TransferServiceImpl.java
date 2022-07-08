package hr.bornast.fantasy.application.service.impl;

import java.util.List;

import hr.bornast.fantasy.application.command.transfer.SaveTransferCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.transfer.TransferDto;
import hr.bornast.fantasy.application.mapper.TransferMapper;
import hr.bornast.fantasy.application.repository.TransferRepository;
import hr.bornast.fantasy.application.service.TransferService;
import hr.bornast.fantasy.common.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;
    private final TransferMapper mapper;

    @Override
    public PagedListDto<TransferDto> findAll(Pageable paging, String name) {
        if (name == null) {
            return new PagedListDto<TransferDto>().getPagedResult(
                transferRepository.findAll(paging)
                    .map(mapper::map));
        }

        return new PagedListDto<TransferDto>().getPagedResult(
            transferRepository.findByName(name, paging)
                .map(mapper::map));
    }

    @Override
    public List<RecordNameDto> findAllRecordNames() {
        return transferRepository.findAll().stream().map(mapper::mapRecordName).toList();
    }

    @Override
    public TransferDto findOne(int id) {
        return transferRepository.findById(id)
            .map(mapper::map)
            .orElseThrow(EntityNotFoundException::new);
    }

    public TransferDto create(SaveTransferCommand command) {
        return mapper.map(transferRepository.create(mapper.map(command)));
    }

    @Override
    public TransferDto update(int id, SaveTransferCommand command) {
        var transfer = transferRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        mapper.map(command, transfer);
        return mapper.map(transferRepository.update(transfer));
    }

    @Override
    public void delete(int id) {
        transferRepository.delete(id);
    }

}
