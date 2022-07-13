package hr.bornast.fantasy.infrastructure.persistence.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import hr.bornast.fantasy.application.repository.PlayerRepository;
import hr.bornast.fantasy.domain.model.Match;
import hr.bornast.fantasy.domain.model.Player;
import hr.bornast.fantasy.infrastructure.persistence.entity.PlayerEntity;
import hr.bornast.fantasy.infrastructure.persistence.mapper.MatchEntityMapper;
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
    private final MatchEntityMapper matchMapper;
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
    public List<Player> findAllPlayersInTransfer(int teamId) {
        var playerEntities = playerRepository.findAllPlayersInTransfer(teamId);
        var matchesToAdd = new HashMap<Integer, List<Match>>();
        for(var player : playerEntities) {
            matchesToAdd.put(player.getId(), new ArrayList<>());
            player.getMatches().forEach(x -> matchesToAdd.get(player.getId()).add(matchMapper.map(x.getMatch())));
        }

        var players = playerEntities.stream().map(x -> mapper.map(x, Player.class)).toList();
        players.forEach(x -> x.setMatches(new HashSet<>()));
        for (var player : players) {
            if (matchesToAdd.containsKey(player.getId())) {
                player.getMatches().addAll(matchesToAdd.get(player.getId()));
            }
        }

        return players;
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
    public List<Player> findByIds(Set<Integer> ids) {
        return playerRepository.findByIdIn(ids).stream().map(x -> mapper.map(x, Player.class)).toList();
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
