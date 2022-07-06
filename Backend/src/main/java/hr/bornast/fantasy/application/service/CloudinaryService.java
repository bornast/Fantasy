package hr.bornast.fantasy.application.service;

import hr.bornast.fantasy.application.dto.media.MediaUploadResultDto;
import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
    MediaUploadResultDto upload(MultipartFile media);
}
