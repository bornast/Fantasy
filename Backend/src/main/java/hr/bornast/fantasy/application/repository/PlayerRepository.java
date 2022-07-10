package hr.bornast.fantasy.application.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import hr.bornast.fantasy.domain.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlayerRepository {
    Page<Player> findAll(Pageable paging);
    Page<Player> findByName(String name, Pageable paging);
    List<Player> findAll();
    List<Player> findAllPlayersInTransfer(int teamId);
    Optional<Player> findById(int id);
    Optional<Player> findByName(String name);
    List<Player> findByIds(Set<Integer> ids);
    Player create(Player player);
    Player update(Player player);
    void delete(int id);

}
