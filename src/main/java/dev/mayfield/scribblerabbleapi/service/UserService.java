package dev.mayfield.scribblerabbleapi.service;

import dev.mayfield.scribblerabbleapi.model.User;
import dev.mayfield.scribblerabbleapi.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * List all Users.
     * @return
     */
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    /**
     * Create a new User.
     * @param user
     */
    public void createNewUser(User user){
        userRepository.save(user);
    }

    /**
     * Find a User by their ID number.
     *
     * @param id ID to search
     * @return User
     */
    public User findUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
    }

    /**
     * Find a User by their username.
     *
     * @param username Username to search
     * @return User
     */
    public User findByUsername(String username) {
        try {
            return userRepository.findByUsername(username);
        } catch (Exception e) {
            throw new RuntimeException("User was not found.");
        }
    }

    /**
     * Check a username and password against database info.
     *
     * @param user User provided by client
     * @return User matching provided username and password
     */
    public User checkLogin(User user) {
        User foundUser;
        try {
            foundUser = userRepository.findByUsername(user.getUsername());
            if (foundUser.getPassword().equals(user.getPassword())) {
                return foundUser;
            }
        } catch (Exception e) {
            System.out.println("No user with that ID.");
        }
        User invalidLogin = new User();
        invalidLogin.setId(-1);
        return invalidLogin;
    }
}
