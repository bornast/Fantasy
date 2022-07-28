package hr.bornast.fantasy.api.controller;

import java.net.URI;

import javax.validation.Valid;

import hr.bornast.fantasy.application.command.rate.SaveRateCommand;
import hr.bornast.fantasy.application.command.rate.UpdateRateCommand;
import hr.bornast.fantasy.application.dto.rate.RateDto;
import hr.bornast.fantasy.application.query.rate.RateQuery;
import hr.bornast.fantasy.application.service.RateService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/v1/rates")
@CrossOrigin
public class RateController {

    private final RateService rateService;

    @GetMapping
    @PreAuthorize("hasAnyRole('Admin', 'User')")
    public ResponseEntity<RateDto> findOne(RateQuery query) {
        return ok(rateService.findOne(query));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('Admin', 'User')")
    public ResponseEntity<RateDto> create(@Valid @RequestBody SaveRateCommand command) {
        var rate = rateService.create(command);
        return created(URI.create("/rates/" + rate.getId())).body(rate);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('Admin', 'User')")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        rateService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('Admin', 'User')")
    public ResponseEntity<RateDto> update(@PathVariable int id, @Valid @RequestBody UpdateRateCommand command) {
        return ok(rateService.update(id, command));
    }

}
