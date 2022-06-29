package hr.bornast.fantasy.infrastructure.persistence.repository.impl;

import java.util.List;
import java.util.Optional;

import hr.bornast.fantasy.application.repository.UserRepository;
import hr.bornast.fantasy.domain.model.User;
import hr.bornast.fantasy.infrastructure.persistence.entity.UserEntity;
import hr.bornast.fantasy.infrastructure.persistence.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserEntityRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public List<User> findAll() {
        return userRepository.findAll().stream().map(x -> mapper.map(x, User.class)).toList();
    }

    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id).map(x -> mapper.map(x, User.class));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username).map(x -> mapper.map(x, User.class));
    }

    @Override
    public void create(User user) {
        userRepository.save(mapper.map(user, UserEntity.class));
    }

    @Override
    public User update(User user) {
        return mapper.map(userRepository.save(mapper.map(user, UserEntity.class)), User.class);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

}
