package at.schrer.cookbook.data.dto;

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

}