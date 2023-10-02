package ru.simple.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.simple.blog.modal.Blog;
import ru.simple.blog.repository.BlogRepository;

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

}
