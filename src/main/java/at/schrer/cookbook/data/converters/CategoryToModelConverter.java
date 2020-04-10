package at.schrer.cookbook.data.converters;

import at.schrer.cookbook.data.dto.CategoryModel;
import at.schrer.cookbook.data.entity.CategoryEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToModelConverter implements Converter<CategoryEntity, CategoryModel> {

    @Override
    public CategoryModel convert(CategoryEntity source) {
        CategoryModel target = new CategoryModel();
        target.setDescription(source.getDescription());
        target.setId(source.getId());
        target.setTitle(source.getTitle());
        return target;
    }
}
