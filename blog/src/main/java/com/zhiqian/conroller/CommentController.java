package com.zhiqian.conroller;

import com.zhiqian.pojo.Comment;
import com.zhiqian.pojo.User;
import com.zhiqian.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/comment")
    public String addComment(@RequestParam("blogId") Long blogId, Model model){
        model.addAttribute("comments",commentService.getCommentsByBlogId(blogId));
        return "blog :: commentList";
    }


    @PostMapping("/comment")
    public String post(Comment comment, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user!=null){
            comment.setAvatar(user.getAvatar());
            comment.setAdmin(true);
        }
        commentService.saveComment(comment);
        return "redirect:/comment"+"?blogId="+comment.getBlogId();
    }

}
