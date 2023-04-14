package at.schrer.cookbook.frontend.controller;

import at.schrer.cookbook.data.model.ImageModel;
import at.schrer.cookbook.data.model.RecipeModel;
import at.schrer.cookbook.service.ImageService;
import at.schrer.cookbook.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.Optional;

import static at.schrer.cookbook.frontend.FrontendConstants.*;

@Controller
@RequestMapping("/" + SEGMENT_RECIPE)
public class RecipeController {

    private final RecipeService recipeService;
    private final ImageService imageService;

    @Autowired
    public RecipeController(RecipeService recipeService, ImageService imageService) {
        this.recipeService = recipeService;
        this.imageService = imageService;
    }

    @GetMapping({"/",""})
    public String redirectHome() {
        return REDIRECT_PREFIX + "/";
    }

    @GetMapping("/{id}")
    public String showRecipe(@PathVariable long id, Model model) {
        Optional<RecipeModel> recipe = recipeService.getRecipeById(id);

        if (recipe.isPresent()) {
            model.addAttribute(MODEL_ATTR_RECIPE, recipe.get());
            return TEMPLATE_RECIPE;
        }

        return TEMPLATE_RECIPE_NOT_FOUND;

    }

    @GetMapping("/" + SEGMENT_ADD)
    public String showAddRecipe(Model model) {
        model.addAttribute(MODEL_ATTR_RECIPE,new RecipeModel());
        return TEMPLATE_ADD_RECIPE;
    }

    @PostMapping(value = "/" + SEGMENT_ADD, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addRecipe(@RequestParam @NotBlank String title,
                            @RequestParam @NotBlank String instructions, @RequestParam Long categoryId, @RequestPart(value = "multiPartImage") MultipartFile image) throws IOException {

//        if (result.hasErrors()) {
//            return TEMPLATE_ADD_RECIPE;
//        }

        RecipeModel recipe = new RecipeModel();
        recipe.setCategoryId(categoryId);
        recipe.setInstructions(instructions);
        recipe.setTitle(title);

        RecipeModel savedRecipe = recipeService.saveRecipe(recipe);

        if (!image.isEmpty()){
            imageService.saveImage(image, savedRecipe.getId());
        }

        return REDIRECT_PREFIX + savedRecipe.getUrl();
    }
}
