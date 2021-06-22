package com.zhiqian.conroller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhiqian.pojo.Blog;
import com.zhiqian.pojo.Type;
import com.zhiqian.pojo.User;
import com.zhiqian.service.BlogService;
import com.zhiqian.service.TypeService;
import com.zhiqian.vo.SearchBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @GetMapping("/essay")
    public String toEssayPage(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,SearchBlog searchBlog, Model model){
        List<Type> types = typeService.listType();
        model.addAttribute("types",types);
        model.addAttribute("searchBlog",searchBlog);

        PageHelper.startPage(pageNum,6," updateTime desc");
        List<Blog> blogs = blogService.listBlog(searchBlog);
        PageInfo pageInfo=new PageInfo(blogs);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/essay";
    }


    @GetMapping("/deleteEssay")
    public String delete(@RequestParam("id") Long id,RedirectAttributes attributes){
        int i = blogService.deleteBlog(id);
        if (i>0){
            attributes.addFlashAttribute("message","操作成功！");
        }else {
            attributes.addFlashAttribute("message","操作失败！");
        }
        return "redirect:/admin/essay";
    }

    @PostMapping("/addEssay")
    public String add(Blog blog, RedirectAttributes attributes, HttpSession session){
        User user = (User) session.getAttribute("user");
        blog.setUser(user);
        int i=0;
        if (blog.getId()==null){
            i = blogService.saveBlog(blog);
        }else{
            i = blogService.updateBlog(blog);
        }
        if (i>0){
            attributes.addFlashAttribute("message","操作成功！");
        }else {
            attributes.addFlashAttribute("message","操作失败！");
        }
        return "redirect:/admin/essay";
    }

    @GetMapping("/toUpdate")
    public String toUpdatePage(@RequestParam("id") Long id,Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("blog",blogService.getBlogById(id));
        return "admin/write";
    }

}
