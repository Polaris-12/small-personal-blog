package com.zhiqian.conroller;

import com.zhiqian.mapper.CommentMapper;
import com.zhiqian.pojo.Blog;
import com.zhiqian.pojo.User;
import com.zhiqian.service.BlogService;
import com.zhiqian.service.TypeService;
import com.zhiqian.service.UserService;
import com.zhiqian.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.security.provider.MD5;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class MainController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserService userService;

    @GetMapping("/main")
    public String toIndex(Model model){
        int typeCount = typeService.getTypeCount();
        int blogCount = blogService.getBlogCount();
        int commentCount = commentMapper.getCommentCount();
        int userCount = userService.getUserCount();
        model.addAttribute("typeCount",typeCount);
        model.addAttribute("blogCount",blogCount);
        model.addAttribute("commentCount",commentCount);
        model.addAttribute("userCount",userCount);
        return "admin/main";
    }

    @PostMapping("/editUser")
    public String editUser(User user, RedirectAttributes attributes, HttpSession session){
        if (user.getPassword()!=null && user.getPassword() !=""){
            String code = MD5Utils.code(user.getPassword());
            user.setPassword(code);
        }
        int i = userService.update(user);
        if (i>0){
            attributes.addFlashAttribute("message","档案更新成功！");
            user.setPassword(null);
            session.setAttribute("user",user);
        }else {
            attributes.addFlashAttribute("message","档案更新失败！");
        }
        return "redirect:/admin/set";
    }

    @GetMapping("/comment")
    public String toCommentPage(){
        return "admin/comment";
    }



    @GetMapping("/set")
    public String toSetPage(){
        return "admin/set";
    }

    @GetMapping("/write")
    public String toWriterPage(Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("blog",new Blog());
        return "admin/write";
    }
}
