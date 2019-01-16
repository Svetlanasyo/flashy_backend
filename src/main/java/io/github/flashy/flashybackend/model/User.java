package io.github.flashy.flashybackend.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter @Setter
@NoArgsConstructor
@Entity
public class User {
    @Id @GeneratedValue
    private Long id;

    private String nickName;

    private String password;

}
