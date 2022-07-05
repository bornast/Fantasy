package hr.bornast.fantasy.application.repository;

import java.util.Optional;

import hr.bornast.fantasy.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepository {
    Page<User> findAll(Pageable paging);
    Page<User> findByUsername(String username, Pageable paging);
    Optional<User> findById(int id);
    Optional<User> findByUsername(String username);
    User create(User user);
    User update(User user);
    void delete(int id);

}
