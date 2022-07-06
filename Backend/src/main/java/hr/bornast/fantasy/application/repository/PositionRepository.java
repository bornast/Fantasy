package hr.bornast.fantasy.application.repository;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.domain.model.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PositionRepository {
    Page<Position> findAll(Pageable paging);
    Page<Position> findByName(String name, Pageable paging);
    List<Position> findAll();
    Optional<Position> findById(int id);
    Optional<Position> findByName(String name);
    Position create(Position position);
    Position update(Position position);
    void delete(int id);

}
