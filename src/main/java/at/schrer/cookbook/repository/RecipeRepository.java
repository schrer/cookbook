package at.schrer.cookbook.repository;

import at.schrer.cookbook.entity.Category;
import at.schrer.cookbook.entity.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    List<Recipe> findRecipesByCategory(Category category);
}