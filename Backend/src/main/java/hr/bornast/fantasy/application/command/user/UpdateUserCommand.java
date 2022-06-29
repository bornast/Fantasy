package hr.bornast.fantasy.application.command.user;

import java.util.Set;

import lombok.Data;

@Data
public class UpdateUserCommand {
    private String username;
    private String firstName;
    private String lastName;
    private Set<Integer> roleIds;
}
