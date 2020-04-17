package at.schrer.cookbook.frontend.util;

import at.schrer.cookbook.data.dto.CategoryModel;
import at.schrer.cookbook.data.dto.RecipeModel;
import org.springframework.stereotype.Component;

/**
 * Resolves URLs to the view of supported Model-types.
 * All attributes (except the url itself) need to be set on the model before calling the resolve method, otherwise a successful resolving cannot be guaranteed.
 */
@Component
public class UrlResolver {

    private static final String SEGMENT_CATEGORY = "categories";
    private static final String SEGMENT_RECIPE = "recipes";
    private static final String SEGMENT_ADD = "add";

    public enum Path {
        ADD_RECIPE("/" + SEGMENT_RECIPE + "/" + SEGMENT_ADD),
        ADD_CATEGORY("/" + SEGMENT_CATEGORY + "/" + SEGMENT_ADD),
        HOMEPAGE("/");

        private final String value;
        Path(String path){
            this.value = path;
        }

        String getValue(){
            return this.value;
        }
    }

    public String resolve(CategoryModel category) {
        return buildUrlFromSegments(SEGMENT_CATEGORY, Long.toString(category.getId()));
    }

    public String resolve(RecipeModel recipe) {
        return buildUrlFromSegments(SEGMENT_RECIPE, Long.toString(recipe.getId()));
    }

    public String resolve(Path path){
        return path.getValue();
    }

    private String buildUrlFromSegments(String... segments){
        if (segments.length == 0){
            return "/";
        }

        StringBuilder urlBuilder = new StringBuilder();
        for (String segment : segments) {
            urlBuilder.append("/");
            urlBuilder.append(segment);
        }
        return urlBuilder.toString();
    }
}
