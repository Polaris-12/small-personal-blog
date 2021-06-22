package com.zhiqian.service;

import com.zhiqian.mapper.BlogMapper;
import com.zhiqian.pojo.Blog;
import com.zhiqian.utils.MarkdownUtils;
import com.zhiqian.vo.RecommendBlog;
import com.zhiqian.vo.SearchBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogMapper blogMapper;

    @Transactional
    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        return blogMapper.saveBlog(blog);
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogMapper.getBlogById(id);
    }

    @Override
    public int deleteBlog(Long id) {
        return blogMapper.deleteBlogById(id);
    }

    @Transactional
    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        return blogMapper.updateBlog(blog);
    }

    @Override
    public List<Blog> listBlog(SearchBlog searchBlog) {
        return blogMapper.listBlog(searchBlog);
    }

    @Override
    public int getBlogCount() {
        return blogMapper.getBlogCount();
    }

    @Override
    public int deleteBlogByTypeId(Long id) {
        return blogMapper.deleteBlogByTypeId(id);
    }

    @Override
    public int getBlogIssueCount() {
        return blogMapper.getBlogIssueCount();
    }

    @Override
    public List<Blog> listIssueBlog() {
        List<Blog> blogs = blogMapper.listIssueBlog();
        if (blogs.size()!=0){
            for (Blog blog:blogs){
                String str = blog.getContent().substring(0, 200).replace("##","  ").replace("**","  ")+"...";
                blog.setContent(str);
            }
        }
        return blogs;
    }

    @Override
    public List<RecommendBlog> listRecommendBlog() {
        return blogMapper.listRecommendBlog();
    }

    @Override
    public List<Blog> query(String query) {
        String str="%"+query+"%";
        List<Blog> blogs = blogMapper.query(str);
        if (blogs.size()!=0){
            for (Blog blog:blogs){
                String string = blog.getContent().substring(0, 200).replace("##","  ").replace("**","  ")+"...";
                blog.setContent(string);
            }
        }
        return blogs;
    }

    @Override
    public List<Blog> queryBlogByTypeId(Long id) {
        List<Blog> blogs = blogMapper.queryBlogByTypeId(id);
        if (blogs.size()!=0){
            for (Blog blog:blogs){
                String str = blog.getContent().substring(0, 200).replace("##","  ").replace("**","  ")+"...";
                blog.setContent(str);
            }
        }

        return blogs;
    }

    @Transactional
    @Override
    public Blog getBlogAndConvert(Long id) {
        Blog blog = blogMapper.getBlogById(id);
        blogMapper.updateBlogViews(id);
        if (blog!=null){
            String s = MarkdownUtils.markdownToHtmlExtensions(blog.getContent());
            blog.setContent(s);
        }
        return blog;
    }

    @Override
    public Map<String, List<Blog>> getBlogArchives() {
        Map<String,List<Blog>> map=new HashMap<>();
        List<String> blogYear = blogMapper.getBlogYear();
        if (blogYear.size()>0){
            for(String s:blogYear){
                map.put(s,blogMapper.listBlogByYear(s));
            }
        }

        return map;
    }
}
