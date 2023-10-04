package ru.simple.blog.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.simple.blog.localization.MessageManager;
import ru.simple.blog.modal.AdminUser;
import ru.simple.blog.repository.AdminUserRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminUserRepository adminUserRepository;

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
            session.setAttribute("errorMsg", MessageManager.getMsg("login.error"));
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
