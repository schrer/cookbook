package at.schrer.cookbook.frontend;

import at.schrer.cookbook.frontend.util.UrlResolver;

public class FrontendConstants {

    // No instantiation possible
    private FrontendConstants() {
    }

    public static final String REDIRECT_PREFIX = "redirect:";

    // URL segments for all pages
    public static final String SEGMENT_CATEGORY = "categories";
    public static final String SEGMENT_RECIPE = "recipes";
    public static final String SEGMENT_ADD = "add";
    public static final String SEGMENT_MEDIA = "media";
    public static final String SEGMENT_IMAGES = "images";

    public static final String MODEL_ATTR_RECIPE_LIST = "recipeList";
    public static final String MODEL_ATTR_CATEGORY = "category";
    public static final String MODEL_ATTR_RECIPE = "recipe";
    public static final String MODEL_ATTR_RECIPE_IMAGE_URL = "recipeImageUrl";

    // HomePageController
    public static final String TEMPLATE_HOMEPAGE = "homepage";

    // RecipeController
    public static final String TEMPLATE_RECIPE_NOT_FOUND = "recipeNotFound";
    public static final String TEMPLATE_RECIPE = "recipe";
    public static final String TEMPLATE_ADD_RECIPE = "addRecipe";

    // CategoryController
    public static final String TEMPLATE_CATEGORY_NOT_FOUND = "categoryNotFound";
    public static final String TEMPLATE_CATEGORY = "category";
    public static final String TEMPLATE_CATEGORY_OVERVIEW = "categoryOverview";
    public static final String TEMPLATE_ADD_CATEGORY = "addCategory";


    /**
     * An enum that represents a specific page on the application. Can be resolved to a URL by the {@link UrlResolver}.
     */
    public enum Path {
        ADD_RECIPE("/" + SEGMENT_RECIPE + "/" + SEGMENT_ADD),
        ADD_CATEGORY("/" + SEGMENT_CATEGORY + "/" + SEGMENT_ADD),
        HOMEPAGE("/");

        private final String value;
        Path(String path){
            this.value = path;
        }

        public String getValue(){
            return this.value;
        }
    }
}
