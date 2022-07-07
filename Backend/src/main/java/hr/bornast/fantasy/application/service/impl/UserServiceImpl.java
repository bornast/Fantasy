package hr.bornast.fantasy.application.service.impl;

import java.util.HashSet;
import java.util.Set;

import hr.bornast.fantasy.application.command.auth.RegisterCommand;
import hr.bornast.fantasy.application.command.user.UpdateUserCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.user.UserDto;
import hr.bornast.fantasy.application.mapper.UserMapper;
import hr.bornast.fantasy.application.repository.EntityTypeRepository;
import hr.bornast.fantasy.application.repository.MediaTypeRepository;
import hr.bornast.fantasy.application.repository.RoleRepository;
import hr.bornast.fantasy.application.repository.UserRepository;
import hr.bornast.fantasy.application.service.UserService;
import hr.bornast.fantasy.common.exception.EntityNotFoundException;
import hr.bornast.fantasy.common.exception.ValidationException;
import hr.bornast.fantasy.domain.model.EntityType;
import hr.bornast.fantasy.domain.model.MediaType;
import hr.bornast.fantasy.domain.model.Role;
import hr.bornast.fantasy.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;
    // TODO: remove asap
    private final MediaTypeRepository mediaTypeRepository;
    private final EntityTypeRepository entityTypeRepository;

    @Override
    public PagedListDto<UserDto> findAll(Pageable paging, String username) {
        if (username == null) {
            return new PagedListDto<UserDto>().getPagedResult(
                userRepository.findAll(paging)
                    .map(mapper::map));
        }

        return new PagedListDto<UserDto>().getPagedResult(
            userRepository.findByUsername(username, paging)
                .map(mapper::map));
    }

    @Override
    public UserDto findOne(int id) {
        return userRepository.findById(id)
            .map(mapper::map)
            .orElseThrow(EntityNotFoundException::new);
    }

    public UserDto create(RegisterCommand command) {
        userRepository.findByUsername(command.getUsername())
            .ifPresent(r -> {throw new ValidationException("User", "user " + command.getUsername() + " already exists");});

        var user = mapper.map(command);

        var role = roleRepository.findByName("User").orElseThrow(RuntimeException::new);

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        user.setPassword(getEncodedPassword(user.getPassword()));

        return mapper.map(userRepository.create(user));
    }

    @Override
    public UserDto update(int id, UpdateUserCommand command) {
        var user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
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

        var entityType = new EntityType();
        entityType.setName("user");
        entityTypeRepository.create(entityType);

        var entityType1 = new EntityType();
        entityType1.setName("player");
        entityTypeRepository.create(entityType1);

        var entityType2 = new EntityType();
        entityType2.setName("team");
        entityTypeRepository.create(entityType2);

        var mediaType = new MediaType();
        mediaType.setName("image");
        mediaTypeRepository.create(mediaType);

        var mediaType1 = new MediaType();
        mediaType1.setName("video");
        mediaTypeRepository.create(mediaType1);
    }

    private String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
