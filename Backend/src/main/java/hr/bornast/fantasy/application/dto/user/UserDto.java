package hr.bornast.fantasy.application.dto.user;

import java.util.Set;

import hr.bornast.fantasy.application.dto.media.EntityMediaDto;
import hr.bornast.fantasy.application.dto.role.RoleDto;
import lombok.Data;

@Data
public class UserDto {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private Set<RoleDto> roles;
    private EntityMediaDto media;
}
