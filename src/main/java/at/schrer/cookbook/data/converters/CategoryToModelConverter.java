package at.schrer.cookbook.data.converters;

import at.schrer.cookbook.data.dto.CategoryModel;
import at.schrer.cookbook.data.entity.CategoryEntity;
import at.schrer.cookbook.frontend.util.UrlResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToModelConverter implements Converter<CategoryEntity, CategoryModel> {

    private UrlResolver urlResolver;

    @Autowired
    public CategoryToModelConverter (UrlResolver urlResolver){
        this.urlResolver = urlResolver;
    }

    @Override
    public CategoryModel convert(CategoryEntity source) {
        CategoryModel target = new CategoryModel();
        target.setDescription(source.getDescription());
        target.setId(source.getId());
        target.setTitle(source.getTitle());
        target.setUrl(urlResolver.resolve(target));
        return target;
    }
}
