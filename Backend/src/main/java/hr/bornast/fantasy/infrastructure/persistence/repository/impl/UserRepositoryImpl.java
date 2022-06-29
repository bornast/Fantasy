package hr.bornast.fantasy.infrastructure.persistence.repository.impl;

import hr.bornast.fantasy.application.repository.UserRepository;
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
    public void create(UserEntity user) {
        userRepository.findByUsername(user.getUsername())
            .ifPresent(r -> {throw new RuntimeException("user " + user.getUsername() + " already exists");});

        userRepository.save(user);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("user with username " + username + " not found!"));
    }
}
