package hr.bornast.fantasy.api.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import hr.bornast.fantasy.application.command.league.SaveLeagueCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.league.LeagueDto;
import hr.bornast.fantasy.application.query.league.LeagueQuery;
import hr.bornast.fantasy.application.service.LeagueService;
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
@RequestMapping("/api/v1/leagues")
@CrossOrigin
public class LeagueController {

    private final LeagueService leagueService;

    @GetMapping
    public ResponseEntity<PagedListDto<LeagueDto>> findAll(LeagueQuery query) {
        return ok(leagueService.findAll(PageRequest.of(query.getPageNumber(), query.getPageSize()), query.getName()));
    }

    @GetMapping("/record-names")
    public ResponseEntity<List<RecordNameDto>> findAllRecordNames() {
        return ok(leagueService.findAllRecordNames());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeagueDto> findOne(@PathVariable int id) {
        return ok(leagueService.findOne(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<LeagueDto> create(@Valid @RequestBody SaveLeagueCommand command) {
        var league = leagueService.create(command);
        return created(URI.create("/leagues/" + league.getId())).body(league);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<LeagueDto> update(@PathVariable int id, @Valid @RequestBody SaveLeagueCommand command) {
        return ok(leagueService.update(id, command));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        leagueService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
