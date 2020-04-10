package at.schrer.cookbook.data.converters;

import at.schrer.cookbook.data.dto.RecipeModel;
import at.schrer.cookbook.data.entity.RecipeEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeToModelConverter implements Converter<RecipeEntity, RecipeModel> {

    @Override
    public RecipeModel convert(RecipeEntity source) {
        var target = new RecipeModel();

        target.setId(source.getId());
        target.setInstructions(source.getInstructions());
        target.setTitle(source.getTitle());
        target.setCategoryId(source.getCategory().getId());
        return target;
    }
}
