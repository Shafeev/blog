package ru.simple.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.simple.blog.modal.Blog;
import ru.simple.blog.repository.BlogRepository;

import java.util.Map;

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


    @GetMapping("/add")
    public String addBlog(Model model) {
        model.addAttribute("blog", new Blog());
        return "blog/add";
    }


    @PostMapping
    public String createBlog(@RequestParam Map<String, String> form) {
        for (String key : form.keySet()) {
            System.out.println(key);
        }
        Blog newBlog = new Blog();
        newBlog.setTitle(form.get("title"));
        blogRepository.save(newBlog);
        return "redirect:/index";
    }


}
