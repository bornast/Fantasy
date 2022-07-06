package hr.bornast.fantasy.api.controller;

import hr.bornast.fantasy.application.command.media.UploadMediaCommand;
import hr.bornast.fantasy.application.dto.media.MediaDto;
import hr.bornast.fantasy.application.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
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

}
