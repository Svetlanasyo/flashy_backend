package io.github.flashy.flashybackend.repositories;

import io.github.flashy.flashybackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
