package at.schrer.cookbook.frontend.controller;

import at.schrer.cookbook.data.model.CategoryModel;
import at.schrer.cookbook.data.model.RecipeModel;
import at.schrer.cookbook.service.CategoryService;
import at.schrer.cookbook.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

import static at.schrer.cookbook.frontend.FrontendConstants.*;

@Controller
@RequestMapping("/" + SEGMENT_CATEGORY)
public class CategoryController {

    private final CategoryService categoryService;
    private final RecipeService recipeService;

    @Autowired
    public CategoryController(CategoryService categoryService, RecipeService recipeService) {
        this.categoryService = categoryService;
        this.recipeService = recipeService;
    }

    @GetMapping({"/",""})
    public String redirectHome() {
        return REDIRECT_PREFIX + "/";
    }

    @GetMapping("/{id}")
    public String showCategory(@PathVariable long id, Model model) {
        Optional<CategoryModel> categoryOptional = categoryService.getCategoryByID(id);

        if (categoryOptional.isPresent()) {
            CategoryModel category = categoryOptional.get();

            List<RecipeModel> recipes = recipeService.getRecipesByCategory(category);

            model.addAttribute(MODEL_ATTR_CATEGORY, category);
            model.addAttribute(MODEL_ATTR_RECIPE_LIST, recipes);

            return TEMPLATE_CATEGORY;
        }

        return TEMPLATE_CATEGORY_NOT_FOUND;

    }

    @GetMapping("/" + SEGMENT_ADD)
    public String showAddCategory(Model model) {
        model.addAttribute(MODEL_ATTR_CATEGORY, new CategoryModel());
        return TEMPLATE_ADD_CATEGORY;
    }

    @PostMapping("/" + SEGMENT_ADD)
    public String addCategory(@Valid CategoryModel category, BindingResult result) {

        if (result.hasErrors()) {
            return TEMPLATE_ADD_CATEGORY;
        }

        CategoryModel savedCategory = categoryService.saveCategory(category);
        return REDIRECT_PREFIX + savedCategory.getUrl();
    }
}
