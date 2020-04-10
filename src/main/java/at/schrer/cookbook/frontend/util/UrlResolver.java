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
    public String resolve(CategoryModel category) {
        return "/categories/" + category.getId();
    }

    public String resolve(RecipeModel recipe) {
        return "/recipes/" + recipe.getId();
    }
}
