package at.schrer.cookbook.frontend.controller;

import at.schrer.cookbook.data.dto.RecipeModel;
import at.schrer.cookbook.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static at.schrer.cookbook.frontend.controller.ControllerConstants.*;

@Controller
@RequestMapping("/recipes/")
public class RecipeController {

    private RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("{id}")
    public String showRecipe(@PathVariable long id, Model model) {
        Optional<RecipeModel> recipe = recipeService.getRecipeById(id);

        if (recipe.isPresent()) {
            model.addAttribute("recipe", recipe.get());
            return TEMPLATE_RECIPE;
        }

        return TEMPLATE_RECIPE_NOT_FOUND;

    }

    @GetMapping("add")
    public String showAddRecipe(Model model) {
        model.addAttribute("recipe",new RecipeModel());
        return TEMPLATE_ADD_RECIPE;
    }

    @PostMapping("add")
    public String addRecipe(@Valid @ModelAttribute("recipe") RecipeModel recipe, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return TEMPLATE_ADD_RECIPE;
        }

        RecipeModel savedRecipe = recipeService.saveRecipe(recipe);
        return REDIRECT_PREFIX + savedRecipe.getId();
    }
}
