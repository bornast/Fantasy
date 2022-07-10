package hr.bornast.fantasy.application.repository;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.domain.model.Formation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FormationRepository {
    Page<Formation> findAll(Pageable paging);
    Page<Formation> findByName(String name, Pageable paging);
    List<Formation> findAll();
    Optional<Formation> findById(int id);
    Optional<Formation> findByName(String name);
    List<Formation> findByIds(List<Integer> ids);
    Formation create(Formation formation);
    Formation update(Formation formation);
    void delete(int id);

}
