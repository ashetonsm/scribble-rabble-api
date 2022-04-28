package dev.mayfield.scribblerabbleapi.controller;

import dev.mayfield.scribblerabbleapi.model.User;
import dev.mayfield.scribblerabbleapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * Create a new User.
     * @param user
     */
    @PostMapping("/register")
    public User createNewUser(@RequestBody User user) {
        return userService.createNewUser(user);
    }

    /**
     * Get a list of all Users.
     *
     * @return
     */
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

    /**
     * Log into the application.
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public User login(@RequestBody User user) {
        return userService.checkLogin(user);
    }

}
