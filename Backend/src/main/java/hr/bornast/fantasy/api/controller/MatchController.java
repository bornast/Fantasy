package hr.bornast.fantasy.api.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import hr.bornast.fantasy.application.command.match.SaveMatchCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.match.MatchDto;
import hr.bornast.fantasy.application.query.match.MatchQuery;
import hr.bornast.fantasy.application.service.MatchService;
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
@RequestMapping("/api/v1/matches")
@CrossOrigin
public class MatchController {

    private final MatchService matchService;

    @GetMapping
    public ResponseEntity<PagedListDto<MatchDto>> findAll(MatchQuery query) {
        return ok(matchService.findAll(PageRequest.of(query.getPageNumber(), query.getPageSize()), query.getTeamName()));
    }

    @GetMapping("/record-names")
    public ResponseEntity<List<RecordNameDto>> findAllRecordNames() {
        return ok(matchService.findAllRecordNames());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchDto> findOne(@PathVariable int id) {
        return ok(matchService.findOne(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<MatchDto> create(@Valid @RequestBody SaveMatchCommand command) {
        var match = matchService.create(command);
        return created(URI.create("/matches/" + match.getId())).body(match);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<MatchDto> update(@PathVariable int id, @Valid @RequestBody SaveMatchCommand command) {
        return ok(matchService.update(id, command));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        matchService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
