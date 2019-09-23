package at.schrer.cookbook.controller;

import at.schrer.cookbook.entity.Category;
import at.schrer.cookbook.repository.CategoryRepository;
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

@Controller
@RequestMapping("/categories")
public class CategoryController {

  private CategoryRepository categoryRepository;

  @Autowired
  public CategoryController(CategoryRepository categoryRepository){
    this.categoryRepository = categoryRepository;
  }

  @GetMapping("/")
  public String listCategories(Model model){
    List<Category> categories = IteratorUtils.toList(categoryRepository.findAll().iterator());
    model.addAttribute("categories", categories);
    return "categoryOverview";
  }

  @GetMapping("{id}")
  public String showRecipe(@PathVariable long id, Model model){
    Optional<Category> recipe = categoryRepository.findById(id);

    if (recipe.isPresent()){
      model.addAttribute("recipe",recipe.get());
      return "recipe";
    }

    return "recipeNotFound";

  }

  @GetMapping("/add")
  public String showAddCategory(Category category){
    return "addCategory";
  }

  @PostMapping("/add")
  public String addCategory(@Valid Category category, BindingResult result, Model model){

    if (result.hasErrors()){
      return "addCategory";
    }

    Category savedCategory = categoryRepository.save(category);
    return "redirect:"+ savedCategory.getId();
  }
}
