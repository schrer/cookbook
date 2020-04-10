package at.schrer.cookbook.data.converters;

import at.schrer.cookbook.data.dto.CategoryModel;
import at.schrer.cookbook.data.entity.CategoryEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToEntityConverter implements Converter<CategoryModel, CategoryEntity> {

    @Override
    public CategoryEntity convert(CategoryModel source) {
        CategoryEntity target = new CategoryEntity();
        target.setDescription(source.getDescription());
        target.setId(source.getId());
        target.setTitle(source.getTitle());
        return target;
    }
}
