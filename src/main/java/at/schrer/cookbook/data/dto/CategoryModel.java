package at.schrer.cookbook.data.dto;

import javax.validation.constraints.NotBlank;

public class CategoryModel {

    private long id;

    @NotBlank
    private String title;

    private String description;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
