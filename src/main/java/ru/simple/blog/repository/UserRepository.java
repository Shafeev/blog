package ru.simple.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.simple.blog.modal.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
