package hr.bornast.fantasy.application.mapper.impl;

import hr.bornast.fantasy.application.command.auth.RegisterCommand;
import hr.bornast.fantasy.application.command.user.UpdateUserCommand;
import hr.bornast.fantasy.application.dto.user.UserDto;
import hr.bornast.fantasy.application.mapper.UserMapper;
import hr.bornast.fantasy.application.repository.RoleRepository;
import hr.bornast.fantasy.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    private final ModelMapper mapper;
    private final RoleRepository roleRepository;

    @Override
    public UserDto map(User user) {
        return mapper.map(user, UserDto.class);
    }

    @Override
    public void map(UpdateUserCommand command, User user) {
        mapper.map(command, user);

        // remove roles
        var rolesToRemove = user.getRoles().stream()
            .filter(x -> !command.getRoleIds().contains(x.getId()))
            .toList();

        for (var roleToRemove : rolesToRemove) {
            user.getRoles().remove(roleToRemove);
        }

        // add roles
        var rolesToAdd = command.getRoleIds().stream()
            .filter(roleId -> user.getRoles().stream().noneMatch(x -> x.getId() == roleId)).toList();

        // TODO: find roles by ids
        for (var roleToAdd : rolesToAdd) {
            // TODO: custom exception
            var roleToInsert = roleRepository.findById(roleToAdd).orElseThrow(RuntimeException::new);
            user.addRole(roleToInsert);
        }

    }

    @Override
    public User map(RegisterCommand command) {
        return mapper.map(command, User.class);
    }
}
