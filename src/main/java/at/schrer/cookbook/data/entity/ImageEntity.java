package at.schrer.cookbook.data.entity;


import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class ImageEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String path;

    private int priority;

    @ManyToOne
    private RecipeEntity recipe;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public RecipeEntity getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeEntity recipe) {
        this.recipe = recipe;
    }
}
