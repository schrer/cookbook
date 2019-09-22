package at.schrer.cookbook.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "Recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    @NotBlank
    private String title;

    @Column(name = "instructions")
    @NotBlank
    private String instructions;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
