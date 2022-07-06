package hr.bornast.fantasy.api.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import hr.bornast.fantasy.application.command.president.CreatePresidentCommand;
import hr.bornast.fantasy.application.command.president.UpdatePresidentCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.president.PresidentDto;
import hr.bornast.fantasy.application.query.president.PresidentQuery;
import hr.bornast.fantasy.application.service.PresidentService;
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
@RequestMapping("/api/v1/presidents")
@CrossOrigin
public class PresidentController {

    private final PresidentService presidentService;

    @GetMapping
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<PagedListDto<PresidentDto>> findAll(PresidentQuery query) {
        return ok(presidentService.findAll(PageRequest.of(query.getPageNumber(), query.getPageSize()), query.getName()));
    }

    @GetMapping("/record-names")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<RecordNameDto>> findAllRecordNames() {
        return ok(presidentService.findAllRecordNames());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<PresidentDto> findOne(@PathVariable int id) {
        return ok(presidentService.findOne(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<PresidentDto> create(@Valid @RequestBody CreatePresidentCommand command) {
        var president = presidentService.create(command);
        return created(URI.create("/presidents/" + president.getId())).body(president);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        presidentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<PresidentDto> update(@PathVariable int id, @Valid @RequestBody UpdatePresidentCommand command) {
        return ok(presidentService.update(id, command));
    }

}
