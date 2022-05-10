package dev.mayfield.scribblerabbleapi.controller;

import dev.mayfield.scribblerabbleapi.service.PostService;
import dev.mayfield.scribblerabbleapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tests")
public class TestController {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @GetMapping
    String getHello(Model model) {
        model.addAttribute("something", "this is coming from the controller.");
        model.addAttribute("users", userService.getAllUsers());

        // return string needs to be the same name as the html template that will be returned
        return "test";
    }

    @GetMapping("/seeAllPosts")
    String testGetPosts(Model model) {
        model.addAttribute("posts", postService.getAllPosts());

        return "index";
    }

    // TODO: Set to "admin" only with @RolesAllowed("admin")

    /**
     * Can only be accessed by sending a GET request with a valid authorization token in the header
     * @param model
     * @return
     */
    @GetMapping("/adminPortal")
    String adminPortal(Model model) {
        model.addAttribute("adminStuff", "This is an admins only portal!");

        return "adminPortalTest";
    }
}
