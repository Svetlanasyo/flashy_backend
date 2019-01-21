package io.github.flashy.flashybackend.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

}
