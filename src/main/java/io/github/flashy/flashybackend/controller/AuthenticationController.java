package io.github.flashy.flashybackend.controller;

import io.github.flashy.flashybackend.model.User;
import io.github.flashy.flashybackend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class AuthenticationController {

    private UserRepository repository;

    @PostMapping(value = "/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        repository.save(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
