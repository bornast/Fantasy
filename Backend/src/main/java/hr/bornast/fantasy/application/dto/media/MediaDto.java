package hr.bornast.fantasy.application.dto.media;

public class MediaDto {
    private int id;
    private String url;
    private boolean isMain;
    private boolean isApproved;
    private int mediaTypeId;

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
}
