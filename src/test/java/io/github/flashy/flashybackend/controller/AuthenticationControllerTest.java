package io.github.flashy.flashybackend.controller;

import io.github.flashy.flashybackend.model.User;
import io.github.flashy.flashybackend.repositories.UserRepository;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthenticationController.class)
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    private List<User> usersdb;


    @Before
    public void setupTest() {
        if (usersdb == null) {
            setupBeforeAllTests();
        }

        usersdb.clear();
    }

    private void setupBeforeAllTests() {
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            usersdb.add(user);

            return user;
        });

        usersdb = new ArrayList<>();
    }

    @Test
    public void testReturn201() throws Exception {

        // Given: I send valid register data, nick name and password also
        JSONObject json = new JSONObject();
        json.put("nick_name", "user");
        json.put("password", "user1234");


        // When: I send that to rest endpoint /register
        ResultActions actions = mockMvc.perform(
                post("/register")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(json.toString())
        );

        // Expected: I will register
        actions.andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        verify(userRepository).save(any(User.class));
    }
}
