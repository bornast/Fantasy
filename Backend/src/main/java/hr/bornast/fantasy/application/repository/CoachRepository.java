package hr.bornast.fantasy.application.repository;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.domain.model.Coach;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CoachRepository {
    Page<Coach> findAll(Pageable paging);
    Page<Coach> findByName(String name, Pageable paging);
    List<Coach> findAll();
    Optional<Coach> findById(int id);
    Optional<Coach> findByName(String name);
    Coach create(Coach coach);
    Coach update(Coach coach);
    void delete(int id);

}
