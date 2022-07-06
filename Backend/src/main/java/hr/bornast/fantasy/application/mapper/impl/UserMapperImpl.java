package hr.bornast.fantasy.application.mapper.impl;

import hr.bornast.fantasy.application.command.auth.RegisterCommand;
import hr.bornast.fantasy.application.command.user.UpdateUserCommand;
import hr.bornast.fantasy.application.dto.user.UserDto;
import hr.bornast.fantasy.application.mapper.UserMapper;
import hr.bornast.fantasy.application.repository.RoleRepository;
import hr.bornast.fantasy.application.service.MediaService;
import hr.bornast.fantasy.common.enums.EntityType;
import hr.bornast.fantasy.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    private final ModelMapper mapper;
    private final RoleRepository roleRepository;
    private final MediaService mediaService;

    @Override
    public UserDto map(User user) {
        var result = mapper.map(user, UserDto.class);
        result.setMedia(mediaService.getEntityMedia(result.getId(), EntityType.USER.getValue()));
        return result;
    }

    @Override
    public User map(RegisterCommand command) {
        return mapper.map(command, User.class);
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
        var rolesToAddIds = command.getRoleIds().stream()
            .filter(roleId -> user.getRoles().stream().noneMatch(x -> x.getId() == roleId)).toList();

        roleRepository.findByIds(rolesToAddIds).forEach(user::addRole);

    }
}
