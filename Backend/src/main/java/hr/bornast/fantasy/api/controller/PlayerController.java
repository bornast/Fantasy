package hr.bornast.fantasy.api.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import hr.bornast.fantasy.application.command.player.CreatePlayerCommand;
import hr.bornast.fantasy.application.command.player.UpdatePlayerCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.player.PlayerDto;
import hr.bornast.fantasy.application.query.player.PlayerQuery;
import hr.bornast.fantasy.application.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/players")
@CrossOrigin
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping
    public ResponseEntity<PagedListDto<PlayerDto>> findAll(PlayerQuery query) {
        return ok(playerService.findAll(PageRequest.of(query.getPageNumber(), query.getPageSize()), query.getName()));
    }

    @GetMapping("/record-names")
    public ResponseEntity<List<RecordNameDto>> findAllRecordNames() {
        return ok(playerService.findAllRecordNames());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDto> findOne(@PathVariable int id) {
        return ok(playerService.findOne(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<PlayerDto> create(@Valid @RequestBody CreatePlayerCommand command) {
        var player = playerService.create(command);
        return created(URI.create("/players/" + player.getId())).body(player);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerDto> update(@PathVariable int id, @Valid @RequestBody UpdatePlayerCommand command) {
        return ok(playerService.update(id, command));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        playerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
