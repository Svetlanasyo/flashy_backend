package io.github.flashy.flashybackend.model;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@JsonTest
public class UserDtoTest {

    @Test
    public void testToDto() {
        // Given
        User user = new User() {{
            setId(1L);
            setNickName("user");
            setPassword("user1234");
        }};

        // When
        UserDto dto = UserDto.toDto(user);

        // Expected
        assertEquals(1L, dto.getId());
        assertEquals("user", dto.getNickName());
        assertEquals("user1234", dto.getPassword());
    }

    @Test
    public void testToDomain() {
        // Given
        UserDto userDto = new UserDto() {{
            setId(1L);
            setNickName("user");
            setPassword("user1234");
        }};

        // When
        User domain = userDto.toDomain();

        // Expect
        assertEquals(1L, domain.getId());
        assertEquals("user", domain.getNickName());
        assertEquals("user1234", domain.getPassword());
    }

    @Autowired
    private JacksonTester<UserDto> json;

    @Test
    public void testSerializeUser() throws IOException, JSONException {
        // Given
        UserDto dto = new UserDto() {{
            setId(1);
            setNickName("user");
            setPassword("user1234");
        }};

        // When
        JsonContent<UserDto> serialized = json.write(dto);

        // Except
        String expected = new JSONObject().put("id", 1).toString();
        String actual = serialized.getJson();
        assertEquals(expected, actual);
    }
}
