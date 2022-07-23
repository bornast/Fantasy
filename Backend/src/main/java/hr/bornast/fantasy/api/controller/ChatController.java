package hr.bornast.fantasy.api.controller;

import hr.bornast.fantasy.application.dto.chat.ChatDto;
import hr.bornast.fantasy.application.dto.chat.MessageDto;
import hr.bornast.fantasy.application.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class ChatController {

    private final ChatService chatService;

    @MessageMapping("/resume/{teamId}")
    @SendTo("/start/initial/{teamId}")
    public MessageDto chat(@Header("Authorization") String token, @DestinationVariable int teamId, String msg) {
        System.out.println("handling send message: " + msg + " to: " + teamId);
        return chatService.addMessage(teamId, msg, token);
    }

    @GetMapping("/api/v1/chats/{teamId}")
    @PreAuthorize("hasAnyRole('Admin', 'User')")
    public ResponseEntity<ChatDto> findOne(@PathVariable int teamId) {
        return ok(chatService.findOne(teamId));
    }

}
