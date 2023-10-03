package ru.simple.blog.controller;

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

    @GetMapping("/{id}/edit")
    public String editBlog(Model model, @PathVariable("id") int id) {
        Optional<Blog> blog = blogRepository.findById((long) id);
        model.addAttribute("blog", blog.get());
        return "blog/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("blog") Blog blog, @PathVariable("id") int id) {
        System.out.println(id);
        blog.setId((long) id);
        blogRepository.save(blog);
        return "redirect:/index";
    }


}
