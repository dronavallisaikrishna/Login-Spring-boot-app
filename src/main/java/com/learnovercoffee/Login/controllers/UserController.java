package com.learnovercoffee.Login.controllers;

import com.learnovercoffee.Login.repositories.UserRepository;
import com.learnovercoffee.Login.exceptionHandling.ResourceNotFoundException;
import com.learnovercoffee.Login.models.User;
import com.learnovercoffee.Login.security.CurrentUser;
import com.learnovercoffee.Login.security.UserPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
