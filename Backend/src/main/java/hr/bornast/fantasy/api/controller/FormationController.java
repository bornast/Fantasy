package hr.bornast.fantasy.api.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import hr.bornast.fantasy.application.command.formation.CreateFormationCommand;
import hr.bornast.fantasy.application.command.formation.UpdateFormationCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.formation.FormationDto;
import hr.bornast.fantasy.application.query.formation.FormationQuery;
import hr.bornast.fantasy.application.service.FormationService;
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
@RequestMapping("/api/v1/formations")
@CrossOrigin
public class FormationController {

    private final FormationService formationService;

    @GetMapping
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<PagedListDto<FormationDto>> findAll(FormationQuery query) {
        return ok(formationService.findAll(PageRequest.of(query.getPageNumber(), query.getPageSize()), query.getName()));
    }

    @GetMapping("/record-names")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<RecordNameDto>> findAllRecordNames() {
        return ok(formationService.findAllRecordNames());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<FormationDto> findOne(@PathVariable int id) {
        return ok(formationService.findOne(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<FormationDto> create(@Valid @RequestBody CreateFormationCommand command) {
        var formation = formationService.create(command);
        return created(URI.create("/formations/" + formation.getId())).body(formation);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        formationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<FormationDto> update(@PathVariable int id, @Valid @RequestBody UpdateFormationCommand command) {
        return ok(formationService.update(id, command));
    }

}
