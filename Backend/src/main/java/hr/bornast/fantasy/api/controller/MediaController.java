package hr.bornast.fantasy.api.controller;

import javax.validation.Valid;

import hr.bornast.fantasy.application.command.media.SetMainMediaCommand;
import hr.bornast.fantasy.application.command.media.UploadMediaCommand;
import hr.bornast.fantasy.application.dto.media.MediaDto;
import hr.bornast.fantasy.application.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public ResponseEntity<MediaDto> upload(UploadMediaCommand command) {
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

}
