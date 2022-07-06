package hr.bornast.fantasy.application.repository;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.domain.model.Season;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SeasonRepository {
    Page<Season> findAll(Pageable paging);
    Page<Season> findByName(String name, Pageable paging);
    List<Season> findAll();
    Optional<Season> findById(int id);
    Optional<Season> findByName(String name);
    Season create(Season season);
    Season update(Season season);
    void delete(int id);

}
