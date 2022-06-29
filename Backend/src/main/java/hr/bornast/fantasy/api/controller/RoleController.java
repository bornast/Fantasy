package hr.bornast.fantasy.api.controller;

import javax.validation.Valid;

import hr.bornast.fantasy.application.command.role.CreateRoleCommand;
import hr.bornast.fantasy.application.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    @PreAuthorize("hasRole('Admin')")
    public void create(@Valid @RequestBody CreateRoleCommand command) {
        roleService.create(command);
    }
}
