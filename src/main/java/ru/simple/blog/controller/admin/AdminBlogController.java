package ru.simple.blog.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.simple.blog.modal.Blog;
import ru.simple.blog.repository.BlogRepository;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminBlogController {

    @Autowired
    private BlogRepository blogRepository;

    @GetMapping("/blogs")
    public String list(Model model) {
        Iterable<Blog> blogs = blogRepository.findAll();
        model.addAttribute("blogs", blogs);
        return "admin/blogs";
    }

    @GetMapping("/add")
    public String addBlog(Model model) {
        model.addAttribute("blog", new Blog());
        return "admin/add";
    }

    @GetMapping("/{id}/edit")
    public String editBlog(Model model, @PathVariable("id") int id) {
        Optional<Blog> blog = blogRepository.findById((long) id);
        model.addAttribute("blog", blog.get());
        return "admin/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("blog") Blog blog, @PathVariable("id") int id) {
        System.out.println(id);
        blog.setId((long) id);
        blogRepository.save(blog);
        return "redirect:admin/index";
    }

    @PostMapping
    public String createBlog(@RequestParam Map<String, String> form) {
        for (String key : form.keySet()) {
            System.out.println(key);
        }
        Blog newBlog = new Blog();
        newBlog.setTitle(form.get("title"));
        blogRepository.save(newBlog);
        return "redirect:admin/index";
    }
}
