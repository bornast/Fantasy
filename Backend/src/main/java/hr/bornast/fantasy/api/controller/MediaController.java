package hr.bornast.fantasy.api.controller;

import java.util.List;

import javax.validation.Valid;

import hr.bornast.fantasy.application.command.media.MediaApprovalCommand;
import hr.bornast.fantasy.application.command.media.SetMainMediaCommand;
import hr.bornast.fantasy.application.command.media.UploadMediaCommand;
import hr.bornast.fantasy.application.dto.common.PagedListDto;
import hr.bornast.fantasy.application.dto.media.MediaDetailDto;
import hr.bornast.fantasy.application.query.common.PaginationQuery;
import hr.bornast.fantasy.application.query.media.MediaQuery;
import hr.bornast.fantasy.application.service.MediaService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/media")
@CrossOrigin
public class MediaController {

    private final MediaService mediaService;

    @PostMapping
    @PreAuthorize("hasAnyRole('Admin', 'User')")
    public ResponseEntity<MediaDetailDto> upload(UploadMediaCommand command) {
        return ok(mediaService.upload(command));
    }

    @PostMapping("/{id}/setMain")
    public ResponseEntity<Void> setMain(@PathVariable int id, @Valid @RequestBody SetMainMediaCommand command) {
        mediaService.setMain(id, command);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        mediaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/approve")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Void> approve(@PathVariable int id, @Valid @RequestBody MediaApprovalCommand command) {
        mediaService.approve(id, command);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/disapprove")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Void> disapprove(@PathVariable int id, @Valid @RequestBody MediaApprovalCommand command) {
        mediaService.disapprove(id, command);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/approved")
    @PreAuthorize("hasAnyRole('Admin', 'User')")
    public ResponseEntity<PagedListDto<MediaDetailDto>> findApprovedMemories(MediaQuery query) {
        return ok(mediaService.findApprovedMemories(PageRequest.of(query.getPageNumber(), query.getPageSize()), query.getMatchId()));
    }

    @GetMapping("/personal")
    @PreAuthorize("hasAnyRole('Admin', 'User')")
    public ResponseEntity<List<MediaDetailDto>> findPersonalMemories() {
        return ok(mediaService.findPersonalMemories());
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('Admin', 'User')")
    public ResponseEntity<PagedListDto<MediaDetailDto>> findAll(PaginationQuery query) {
        return ok(mediaService.findAllMedia(PageRequest.of(query.getPageNumber(), query.getPageSize())));
    }

}
