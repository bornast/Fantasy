package hr.bornast.fantasy.infrastructure.persistence.repository.impl;

import java.util.Optional;

import hr.bornast.fantasy.application.repository.UserRepository;
import hr.bornast.fantasy.domain.model.User;
import hr.bornast.fantasy.infrastructure.persistence.entity.UserEntity;
import hr.bornast.fantasy.infrastructure.persistence.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserEntityRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public Page<User> findAll(Pageable paging) {
        return userRepository.findAll(paging).map(x -> mapper.map(x, User.class));
    }

    @Override
    public Page<User> findByUsername(String username, Pageable paging) {
        return userRepository.findByUsernameContainingIgnoreCase(username, paging)
            .map(x -> mapper.map(x, User.class));
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
    public User create(User user) {
        return mapper.map(userRepository.save(mapper.map(user, UserEntity.class)), User.class);
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
