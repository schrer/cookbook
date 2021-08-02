package at.schrer.cookbook.data.converters;

import at.schrer.cookbook.data.entity.ImageEntity;
import at.schrer.cookbook.data.model.ImageModel;
import at.schrer.cookbook.data.model.RecipeModel;
import at.schrer.cookbook.data.entity.CategoryEntity;
import at.schrer.cookbook.data.entity.RecipeEntity;
import at.schrer.cookbook.repository.CategoryRepository;
import at.schrer.cookbook.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class RecipeToEntityConverter implements Converter<RecipeModel, RecipeEntity> {

    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;

    @Autowired
    public RecipeToEntityConverter(CategoryRepository categoryRepository, ImageRepository imageRepository){
        this.categoryRepository = categoryRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public RecipeEntity convert(RecipeModel source) {
        var target = new RecipeEntity();
        target.setId(source.getId());
        target.setCategory(this.getCategory(source.getCategoryId()));
        target.setInstructions(source.getInstructions());
        target.setTitle(source.getTitle());
        if (source.getImage() != null) {
            var imageEntity = this.getImage(source.getImage().getId());
            if (imageEntity != null) {
                target.setImage(List.of(imageEntity));
            }
        }
        return target;
    }

    private ImageEntity getImage(UUID id){
        return imageRepository.findById(id).orElse(null);
    }

    private CategoryEntity getCategory(long id){
        return categoryRepository.findById(id).orElse(null);
    }
}
