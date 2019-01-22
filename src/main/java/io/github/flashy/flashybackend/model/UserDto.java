package io.github.flashy.flashybackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserDto {
    private long id;
    @JsonIgnore
    private String nickName;
    @JsonIgnore
    private String password;


    public static UserDto toDto(User user) {
        return new UserDto() {{
            setId(user.getId());
            setNickName(user.getNickName());
            setPassword(user.getPassword());
        }};
    }

    public User toDomain() {
        return new User() {{
            setId(id);
            setNickName(nickName);
            setPassword(password);
        }};
    }
}
