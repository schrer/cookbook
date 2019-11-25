package at.schrer.cookbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static at.schrer.cookbook.controller.ControllerConstants.TEMPLATE_HOMEPAGE;

@Controller
@RequestMapping("/")
public class HomePageController {


    @GetMapping
    public String showHomePage(){
        return TEMPLATE_HOMEPAGE;
    }

}
