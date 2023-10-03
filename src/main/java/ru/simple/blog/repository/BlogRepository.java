package ru.simple.blog.repository;

import org.springframework.data.repository.CrudRepository;
import ru.simple.blog.modal.Blog;

public interface BlogRepository extends CrudRepository<Blog, Long> {

    Blog findByTitle(String title);
}
