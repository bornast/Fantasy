package hr.bornast.fantasy.application.service.impl;

import java.util.List;

import hr.bornast.fantasy.application.command.player.CreatePlayerCommand;
import hr.bornast.fantasy.application.command.player.UpdatePlayerCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.player.PlayerDto;
import hr.bornast.fantasy.application.mapper.PlayerMapper;
import hr.bornast.fantasy.application.repository.PlayerRepository;
import hr.bornast.fantasy.application.service.PlayerService;
import hr.bornast.fantasy.common.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper mapper;

    @Override
    public PagedListDto<PlayerDto> findAll(Pageable paging, String name) {
        if (name == null) {
            return new PagedListDto<PlayerDto>().getPagedResult(
                playerRepository.findAll(paging)
                    .map(mapper::map));
        }

        return new PagedListDto<PlayerDto>().getPagedResult(
            playerRepository.findByName(name, paging)
                .map(mapper::map));
    }

    @Override
    public List<RecordNameDto> findAllRecordNames() {
        return playerRepository.findAll().stream().map(mapper::mapRecordName).toList();
    }

    @Override
    public PlayerDto findOne(int id) {
        return playerRepository.findById(id)
            .map(mapper::map)
            .orElseThrow(EntityNotFoundException::new);
    }

    public PlayerDto create(CreatePlayerCommand command) {
        return mapper.map(playerRepository.create(mapper.map(command)));
    }

    @Override
    public PlayerDto update(int id, UpdatePlayerCommand command) {
        var player = playerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        mapper.map(command, player);
        return mapper.map(playerRepository.update(player));
    }

    @Override
    public void delete(int id) {
        playerRepository.delete(id);
    }

}
