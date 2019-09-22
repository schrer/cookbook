package at.schrer.cookbook.controller;

import at.schrer.cookbook.entity.Category;
import at.schrer.cookbook.repository.CategoryRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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

  @GetMapping("/add")
  public String addCategory(){
    return "addCategory";
  }
}
