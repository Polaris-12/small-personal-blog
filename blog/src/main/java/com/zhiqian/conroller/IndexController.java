package com.zhiqian.conroller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhiqian.pojo.Blog;
import com.zhiqian.pojo.Type;
import com.zhiqian.service.BlogService;
import com.zhiqian.service.CommentService;
import com.zhiqian.service.TypeService;
import com.zhiqian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @GetMapping("/blog")
    public String blog(@RequestParam("id") Long id,Model model){
        Blog blog = blogService.getBlogAndConvert(id);
        model.addAttribute("blog",blog);
        model.addAttribute("comments",commentService.getCommentsByBlogId(id));
        return "blog";
    }

    @GetMapping("/type")
    public String type(Long id,@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,Model model){
        int typeCount = typeService.getTypeCount();
        List<Type> types = typeService.listType();
        model.addAttribute("typeCount",typeCount);
        model.addAttribute("types",types);

        if (id==null){
            Long typeId = types.get(0).getId();
            PageHelper.startPage(pageNum,4);
            List<Blog> blogs = blogService.queryBlogByTypeId(typeId);
            PageInfo pageInfo=new PageInfo(blogs);
            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("typeId",typeId);
        }else{
            PageHelper.startPage(pageNum,4);
            List<Blog> blogs = blogService.queryBlogByTypeId(id);
            PageInfo pageInfo=new PageInfo(blogs);
            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("typeId",id);
        }
        return "types";
    }

    @GetMapping("/archives")
    public String archives(Model model){
        int blogIssueCount = blogService.getBlogIssueCount();
        model.addAttribute("count",blogIssueCount);
        model.addAttribute("maps",blogService.getBlogArchives());
        return "archives";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/")
    public String index(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, Model model){
        model.addAttribute("count",blogService.getBlogIssueCount());
        model.addAttribute("recommendBlogs",blogService.listRecommendBlog());
        model.addAttribute("user",userService.getUser());

        PageHelper.startPage(1,5);
        List<Type> types = typeService.listType();
        PageInfo typesPageInfo=new PageInfo(types);
        model.addAttribute("typesPageInfo",typesPageInfo);

        PageHelper.startPage(pageNum,5," createTime desc");
        List<Blog> blogs = blogService.listIssueBlog();
        PageInfo pageInfo=new PageInfo(blogs);
        model.addAttribute("pageInfo",pageInfo);
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,@RequestParam("query") String query,Model model){
        PageHelper.startPage(pageNum,5);
        List<Blog> blogs = blogService.query(query);
        PageInfo pageInfo=new PageInfo(blogs);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("query",query);
        return "search";
    }
}
