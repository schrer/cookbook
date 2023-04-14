package at.schrer.cookbook.data.model;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public class ImageModel {

    @NotBlank
    private UUID id;
    private String url;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
