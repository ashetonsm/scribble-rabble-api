package dev.mayfield.scribblerabbleapi.controller;

import dev.mayfield.scribblerabbleapi.DTOs.LoginSuccessResponse;
import dev.mayfield.scribblerabbleapi.model.User;
import dev.mayfield.scribblerabbleapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public void createNewUser(@RequestBody User user) {
        userService.createNewUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Get user by ID or Username. If ID cannot be parsed as an integer, assume string and find by username.
     *
     * @param identifier
     * @return
     */
    @GetMapping("/{identifier}")
    public User findUserByIdOrUsername(@PathVariable String identifier) {
        boolean isNum;
        Integer id = null;
        User user = new User();

        try {
            id = Integer.parseInt(identifier);
            isNum = true;
        } catch (NumberFormatException e) {
            isNum = false;
        }

        if (isNum) {
            try {
                return userService.findUserById(id);
            } catch (Exception e) {
                System.out.println("User not found!");
            }
        } else {
            try {
                return userService.findByUsername(identifier);
            } catch (Exception e) {
                System.out.println("User not found!");
            }
        }

        return user;
    }

    @PostMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public LoginSuccessResponse loginAuthentication(@RequestBody User user) {
        return new LoginSuccessResponse(user.getUsername(), "JWT: asuidhf0 129374yuasidfhashdf0918wysf897yhui", "http://localhost:3000/");
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User login(@RequestBody User user) {
        return userService.verifyCredentials(user);
    }

}
