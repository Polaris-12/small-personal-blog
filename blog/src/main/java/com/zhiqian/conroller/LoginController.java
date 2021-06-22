package com.zhiqian.conroller;

import com.zhiqian.pojo.User;
import com.zhiqian.service.UserService;
import com.zhiqian.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/toLogin")
    public String toLogin(HttpSession session){
        if (session.getAttribute("user")!=null){
            return "forward:/admin/main";
        }
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.checkUser(username, MD5Utils.code(password));
        if (user!=null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "redirect:/admin/main";
        }
        attributes.addFlashAttribute("msg","您输入的用户名或密码错误");
        return "redirect:/toLogin";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/toLogin";
    }
}
