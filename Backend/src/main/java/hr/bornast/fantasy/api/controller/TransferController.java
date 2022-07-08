package hr.bornast.fantasy.api.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import hr.bornast.fantasy.application.command.transfer.SaveTransferCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.dto.transfer.TransferDto;
import hr.bornast.fantasy.application.query.transfer.TransferQuery;
import hr.bornast.fantasy.application.service.TransferService;
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
@RequestMapping("/api/v1/transfers")
@CrossOrigin
public class TransferController {

    private final TransferService transferService;

    @GetMapping
    public ResponseEntity<PagedListDto<TransferDto>> findAll(TransferQuery query) {
        return ok(transferService.findAll(PageRequest.of(query.getPageNumber(), query.getPageSize()), query.getPlayerName()));
    }

    @GetMapping("/record-names")
    public ResponseEntity<List<RecordNameDto>> findAllRecordNames() {
        return ok(transferService.findAllRecordNames());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferDto> findOne(@PathVariable int id) {
        return ok(transferService.findOne(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<TransferDto> create(@Valid @RequestBody SaveTransferCommand command) {
        var transfer = transferService.create(command);
        return created(URI.create("/transfers/" + transfer.getId())).body(transfer);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<TransferDto> update(@PathVariable int id, @Valid @RequestBody SaveTransferCommand command) {
        return ok(transferService.update(id, command));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        transferService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
