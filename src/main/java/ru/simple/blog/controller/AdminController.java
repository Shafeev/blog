package ru.simple.blog.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.simple.blog.modal.AdminUser;
import ru.simple.blog.repository.AdminUserRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminUserRepository adminUserRepository;
    @Autowired
    private MessageSource messageSource;

    @GetMapping({"/login"})
    public String login() {
        return "admin/login";
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        HttpSession session) {

        AdminUser adminUser = adminUserRepository.findAdminUserByUserNameAndPassword(userName, password);
        if (adminUser != null) {
            session.setAttribute("loginUser", adminUser.getNickName());
            session.setAttribute("loginUserId", adminUser.getId());
            return "redirect:/admin/index";
        } else {
            System.out.println(messageSource.getMessage("login.error", null,  LocaleContextHolder.getLocale()));
            session.setAttribute("errorMsg", messageSource.getMessage("login.error", null,  LocaleContextHolder.getLocale()));
            return "admin/login";
        }
    }
    @GetMapping({"", "/", "/index", "/index.html"})
    public String index(Model model) {
        return "admin/index";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("loginUserId");
        request.getSession().removeAttribute("loginUser");
        request.getSession().removeAttribute("errorMsg");
        return "admin/login";
    }
}
