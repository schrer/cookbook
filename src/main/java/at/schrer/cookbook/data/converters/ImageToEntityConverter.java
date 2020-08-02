package at.schrer.cookbook.data.converters;

import at.schrer.cookbook.data.entity.ImageEntity;
import at.schrer.cookbook.data.model.ImageModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ImageToEntityConverter implements Converter<ImageModel, ImageEntity> {

    @Override
    public ImageEntity convert(ImageModel source){
        ImageEntity target = new ImageEntity();
        target.setId(source.getId());
        // Can't set path, not available from ImageModel
        return target;
    }
}
