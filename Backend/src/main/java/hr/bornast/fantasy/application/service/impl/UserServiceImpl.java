package hr.bornast.fantasy.application.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import hr.bornast.fantasy.application.command.auth.RegisterCommand;
import hr.bornast.fantasy.application.repository.RoleRepository;
import hr.bornast.fantasy.application.repository.UserRepository;
import hr.bornast.fantasy.application.service.UserService;
import hr.bornast.fantasy.infrastructure.persistence.entity.RoleEntity;
import hr.bornast.fantasy.infrastructure.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    public void create(RegisterCommand command) {
        var user = mapper.map(command, UserEntity.class);

        var role = roleRepository.findByName("User");

        Set<RoleEntity> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        user.setPassword(getEncodedPassword(user.getPassword()));

        userRepository.create(user);
    }

    @Transactional
    public void initRolesAndUser() {
        var adminRole = new RoleEntity();
        adminRole.setName("Admin");
        var adminRoleCreated = roleRepository.create(adminRole);

        var userRole = new RoleEntity();
        userRole.setName("User");
        var userRoleCreated = roleRepository.create(userRole);

        var adminUser = new UserEntity();
        adminUser.setFirstName("admin");
        adminUser.setLastName("admin");
        adminUser.setUsername("admin123");
        adminUser.setPassword(getEncodedPassword("123"));
        Set<RoleEntity> adminRoles = new HashSet<>();
        adminRoles.add(adminRoleCreated);
        adminUser.setRoles(adminRoles);
        userRepository.create(adminUser);

        var user = new UserEntity();
        user.setFirstName("user");
        user.setLastName("user");
        user.setUsername("user123");
        user.setPassword(getEncodedPassword("123"));
        Set<RoleEntity> userRoles = new HashSet<>();
        userRoles.add(userRoleCreated);
        user.setRoles(userRoles);
        userRepository.create(user);
    }

    private String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
