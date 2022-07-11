package hr.bornast.fantasy.api.controller;

import java.util.List;

import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.application.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cards")
@CrossOrigin
public class CardController {

    private final CardService cardService;

    @GetMapping("/record-names")
    public ResponseEntity<List<RecordNameDto>> findAllRecordNames() {
        return ok(cardService.findAllRecordNames());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecordNameDto> findOne(@PathVariable int id) {
        return ok(cardService.findOne(id));
    }

}
