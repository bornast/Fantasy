package hr.bornast.fantasy.application.mapper.impl;

import hr.bornast.fantasy.application.command.player.CreatePlayerCommand;
import hr.bornast.fantasy.application.command.player.UpdatePlayerCommand;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.player.PlayerDto;
import hr.bornast.fantasy.application.mapper.PlayerMapper;
import hr.bornast.fantasy.application.repository.PositionRepository;
import hr.bornast.fantasy.application.service.MediaService;
import hr.bornast.fantasy.common.enums.EntityType;
import hr.bornast.fantasy.common.exception.EntityNotFoundException;
import hr.bornast.fantasy.domain.model.Player;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerMapperImpl implements PlayerMapper {

    private final ModelMapper mapper;
    private final PositionRepository positionRepository;
    private final MediaService mediaService;

    @Override
    public PlayerDto map(Player player) {
        var result = mapper.map(player, PlayerDto.class);
        var media = mediaService.getEntityMedia(player.getId(), EntityType.PLAYER.getValue());
        result.setMedia(media);
        return result;
    }

    @Override
    public void map(UpdatePlayerCommand command, Player player) {
        mapper.map(command, player);

        var position = positionRepository.findById(command.getPositionId())
            .orElseThrow(EntityNotFoundException::new);

        player.setPosition(position);
    }

    @Override
    public Player map(CreatePlayerCommand command) {
        var result = mapper.map(command, Player.class);

        var position = positionRepository.findById(command.getPositionId())
            .orElseThrow(EntityNotFoundException::new);

        result.setPosition(position);

        return result;
    }

    @Override
    public RecordNameDto mapRecordName(Player player) {
        return mapper.map(player, RecordNameDto.class);
    }
}
