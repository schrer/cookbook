package at.schrer.cookbook.frontend.interceptor;

import at.schrer.cookbook.data.dto.CategoryModel;
import at.schrer.cookbook.service.CategoryService;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class SidebarHandlerInterceptor extends HandlerInterceptorAdapter {

    private CategoryService categoryService;

    @Autowired
    public SidebarHandlerInterceptor(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView) {

        if (modelAndView != null) {

            ModelMap modelMap = modelAndView.getModelMap();

            if (!modelMap.containsAttribute("categories")) {
                List<CategoryModel> categories = IteratorUtils.
                        toList(categoryService.
                                getAllCategories().
                                iterator());
                modelMap.addAttribute("categories", categories);
            }

        }

    }
}
