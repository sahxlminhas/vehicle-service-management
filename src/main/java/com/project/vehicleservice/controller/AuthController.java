package com.project.vehicleservice.controller;

import com.project.vehicleservice.model.User;
import com.project.vehicleservice.services.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller  // 🔹 Indicates this class handles web routes
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // 🔹 Show login page
    @GetMapping("/login")
    public String login() {
        return "login";  // maps to login.html
    }

    // 🔹 Show registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";  // maps to register.html
    }

    // 🔹 Handle form submission
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        if (userService.findByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "Email already exists!");
            return "register";
        }

        userService.registerUser(user);
        model.addAttribute("success", "Registration successful! Please log in.");
        return "login";
    }

    // 🔹 After login, redirect to dashboard
    
    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        model.addAttribute("userEmail", authentication.getName());

        List<Map<String, String>> services = new ArrayList<>();

        services.add(Map.of(
            "name", "Oil Change",
            "image", "oil_change.jpg",
            "price", "499"
        ));
        services.add(Map.of(
            "name", "Tyre Replacement",
            "image", "tyre_replacement.jpg",
            "price", "1200"
        ));
        services.add(Map.of(
            "name", "Engine Check",
            "image", "engine_check.jpg",
            "price", "799"
        ));
        services.add(Map.of(
            "name", "Battery Service",
            "image", "battery_service.jpg",
            "price", "999"
        ));
        services.add(Map.of(
            "name", "Car Wash",
            "image", "car_wash.jpg",
            "price", "400"
        ));
        services.add(Map.of(
            "name", "Full Service",
            "image", "full_service.jpg",
            "price", "3000"
        ));

        model.addAttribute("services", services);
        return "dashboard";
    }

}
