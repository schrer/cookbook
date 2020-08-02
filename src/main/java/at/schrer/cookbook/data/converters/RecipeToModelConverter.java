package at.schrer.cookbook.data.converters;

import at.schrer.cookbook.data.model.RecipeModel;
import at.schrer.cookbook.data.entity.RecipeEntity;
import at.schrer.cookbook.frontend.util.UrlResolver;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeToModelConverter implements Converter<RecipeEntity, RecipeModel> {

    private final UrlResolver urlResolver;

    @Autowired
    public RecipeToModelConverter (UrlResolver urlResolver){
        this.urlResolver = urlResolver;
    }

    @Override
    public RecipeModel convert(RecipeEntity source) {
        var target = new RecipeModel();

        target.setId(source.getId());
        target.setInstructions(source.getInstructions());
        target.setTitle(source.getTitle());
        target.setCategoryId(source.getCategory().getId());
        target.setShortDescription(createShortDescription(source));
        target.setUrl(urlResolver.resolve(target));
        return target;
    }

    private String createShortDescription(RecipeEntity source){
        String instructions = source.getInstructions();
        String shortDescription;

        if (StringUtils.isBlank(instructions)){
            return null;
        }

        if (instructions.length()>150){
            shortDescription = instructions.substring(0, 150);

        } else if (!instructions.contains("\n")) {
            return instructions;

        } else {
            shortDescription = instructions;
        }

        if (shortDescription.contains("\n")){
            return shortDescription.split("\n")[0] + " ...";
        }

        return shortDescription.substring(0, shortDescription.lastIndexOf(' ')) + " ...";
    }
}
