package hr.bornast.fantasy.api.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import hr.bornast.fantasy.application.command.coach.CreateCoachCommand;
import hr.bornast.fantasy.application.command.coach.UpdateCoachCommand;
import hr.bornast.fantasy.application.dto.coach.CoachDto;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.query.coach.CoachQuery;
import hr.bornast.fantasy.application.service.CoachService;
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
@RequestMapping("/api/v1/coaches")
@CrossOrigin
public class CoachController {

    private final CoachService coachService;

    @GetMapping
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<PagedListDto<CoachDto>> findAll(CoachQuery query) {
        return ok(coachService.findAll(PageRequest.of(query.getPageNumber(), query.getPageSize()), query.getName()));
    }

    @GetMapping("/record-names")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<RecordNameDto>> findAllRecordNames() {
        return ok(coachService.findAllRecordNames());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<CoachDto> findOne(@PathVariable int id) {
        return ok(coachService.findOne(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<CoachDto> create(@Valid @RequestBody CreateCoachCommand command) {
        var coach = coachService.create(command);
        return created(URI.create("/coaches/" + coach.getId())).body(coach);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        coachService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<CoachDto> update(@PathVariable int id, @Valid @RequestBody UpdateCoachCommand command) {
        return ok(coachService.update(id, command));
    }

}
