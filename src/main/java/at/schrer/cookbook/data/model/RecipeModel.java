package at.schrer.cookbook.data.model;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RecipeModel {

    private long id;

    @NotBlank
    private String title;

    @NotBlank
    private String instructions;

    @NotNull
    private long categoryId;

    private String url;

    private String shortDescription;

    private ImageModel image;

    private MultipartFile multiPartImage;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public ImageModel getImage() {
        return image;
    }

    public void setImage(ImageModel image) {
        this.image = image;
    }

    public MultipartFile getMultiPartImage() {
        return multiPartImage;
    }

    public void setMultiPartImage(MultipartFile multiPartImage) {
        this.multiPartImage = multiPartImage;
    }
}
