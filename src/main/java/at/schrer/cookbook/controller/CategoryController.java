package at.schrer.cookbook.controller;

import at.schrer.cookbook.entity.Category;
import at.schrer.cookbook.entity.Recipe;
import at.schrer.cookbook.repository.CategoryRepository;
import at.schrer.cookbook.repository.RecipeRepository;
import org.apache.commons.collections4.IteratorUtils;
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

import static at.schrer.cookbook.controller.ControllerConstants.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/")
    public String listCategories(Model model) {
        List<Category> categories = IteratorUtils.toList(categoryRepository.findAll().iterator());
        model.addAttribute("categories", categories);
        return TEMPLATE_CATEGORY_OVERVIEW;
    }

    @GetMapping("/{id}")
    public String showCategory(@PathVariable long id, Model model) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();

            List<Recipe> recipes = recipeRepository.findRecipesByCategory(category);

            model.addAttribute("category", category);
            model.addAttribute("recipes", recipes);

            return TEMPLATE_CATEGORY;
        }

        return TEMPLATE_CATEGORY_NOT_FOUND;

    }

    @GetMapping("/add")
    public String showAddCategory() {
        return TEMPLATE_ADD_CATEGORY;
    }

    @PostMapping("/add")
    public String addCategory(@Valid Category category, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return TEMPLATE_ADD_CATEGORY;
        }

        Category savedCategory = categoryRepository.save(category);
        return REDIRECT_PREFIX + savedCategory.getId();
    }
}
