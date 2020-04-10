package at.schrer.cookbook.frontend.controller;

import at.schrer.cookbook.data.dto.CategoryModel;
import at.schrer.cookbook.data.dto.RecipeModel;
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

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static at.schrer.cookbook.frontend.controller.ControllerConstants.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;
    private RecipeService recipeService;

    @Autowired
    public CategoryController(CategoryService categoryService, RecipeService recipeService) {
        this.categoryService = categoryService;
        this.recipeService = recipeService;
    }

    @GetMapping("/")
    public String listCategories(Model model) {
        List<CategoryModel> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return TEMPLATE_CATEGORY_OVERVIEW;
    }

    @GetMapping("/{id}")
    public String showCategory(@PathVariable long id, Model model) {
        Optional<CategoryModel> categoryOptional = categoryService.getCategoryByID(id);

        if (categoryOptional.isPresent()) {
            CategoryModel category = categoryOptional.get();

            List<RecipeModel> recipes = recipeService.getRecipesByCategory(category);

            model.addAttribute("category", category);
            model.addAttribute("recipes", recipes);

            return TEMPLATE_CATEGORY;
        }

        return TEMPLATE_CATEGORY_NOT_FOUND;

    }

    @GetMapping("/add")
    public String showAddCategory(Model model) {
        model.addAttribute("category", new CategoryModel());
        return TEMPLATE_ADD_CATEGORY;
    }

    @PostMapping("/add")
    public String addCategory(@Valid CategoryModel category, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return TEMPLATE_ADD_CATEGORY;
        }

        CategoryModel savedCategory = categoryService.saveCategory(category);
        return REDIRECT_PREFIX + savedCategory.getId();
    }
}
