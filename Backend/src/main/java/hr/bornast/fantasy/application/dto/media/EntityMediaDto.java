package hr.bornast.fantasy.application.dto.media;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class EntityMediaDto {
    private String mainMedia;
    private List<MediaDto> media = new ArrayList<>();

    public void addMedia(MediaDto media) {
        this.media.add(media);
    }
}
