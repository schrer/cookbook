package at.schrer.cookbook.controller;

import at.schrer.cookbook.entity.Recipe;
import at.schrer.cookbook.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/recipes/")
public class RecipeController {

    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeController(RecipeRepository recipeRepository){
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("{id}")
    public String showRecipe(@PathVariable long id, Model model){
        Optional<Recipe> recipe = recipeRepository.findById(id);

        if (recipe.isPresent()){
            model.addAttribute("recipe",recipe.get());
            return "recipe";
        }

        return "recipeNotFound";

    }

    @GetMapping("add")
    public String showAddRecipe(Recipe recipe){
        return "addRecipe";
    }

    @PostMapping("add")
    public String addRecipe(@Valid Recipe recipe, BindingResult result, Model model){

        if (result.hasErrors()){
            return "addRecipe";
        }

        Recipe savedRecipe = recipeRepository.save(recipe);
        return "redirect:"+ savedRecipe.getId();
    }
}
