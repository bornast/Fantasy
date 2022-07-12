package hr.bornast.fantasy.api.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import hr.bornast.fantasy.application.command.team.SaveTeamCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.team.TeamDto;
import hr.bornast.fantasy.application.query.team.TeamQuery;
import hr.bornast.fantasy.application.service.TeamService;
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
@RequestMapping("/api/v1/teams")
@CrossOrigin
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    public ResponseEntity<PagedListDto<TeamDto>> findAll(TeamQuery query) {
        return ok(teamService.findAll(PageRequest.of(query.getPageNumber(), query.getPageSize()), query.getName()));
    }

    @GetMapping("/record-names")
    public ResponseEntity<List<RecordNameDto>> findAllRecordNames() {
        return ok(teamService.findAllRecordNames());
    }

    @GetMapping("/{id}/players")
    public ResponseEntity<List<RecordNameDto>> findTeamPlayers(@PathVariable int id) {
        return ok(teamService.findTeamPlayers(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDto> findOne(@PathVariable int id) {
        return ok(teamService.findOne(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<TeamDto> create(@Valid @RequestBody SaveTeamCommand command) {
        var team = teamService.create(command);
        return created(URI.create("/teams/" + team.getId())).body(team);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<TeamDto> update(@PathVariable int id, @Valid @RequestBody SaveTeamCommand command) {
        return ok(teamService.update(id, command));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        teamService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/set-favourite")
    @PreAuthorize("hasAnyRole('Admin', 'User')")
    public ResponseEntity<Void> setFavourite(@PathVariable int id) {
        teamService.setFavourite(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/set-unfavored")
    @PreAuthorize("hasAnyRole('Admin', 'User')")
    public ResponseEntity<Void> setUnfavored(@PathVariable int id) {
        teamService.setUnfavored(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/favourites")
    @PreAuthorize("hasAnyRole('Admin', 'User')")
    public ResponseEntity<PagedListDto<TeamDto>> findFavouriteTeams(TeamQuery query) {
        return ok(teamService.findFavourites(PageRequest.of(query.getPageNumber(), query.getPageSize()), query.getName()));
    }

    @GetMapping("/unfavored")
    @PreAuthorize("hasAnyRole('Admin', 'User')")
    public ResponseEntity<PagedListDto<TeamDto>> findUnfavoredTeams(TeamQuery query) {
        return ok(teamService.findUnfavored(PageRequest.of(query.getPageNumber(), query.getPageSize()), query.getName()));
    }
}
