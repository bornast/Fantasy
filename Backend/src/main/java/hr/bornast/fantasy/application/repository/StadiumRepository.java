package hr.bornast.fantasy.application.repository;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.domain.model.Stadium;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StadiumRepository {
    Page<Stadium> findAll(Pageable paging);
    Page<Stadium> findByName(String name, Pageable paging);
    List<Stadium> findAll();
    Optional<Stadium> findById(int id);
    Optional<Stadium> findByName(String name);
    Stadium create(Stadium stadium);
    Stadium update(Stadium stadium);
    void delete(int id);

}
