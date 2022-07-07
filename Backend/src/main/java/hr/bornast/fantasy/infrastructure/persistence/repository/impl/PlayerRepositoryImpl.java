package hr.bornast.fantasy.infrastructure.persistence.repository.impl;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.application.repository.PlayerRepository;
import hr.bornast.fantasy.domain.model.Player;
import hr.bornast.fantasy.infrastructure.persistence.entity.PlayerEntity;
import hr.bornast.fantasy.infrastructure.persistence.repository.PlayerEntityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PlayerRepositoryImpl implements PlayerRepository {

    private final PlayerEntityRepository playerRepository;
    private final ModelMapper mapper;

    @Override
    public Page<Player> findAll(Pageable paging) {
        return playerRepository.findAll(paging).map(x -> mapper.map(x, Player.class));
    }

    @Override
    public Page<Player> findByName(String name, Pageable pageable) {
        return playerRepository.findByNameContainingIgnoreCase(name, pageable)
            .map(x -> mapper.map(x, Player.class));
    }

    @Override
    public List<Player> findAll() {
        return playerRepository.findAll().stream().map(x -> mapper.map(x, Player.class)).toList();
    }

    @Override
    public Optional<Player> findById(int id) {
        return playerRepository.findById(id).map(x -> mapper.map(x, Player.class));
    }

    @Override
    public Optional<Player> findByName(String name) {
        return playerRepository.findByName(name)
            .map(x -> mapper.map(x, Player.class));
    }

    @Override
    public Player create(Player player) {
        return mapper.map(playerRepository.save(mapper.map(player, PlayerEntity.class)), Player.class);
    }

    @Override
    public void delete(int id) {
        playerRepository.deleteById(id);
    }

    @Override
    public Player update(Player player) {
        return mapper.map(playerRepository.save(mapper.map(player, PlayerEntity.class)), Player.class);
    }

}
