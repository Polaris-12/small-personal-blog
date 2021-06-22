package com.zhiqian.service;

import com.zhiqian.pojo.Blog;
import com.zhiqian.vo.RecommendBlog;
import com.zhiqian.vo.SearchBlog;

import java.util.List;
import java.util.Map;


public interface BlogService {

    int saveBlog(Blog blog);

    Blog getBlogById(Long id);

    int deleteBlog(Long id);

    int updateBlog(Blog blog);

    List<Blog> listBlog(SearchBlog searchBlog);

    int getBlogCount();

    int deleteBlogByTypeId(Long id);

    int getBlogIssueCount();

    List<Blog> listIssueBlog();

    List<RecommendBlog> listRecommendBlog();

    List<Blog> query(String query);

    List<Blog> queryBlogByTypeId(Long id);

    Blog getBlogAndConvert(Long id);

    Map<String,List<Blog>> getBlogArchives();
}
