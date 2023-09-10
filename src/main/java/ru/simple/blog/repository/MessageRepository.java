package ru.simple.blog.repository;

import org.springframework.data.repository.CrudRepository;
import ru.simple.blog.modal.Message;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {

    List<Message> findByTag(String tag);
}
