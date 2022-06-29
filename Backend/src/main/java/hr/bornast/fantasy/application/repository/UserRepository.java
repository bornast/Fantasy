package hr.bornast.fantasy.application.repository;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.domain.model.User;

public interface UserRepository {
    List<User> findAll();
    Optional<User> findById(int id);
    Optional<User> findByUsername(String username);
    User create(User user);
    User update(User user);
    void delete(int id);

}
