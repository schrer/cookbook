package at.schrer.cookbook.frontend.controller;

class ControllerConstants {

    // No instantiation possible
    private ControllerConstants() {
    }

    static final String REDIRECT_PREFIX = "redirect:";

    static final String MODEL_ATTR_RECIPE_LIST = "recipeList";
    static final String MODEL_ATTR_CATEGORY = "category";
    static final String MODEL_ATTR_RECIPE = "recipe";

    // HomePageController
    static final String TEMPLATE_HOMEPAGE = "homepage";

    // RecipeController
    static final String TEMPLATE_RECIPE_NOT_FOUND = "recipeNotFound";
    static final String TEMPLATE_RECIPE = "recipe";
    static final String TEMPLATE_ADD_RECIPE = "addRecipe";

    // CategoryController
    static final String TEMPLATE_CATEGORY_NOT_FOUND = "categoryNotFound";
    static final String TEMPLATE_CATEGORY = "category";
    static final String TEMPLATE_CATEGORY_OVERVIEW = "categoryOverview";
    static final String TEMPLATE_ADD_CATEGORY = "addCategory";
}
