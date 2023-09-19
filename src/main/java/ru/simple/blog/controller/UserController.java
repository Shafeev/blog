package ru.simple.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.simple.blog.modal.Role;
import ru.simple.blog.modal.User;
import ru.simple.blog.repository.UserRepository;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String userList(Map<String, Object> model) {
        model.put("users", userRepository.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Map<String, Object> model) {
        model.put("user", user);
        model.put("roles", Role.values());
        for (Role role : user.getRoles()) {
            System.out.println(role);
        }
        model.put("isAdmin", user.getRoles().contains(Role.ADMIN));
        model.put("isUser", user.getRoles().contains(Role.USER));
        return "userEdit";
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user) {
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            System.out.println(key);
            if(roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));;
            }
        }
        userRepository.save(user);
        return "redirect:/user";
    }
}
