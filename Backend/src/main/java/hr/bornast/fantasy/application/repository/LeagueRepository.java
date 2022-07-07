package hr.bornast.fantasy.application.repository;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.domain.model.League;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LeagueRepository {
    Page<League> findAll(Pageable paging);
    Page<League> findByName(String name, Pageable paging);
    List<League> findAll();
    Optional<League> findById(int id);
    Optional<League> findByName(String name);
    League create(League league);
    League update(League league);
    void delete(int id);

}
