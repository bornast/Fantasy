package hr.bornast.fantasy.application.repository;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.domain.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeamRepository {
    Page<Team> findAll(Pageable paging);
    List<Team> findAllByName(String name);
    Page<Team> findByName(String name, Pageable paging);
    List<Team> findAll();
    Optional<Team> findById(int id);
    Optional<Team> findByName(String name);
    List<Team> findByIds(List<Integer> ids);
    Team create(Team team);
    Team update(Team team);
    void delete(int id);
    Page<Team> findFavouriteTeams(int userId, Pageable paging);
    Page<Team> findFavouriteTeams(int userId, String name, Pageable paging);
    List<Team> findFavouriteTeams(int userId);
    List<Team> findFavouriteTeams(int userId, String name);
}
