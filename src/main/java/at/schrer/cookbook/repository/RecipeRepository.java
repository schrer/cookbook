package at.schrer.cookbook.repository;

import at.schrer.cookbook.entity.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
