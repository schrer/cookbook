package at.schrer.cookbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recipe/")
public class RecipeController {

    @GetMapping("{id}")
    public String showRecipe(@PathVariable long id, Model model){
        // Get recipe from ID and add to model
        return "recipe";
    }
}
