package at.schrer.cookbook.frontend.controller;

import at.schrer.cookbook.data.dto.RecipeModel;
import at.schrer.cookbook.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static at.schrer.cookbook.frontend.controller.ControllerConstants.MODEL_ATTR_RECIPE_LIST;
import static at.schrer.cookbook.frontend.controller.ControllerConstants.TEMPLATE_HOMEPAGE;

@Controller
@RequestMapping("/")
public class HomePageController {

    private RecipeService recipeService;

    @Autowired
    public HomePageController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public String showHomePage(Model model) {

        List<RecipeModel> recipes = recipeService.getAllRecipes();
        model.addAttribute(MODEL_ATTR_RECIPE_LIST, recipes);

        return TEMPLATE_HOMEPAGE;
    }

}
