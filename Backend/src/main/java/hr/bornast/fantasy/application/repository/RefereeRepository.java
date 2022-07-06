package hr.bornast.fantasy.application.repository;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.domain.model.Referee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RefereeRepository {
    Page<Referee> findAll(Pageable paging);
    Page<Referee> findByName(String name, Pageable paging);
    List<Referee> findAll();
    Optional<Referee> findById(int id);
    Optional<Referee> findByName(String name);
    Referee create(Referee referee);
    Referee update(Referee referee);
    void delete(int id);

}
