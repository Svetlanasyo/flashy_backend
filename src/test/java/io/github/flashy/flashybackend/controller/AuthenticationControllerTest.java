package io.github.flashy.flashybackend.controller;

import io.github.flashy.flashybackend.model.User;
import io.github.flashy.flashybackend.model.UserDto;
import io.github.flashy.flashybackend.service.UserService;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
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
    private UserService userService;

    private List<UserDto> usersdb;


    @Before
    public void setupTest() {
        when(userService.registerNewUser(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            UserDto dto = new UserDto();

            dto.setId(usersdb.size());
            dto.setNickName(user.getNickName());
            dto.setPassword(user.getPassword());

            usersdb.add(dto);

            return dto;
        });

        when(userService.canLogin(anyString(), anyString())).thenAnswer(invocation -> {
            String username = invocation.getArgument(0);
            String password = invocation.getArgument(1);

            return usersdb.stream()
                    .anyMatch(dto -> dto.getNickName().equals(username) && dto.getPassword().equals(password));
        });

        usersdb = new ArrayList<>();
    }

    @Test
    public void testReturn201IfUserRegistered() throws Exception {
        JSONObject json = new JSONObject();
        json.put("nick_name", "user");
        json.put("password", "user1234");

        ResultActions actions = mockMvc.perform(
                post("/register")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(json.toString())
        );

        // Expected: I will register
        actions.andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        verify(userService).registerNewUser(any(User.class));
    }

    @Test
    public void testReturnAcceptedIfUserLogin() throws Exception {
        // given

        UserDto user = new UserDto();
        user.setId(0);
        user.setNickName("user");
        user.setPassword("password");

        usersdb.add(user);

        JSONObject json = new JSONObject();
        json.put("nick_name", "user");
        json.put("password", "password");

        ResultActions actions = mockMvc.perform(
                post("/login")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(json.toString())
        );

        // Expected:
        JSONObject expecteJson = new JSONObject();
        expecteJson.put("logged_in", "true");


        actions.andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        verify(userService).canLogin(anyString(), anyString());
    }
}
