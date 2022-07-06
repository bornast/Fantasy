package hr.bornast.fantasy.infrastructure.cloudinary.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import hr.bornast.fantasy.application.dto.media.MediaUploadResultDto;
import hr.bornast.fantasy.application.service.CloudinaryService;
import hr.bornast.fantasy.common.enums.MediaType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;

    @Override
    public MediaUploadResultDto upload(MultipartFile media) {
        try {
            var result = new MediaUploadResultDto();
            var uploadResult = cloudinary.uploader().upload(media.getBytes(), ObjectUtils.asMap("resource_type", "auto"));

            result.setPublicId(uploadResult.get("public_id").toString());
            result.setUrl(uploadResult.get("url").toString());

            if (uploadResult.get("resource_type").equals("image")) {
                result.setMediaType(MediaType.IMAGE);
            }
            else {
                result.setMediaType(MediaType.VIDEO);
            }

            return result;
        } catch (Exception ex) {
            // TODO: custom exception
            throw new RuntimeException();
        }
    }
}
