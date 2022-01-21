package at.schrer.cookbook.service;

import at.schrer.cookbook.data.model.CategoryModel;
import at.schrer.cookbook.data.model.RecipeModel;
import at.schrer.cookbook.data.entity.CategoryEntity;
import at.schrer.cookbook.data.entity.RecipeEntity;
import at.schrer.cookbook.repository.RecipeRepository;
import at.schrer.cookbook.search.HibernateSearchService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static at.schrer.cookbook.CookbookConfig.COOOKBOOK_CONVERTER_BEAN_NAME;

@Service
public class RecipeService {

    @Value("${cookbook.paging.size:0}")
    private Integer pagingSize;

    private final RecipeRepository recipeRepository;
    private final ConversionService converter;
    private final HibernateSearchService searchService;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, @Qualifier(COOOKBOOK_CONVERTER_BEAN_NAME) ConversionService converter, HibernateSearchService searchService) {
        this.recipeRepository = recipeRepository;
        this.converter = converter;
        this.searchService = searchService;
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
        return convertRecipeEntities(recipeEntities);
    }

    public Pair<List<RecipeModel>, Integer> getRecipesByCategoryPaged(CategoryModel categoryModel, Integer page) {
        int baseMultiplier = (page == null || page <= 1) ? 0 : page - 1;

        List<RecipeEntity> recipeEntities = recipeRepository.findRecipeEntitiesByCategory(converter.convert(categoryModel, CategoryEntity.class));

        if (CollectionUtils.isEmpty(recipeEntities)) {
            return new ImmutablePair<>(Collections.emptyList(), 0);
        }

        int numberOfPages = (int) Math.ceil(CollectionUtils.size(recipeEntities) * 1d / pagingSize.doubleValue());

        int firstRecipe = baseMultiplier * pagingSize;
        int nextPageRecipe = (baseMultiplier + 1) * pagingSize;

        if (numberOfPages <= baseMultiplier) {
            firstRecipe = baseMultiplier * pagingSize;
            nextPageRecipe = CollectionUtils.size(recipeEntities);
        }

        List<RecipeModel> recipeModels = convertRecipeEntities(recipeEntities.subList(firstRecipe, nextPageRecipe));
        return new ImmutablePair<>(recipeModels, numberOfPages);
    }

    public List<RecipeModel> getRecipesByCategory(CategoryModel categoryModel) {

        List<RecipeEntity> recipeEntities = recipeRepository.findRecipeEntitiesByCategory(converter.convert(categoryModel, CategoryEntity.class));
        return convertRecipeEntities(recipeEntities);
    }

    public Optional<RecipeModel> getRecipeById(long id) {
        Optional<RecipeEntity> optionalEntity = recipeRepository.findById(id);
        return optionalEntity.map(recipeEntity -> converter.convert(recipeEntity, RecipeModel.class));
    }

    public List<RecipeModel> searchRecipesByTitle(String query){
        List<RecipeEntity> recipeEntities = searchService.searchRecipesByFuzzyTitle(query);
        return convertRecipeEntities(recipeEntities);
    }

    private List<RecipeModel> convertRecipeEntities(Iterable<RecipeEntity> entities){
        List<RecipeModel> models = new LinkedList<>();

        for (RecipeEntity entity: entities){
            models.add(converter.convert(entity, RecipeModel.class));
        }
        return models;
    }

}
