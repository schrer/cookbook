package at.schrer.cookbook.frontend.controller;

import at.schrer.cookbook.data.model.RecipeModel;
import at.schrer.cookbook.service.RecipeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static at.schrer.cookbook.frontend.FrontendConstants.MODEL_ATTR_RECIPE_LIST;
import static at.schrer.cookbook.frontend.FrontendConstants.TEMPLATE_HOMEPAGE;

@Controller
@RequestMapping("/")
public class HomePageController {

    private final RecipeService recipeService;

    @Autowired
    public HomePageController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public String showHomePage(Model model, @RequestParam(required = false) String query) {

        List<RecipeModel> recipes;

        if (StringUtils.isBlank(query)){
            recipes = recipeService.getAllRecipes();
        } else {
            recipes = recipeService.searchRecipesByTitle(query);
        }
        model.addAttribute(MODEL_ATTR_RECIPE_LIST, recipes);

        return TEMPLATE_HOMEPAGE;
    }

}
