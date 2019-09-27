package at.schrer.cookbook.interceptor;

import at.schrer.cookbook.entity.Category;
import at.schrer.cookbook.repository.CategoryRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SidebarHandlerInterceptor extends HandlerInterceptorAdapter {

    private CategoryRepository categoryRepository;

    public SidebarHandlerInterceptor(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView) {

        if(modelAndView != null){

            ModelMap modelMap = modelAndView.getModelMap();

            if(!modelMap.containsAttribute("categories")){
                List<Category> categories = IteratorUtils.
                        toList(categoryRepository.
                                findAll().
                                iterator());
                modelMap.addAttribute("categories",categories);
            }

        }

    }
}
