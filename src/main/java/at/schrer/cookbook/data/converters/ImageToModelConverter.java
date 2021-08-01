package at.schrer.cookbook.data.converters;

import at.schrer.cookbook.data.entity.ImageEntity;
import at.schrer.cookbook.data.model.ImageModel;
import at.schrer.cookbook.frontend.util.UrlResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ImageToModelConverter implements Converter<ImageEntity, ImageModel> {

    private final UrlResolver urlResolver;

    @Autowired
    public ImageToModelConverter(UrlResolver urlResolver){
        this.urlResolver = urlResolver;
    }

    @Override
    public ImageModel convert(ImageEntity source){
        ImageModel target = new ImageModel();
        target.setId(source.getId().toString());
        target.setUrl(urlResolver.resolve(target));
        return target;
    }
}
