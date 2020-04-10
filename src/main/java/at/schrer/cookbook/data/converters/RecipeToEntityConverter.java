package at.schrer.cookbook.data.converters;

import at.schrer.cookbook.data.dto.RecipeModel;
import at.schrer.cookbook.data.entity.CategoryEntity;
import at.schrer.cookbook.data.entity.RecipeEntity;
import at.schrer.cookbook.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeToEntityConverter implements Converter<RecipeModel, RecipeEntity> {

    private CategoryRepository categoryRepository;

    @Autowired
    public RecipeToEntityConverter(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public RecipeEntity convert(RecipeModel source) {
        var target = new RecipeEntity();
        target.setId(source.getId());
        target.setCategory(this.getCategory(source.getCategoryId()));
        target.setInstructions(source.getInstructions());
        target.setTitle(source.getTitle());
        return target;
    }

    private CategoryEntity getCategory(long id){
        return categoryRepository.findById(id).orElse(null);
    }
}
