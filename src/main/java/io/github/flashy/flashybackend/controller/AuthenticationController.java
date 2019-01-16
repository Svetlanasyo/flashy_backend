package io.github.flashy.flashybackend.controller;

import io.github.flashy.flashybackend.model.User;
import io.github.flashy.flashybackend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class AuthenticationController {

    private UserRepository repository;

    @PostMapping("/signup")
    public User signup(User newUser) {
        return null;
    }
}
