package io.github.flashy.flashybackend.controller;

import io.github.flashy.flashybackend.model.User;
import io.github.flashy.flashybackend.repositories.UserRepository;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthenticationControllerTest {

    private final List<User> users = new ArrayList<>();

    @Test
    public void testWhenSignupNewUser() {
        final User newUser = new User();

        newUser.setId((long) 9991);
        newUser.setNickName("weird_nickname");
        newUser.setPassword("password");

        UserRepository repository = mock(UserRepository.class);

        when(repository.save(newUser)).thenAnswer(invocationOnMock -> {
            users.add(newUser);
            return newUser;
        });

        AuthenticationController controller = new AuthenticationController(repository);

        User createdUser = controller.signup(newUser);

        assertEquals((long) 9991, (long) createdUser.getId());
        assertEquals("weird_nickname", createdUser.getNickName());
        assertEquals("password", createdUser.getPassword());
    }

}