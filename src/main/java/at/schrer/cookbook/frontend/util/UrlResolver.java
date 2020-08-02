package at.schrer.cookbook.frontend.util;

import at.schrer.cookbook.data.model.CategoryModel;
import at.schrer.cookbook.data.model.ImageModel;
import at.schrer.cookbook.data.model.RecipeModel;
import org.springframework.stereotype.Component;

import static at.schrer.cookbook.frontend.FrontendConstants.*;

/**
 * Resolves URLs to the view of supported Model-types and to .
 * All attributes (except the url itself) need to be set on the model before calling the resolve method, otherwise a successful resolving cannot be guaranteed.
 */
@Component
public class UrlResolver {

    /**
     * Resolves the view-URL to a category.
     * All attributes (except the url itself) need to be set on the model before calling the resolve method, otherwise a successful resolving cannot be guaranteed.
     *
     * @param category the category model
     * @return the URL to the view page of the category
     */
    public String resolve(CategoryModel category) {
        return buildUrlFromSegments(SEGMENT_CATEGORY, Long.toString(category.getId()));
    }

    /**
     * Resolves the view-URL to a recipe.
     * All attributes (except the url itself) need to be set on the model before calling the resolve method, otherwise a successful resolving cannot be guaranteed.
     *
     * @param recipe the recipe model
     * @return the URL to the view page of the recipe
     */
    public String resolve(RecipeModel recipe) {
        return buildUrlFromSegments(SEGMENT_RECIPE, Long.toString(recipe.getId()));
    }

    /**
     * Resolves the view-URL to an image.
     * All attributes (except the url itself) need to be set on the model before calling the resolve method, otherwise a successful resolving cannot be guaranteed.
     *
     * @param image the image model
     * @return the URL to the view page of the recipe
     */
    public String resolve(ImageModel image){
        return buildUrlFromSegments(SEGMENT_MEDIA, SEGMENT_IMAGES, image.getId());
    }

    /**
     * Resolves the URL for one of the path enums.
     * @param path a Path enum, that represents the desired URL
     * @return the URL described by the Path enum
     */
    public String resolve(Path path){
        return path.getValue();
    }

    /**
     * Takes an arbitrary number of segments and adds them up to a URL. If no segments are provided the link to the homepage is returned.
     *
     * @param segments the segments of the URL
     * @return the segments merged into a URL, "/" if no segments are provided
     */
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
