package hr.bornast.fantasy.infrastructure.persistence.entity;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "media")
@Data
public class MediaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id", name = "media_type_id")
    private MediaTypeEntity mediaType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id", name = "entity_type_id")
    private EntityTypeEntity entityType;

    private int entityId;

    private String url;

    private String description;

    private OffsetDateTime createdAt;

    private boolean isMain;

    private String publicId;

    @Column(columnDefinition = "boolean default false")
    private boolean isApproved;

    private int uploadedByUserId;
}
