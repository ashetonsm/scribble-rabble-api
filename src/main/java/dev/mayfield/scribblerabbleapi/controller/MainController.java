package dev.mayfield.scribblerabbleapi.controller;

import dev.mayfield.scribblerabbleapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    PostService postService;

    @GetMapping("/")
    String returnIndex(Model model) {
        model.addAttribute("posts", postService.getAllPosts());

        return "index";
    }
}
