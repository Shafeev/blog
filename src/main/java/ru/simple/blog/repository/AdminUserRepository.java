package ru.simple.blog.repository;

import org.springframework.data.repository.CrudRepository;
import ru.simple.blog.modal.AdminUser;

public interface AdminUserRepository extends CrudRepository<AdminUser, Long> {
    AdminUser findAdminUserByUserNameAndPassword(String username, String password);
}
