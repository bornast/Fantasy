package hr.bornast.fantasy.api.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import hr.bornast.fantasy.application.command.auth.RegisterCommand;
import hr.bornast.fantasy.application.command.user.UpdateUserCommand;
import hr.bornast.fantasy.application.dto.user.UserDto;
import hr.bornast.fantasy.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<UserDto>> findAll() {
        return ok(userService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<UserDto> findOne(@PathVariable int id) {
        return ok(userService.findOne(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('Admin')")
    public void create(@Valid @RequestBody RegisterCommand command) {
        userService.create(command);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public void delete(@PathVariable int id) {
        userService.delete(id);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<UserDto> update(@PathVariable int id, @Valid @RequestBody UpdateUserCommand command) {
        return ok(userService.update(id, command));
    }

    @PostConstruct
    public void initRolesAndUsers() {
        userService.initRolesAndUser();
    }

}
