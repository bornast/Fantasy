package hr.bornast.fantasy.application.command.media;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadMediaCommand {
    private MultipartFile file;
    private int entityId;
    private int entityTypeId;
}
