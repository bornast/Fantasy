package hr.bornast.fantasy.application.dto.media;

public class MediaDetailDto {
    private int id;
    private String url;
    private boolean isMain;
    private int entityTypeId;
    private int mediaTypeId;
    private boolean isApproved;
    private int uploadedByUserId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean getIsMain() {
        return isMain;
    }

    public void setIsMain(boolean main) {
        isMain = main;
    }

    public int getEntityTypeId() {
        return entityTypeId;
    }

    public void setEntityTypeId(int entityTypeId) {
        this.entityTypeId = entityTypeId;
    }

    public int getMediaTypeId() {
        return mediaTypeId;
    }

    public void setMediaTypeId(int mediaTypeId) {
        this.mediaTypeId = mediaTypeId;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public int getUploadedByUserId() {
        return uploadedByUserId;
    }

    public void setUploadedByUserId(int uploadedByUserId) {
        this.uploadedByUserId = uploadedByUserId;
    }
}
