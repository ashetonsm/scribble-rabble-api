package dev.mayfield.scribblerabbleapi.controller;

import dev.mayfield.scribblerabbleapi.model.FooModel;
import dev.mayfield.scribblerabbleapi.service.PostService;
import dev.mayfield.scribblerabbleapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@Controller
@RequestMapping("/tests")
public class TestController {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @Autowired
    private WebClient webClient;

    @Value("${recourseserver.api.url}")
    private String fooApiUrl;

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

    @GetMapping("/foos")
    public String getFoos(Model model) {
        List<FooModel> foos = this.webClient.get()
                .uri(fooApiUrl)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<FooModel>>() {

                })
                .block();
        model.addAttribute("foos", foos);
        return "foos";
    }
}
