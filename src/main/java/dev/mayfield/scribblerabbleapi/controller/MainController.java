package dev.mayfield.scribblerabbleapi.controller;

import dev.mayfield.scribblerabbleapi.service.PostService;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    PostService postService;

    @GetMapping("/")
    String returnIndex(Model model, Principal principal) {
        KeycloakAuthenticationToken keycloakAuthenticationToken = (KeycloakAuthenticationToken)
                SecurityContextHolder.getContext().getAuthentication();
        AccessToken token = keycloakAuthenticationToken.getAccount().getKeycloakSecurityContext().getToken();

        // Current user
        System.out.println(token.getPreferredUsername());

        model.addAttribute("posts", postService.getAllPosts());
        model.addAttribute("currentUser", token.getPreferredUsername());
        return "index";
    }
}
