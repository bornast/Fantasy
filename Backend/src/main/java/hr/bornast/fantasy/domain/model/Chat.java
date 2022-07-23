package hr.bornast.fantasy.domain.model;

import java.util.List;

import lombok.Data;

@Data
public class Chat {
    private int id;
    private String name;
    private Team team;
    private List<Message> messages;
}
