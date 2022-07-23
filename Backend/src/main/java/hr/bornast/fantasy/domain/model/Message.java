package hr.bornast.fantasy.domain.model;

import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class Message {
    private int id;
    private Chat chat;
    private User user;
    private String content;
    private OffsetDateTime time;
}
