package hr.bornast.fantasy.api.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import hr.bornast.fantasy.application.command.season.CreateSeasonCommand;
import hr.bornast.fantasy.application.command.season.UpdateSeasonCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.season.SeasonDto;
import hr.bornast.fantasy.application.query.season.SeasonQuery;
import hr.bornast.fantasy.application.service.SeasonService;
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
@RequestMapping("/api/v1/seasons")
@CrossOrigin
public class SeasonController {

    private final SeasonService seasonService;

    @GetMapping
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<PagedListDto<SeasonDto>> findAll(SeasonQuery query) {
        return ok(seasonService.findAll(PageRequest.of(query.getPageNumber(), query.getPageSize()), query.getName()));
    }

    @GetMapping("/recordNames")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<RecordNameDto>> findAllRecordNames() {
        return ok(seasonService.findAllRecordNames());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<SeasonDto> findOne(@PathVariable int id) {
        return ok(seasonService.findOne(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<SeasonDto> create(@Valid @RequestBody CreateSeasonCommand command) {
        var season = seasonService.create(command);
        return created(URI.create("/seasons/" + season.getId())).body(season);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        seasonService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<SeasonDto> update(@PathVariable int id, @Valid @RequestBody UpdateSeasonCommand command) {
        return ok(seasonService.update(id, command));
    }

}
