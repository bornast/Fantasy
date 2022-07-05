package hr.bornast.fantasy.application.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import hr.bornast.fantasy.application.command.auth.RegisterCommand;
import hr.bornast.fantasy.application.command.user.UpdateUserCommand;
import hr.bornast.fantasy.application.dto.user.UserDto;
import hr.bornast.fantasy.application.mapper.UserMapper;
import hr.bornast.fantasy.application.repository.RoleRepository;
import hr.bornast.fantasy.application.repository.UserRepository;
import hr.bornast.fantasy.application.service.UserService;
import hr.bornast.fantasy.common.exception.EntityNotFoundException;
import hr.bornast.fantasy.common.exception.ValidationException;
import hr.bornast.fantasy.domain.model.Role;
import hr.bornast.fantasy.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
            .stream()
            .map(mapper::map)
            .toList();
    }

    @Override
    public UserDto findOne(int id) {
        // TODO: custom exception
        return userRepository.findById(id)
            .map(mapper::map)
            .orElseThrow(RuntimeException::new);
    }

    public UserDto create(RegisterCommand command) {
        userRepository.findByUsername(command.getUsername())
            .ifPresent(r -> {throw new ValidationException("User", "user " + command.getUsername() + " already exists");});

        var user = mapper.map(command);

        var role = roleRepository.findByName("User").orElseThrow(EntityNotFoundException::new);

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        user.setPassword(getEncodedPassword(user.getPassword()));

        return mapper.map(userRepository.create(user));
    }

    @Override
    public UserDto update(int id, UpdateUserCommand command) {
        // TODO: custom exception
        var user = userRepository.findById(id).orElseThrow(RuntimeException::new);

        mapper.map(command, user);

        return mapper.map(userRepository.update(user));
    }

    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }

    // TODO: this is temporary
//    @Transactional
    public void initRolesAndUser() {
        var adminRole = new Role();
        adminRole.setName("Admin");
        var adminRoleCreated = roleRepository.create(adminRole);

        var userRole = new Role();
        userRole.setName("User");
        var userRoleCreated = roleRepository.create(userRole);

        var adminUser = new User();
        adminUser.setFirstName("admin");
        adminUser.setLastName("admin");
        adminUser.setUsername("admin");
        adminUser.setPassword(getEncodedPassword("admin"));
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRoleCreated);
        adminUser.setRoles(adminRoles);
        userRepository.create(adminUser);

        var user = new User();
        user.setFirstName("user");
        user.setLastName("user");
        user.setUsername("user");
        user.setPassword(getEncodedPassword("user"));
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRoleCreated);
        user.setRoles(userRoles);
        userRepository.create(user);
    }

    private String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
