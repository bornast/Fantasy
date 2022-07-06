package hr.bornast.fantasy.domain.model;

import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class Media {
    private int id;
    private String url;
    private MediaType mediaType;
    private String description;
    private OffsetDateTime createdAt;
    private boolean isMain;
    private String publicId;
    private EntityType entityType;
    private int entityId;
}
