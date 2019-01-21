package io.github.flashy.flashybackend.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
public class User {
    @Id @GeneratedValue
    private long id;

    @Size(min = 3, max = 20, message = "Nickname must be beetween 3 and 20 characters long")
    private String nickName;

    @Size(min = 4, max = 8, message = "Password must be beetween 4 and 8 characters long")
    private String password;
}
