package com.example.productmanagement.system.web.controller.user;

import com.example.productmanagement.system.persistence.dao.user.UserRepository;
import com.example.productmanagement.system.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String showSignUpForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String createUser(String username, String password) {
        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));

            if (username.equals("admin")) {
                user.setRoles(Set.of("ADMIN", "USER")); // Admin has both roles
            } else {
                user.setRoles(Set.of("USER")); // Other users have only USER role
            }

            userRepository.save(user);
            return "redirect:/login";
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            // Return an error view or handle the error gracefully
            return "error";
        }
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

}
