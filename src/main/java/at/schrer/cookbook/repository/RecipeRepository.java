package at.schrer.cookbook.repository;

import at.schrer.cookbook.data.entity.CategoryEntity;
import at.schrer.cookbook.data.entity.RecipeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<RecipeEntity, Long> {

    List<RecipeEntity> findRecipesByCategory(CategoryEntity category);
}