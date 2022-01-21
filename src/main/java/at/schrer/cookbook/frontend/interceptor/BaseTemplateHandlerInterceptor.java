package at.schrer.cookbook.frontend.interceptor;

import at.schrer.cookbook.data.model.CategoryModel;
import at.schrer.cookbook.frontend.FrontendConstants;
import at.schrer.cookbook.frontend.util.UrlResolver;
import at.schrer.cookbook.service.CategoryService;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class BaseTemplateHandlerInterceptor implements HandlerInterceptor {

    private final CategoryService categoryService;
    private final UrlResolver urlResolver;

    @Autowired
    public BaseTemplateHandlerInterceptor(CategoryService categoryService, UrlResolver urlResolver) {
        this.categoryService = categoryService;
        this.urlResolver = urlResolver;
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
            modelMap.addAttribute("homepageUrl", urlResolver.resolve(FrontendConstants.Path.HOMEPAGE));
            modelMap.addAttribute("addRecipeUrl", urlResolver.resolve(FrontendConstants.Path.ADD_RECIPE));
            modelMap.addAttribute("addCategoryUrl", urlResolver.resolve(FrontendConstants.Path.ADD_CATEGORY));
        }

    }
}
