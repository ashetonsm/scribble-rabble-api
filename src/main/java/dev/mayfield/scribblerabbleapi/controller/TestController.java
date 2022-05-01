package dev.mayfield.scribblerabbleapi.controller;

import dev.mayfield.scribblerabbleapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequestMapping("/tests")
public class TestController {

    @Autowired
    UserService userService;

    @GetMapping
    String getHello(Model model) {
        model.addAttribute("something", "this is coming from the controller.");
        model.addAttribute("users", userService.getAllUsers());

        // return string needs to be the same name as the html template that will be returned
        return "test";
    }
}
