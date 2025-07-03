package com.project.vehicleservice.services;

import com.project.vehicleservice.model.User;

public interface UserService {

    //  Register a new user
    User registerUser(User user);

    //  Find a user by their email (used in login)
    User findByEmail(String email);
}
