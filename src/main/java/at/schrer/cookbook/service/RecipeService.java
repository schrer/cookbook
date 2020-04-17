package at.schrer.cookbook.service;

import at.schrer.cookbook.data.dto.CategoryModel;
import at.schrer.cookbook.data.dto.RecipeModel;
import at.schrer.cookbook.data.entity.CategoryEntity;
import at.schrer.cookbook.data.entity.RecipeEntity;
import at.schrer.cookbook.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static at.schrer.cookbook.CookbookConfig.COOOKBOOK_CONVERTER_BEAN_NAME;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final ConversionService converter;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, @Qualifier(COOOKBOOK_CONVERTER_BEAN_NAME) ConversionService converter) {
        this.recipeRepository = recipeRepository;
        this.converter = converter;
    }

    public RecipeModel saveRecipe(RecipeModel recipe) {
        RecipeEntity entity = converter.convert(recipe, RecipeEntity.class);
        if (entity == null) {
            return null;
        }

        entity = recipeRepository.save(entity);
        return converter.convert(entity, RecipeModel.class);
    }

    public List<RecipeModel> getAllRecipes(){
        Iterable<RecipeEntity> recipeEntities = recipeRepository.findAll();
        List<RecipeModel> recipeModels = new LinkedList<>();

        recipeEntities.
                forEach(
                        recipeEntity -> recipeModels.add(converter.convert(recipeEntity, RecipeModel.class))
                );

        return recipeModels;
    }

    public List<RecipeModel> getRecipesByCategory(CategoryModel categoryModel) {

        List<RecipeEntity> recipeEntities = recipeRepository.findRecipesByCategory(converter.convert(categoryModel, CategoryEntity.class));
        List<RecipeModel> recipeModels = new LinkedList<>();

        recipeEntities.
                forEach(
                        recipeEntity -> recipeModels.add(converter.convert(recipeEntity, RecipeModel.class))
                );

        return recipeModels;
    }

    public Optional<RecipeModel> getRecipeById(long id) {
        Optional<RecipeEntity> optionalEntity = recipeRepository.findById(id);
        return optionalEntity.map(recipeEntity -> converter.convert(recipeEntity, RecipeModel.class));
    }

}
