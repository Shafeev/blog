package ru.simple.blog.controller.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.simple.blog.modal.Blog;
import ru.simple.blog.repository.BlogRepository;

import java.util.Map;
import java.util.Optional;

@Controller
public class BlogController {
    @Autowired
    private BlogRepository blogRepository;

    @GetMapping({"/", "/index", "index.html"})
    public String getBlog(Model model) {
        Iterable<Blog> blogs = blogRepository.findAll();
        model.addAttribute("blogs", blogs);
        return "blog/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Optional<Blog> blog = blogRepository.findById((long) id);
        model.addAttribute("blog", blog.get());
        return "blog/show";
    }












}
