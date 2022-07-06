package hr.bornast.fantasy.application.dto.media;

import hr.bornast.fantasy.common.enums.MediaType;
import lombok.Data;

@Data
public class MediaUploadResultDto {
    private String url;
    private String publicId;
    private MediaType mediaType;
}
