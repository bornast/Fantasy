package hr.bornast.fantasy.api.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import hr.bornast.fantasy.application.command.stadium.CreateStadiumCommand;
import hr.bornast.fantasy.application.command.stadium.UpdateStadiumCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.stadium.StadiumDto;
import hr.bornast.fantasy.application.query.stadium.StadiumQuery;
import hr.bornast.fantasy.application.service.StadiumService;
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
@RequestMapping("/api/v1/stadiums")
@CrossOrigin
public class StadiumController {

    private final StadiumService stadiumService;

    @GetMapping
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<PagedListDto<StadiumDto>> findAll(StadiumQuery query) {
        return ok(stadiumService.findAll(PageRequest.of(query.getPageNumber(), query.getPageSize()), query.getName()));
    }

    @GetMapping("/record-names")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<RecordNameDto>> findAllRecordNames() {
        return ok(stadiumService.findAllRecordNames());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<StadiumDto> findOne(@PathVariable int id) {
        return ok(stadiumService.findOne(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<StadiumDto> create(@Valid @RequestBody CreateStadiumCommand command) {
        var stadium = stadiumService.create(command);
        return created(URI.create("/stadiums/" + stadium.getId())).body(stadium);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        stadiumService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<StadiumDto> update(@PathVariable int id, @Valid @RequestBody UpdateStadiumCommand command) {
        return ok(stadiumService.update(id, command));
    }

}
