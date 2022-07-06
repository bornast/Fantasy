package hr.bornast.fantasy.application.repository;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.domain.model.President;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PresidentRepository {
    Page<President> findAll(Pageable paging);
    Page<President> findByName(String name, Pageable paging);
    List<President> findAll();
    Optional<President> findById(int id);
    Optional<President> findByName(String name);
    President create(President president);
    President update(President president);
    void delete(int id);

}
