package hr.bornast.fantasy.application.dto.media;

public class EntityMediaDto {
    private int id;
    private String url;
    private boolean isMain;

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
}
