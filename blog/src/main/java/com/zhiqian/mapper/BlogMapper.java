package com.zhiqian.mapper;

import com.zhiqian.pojo.Blog;
import com.zhiqian.vo.RecommendBlog;
import com.zhiqian.vo.SearchBlog;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogMapper {


    int saveBlog(@Param("blog") Blog blog);


    List<Blog> listBlog(@Param("searchBlog") SearchBlog searchBlog);

    @Select("select count(*) from blog")
    int getBlogCount();

    @Delete("delete from blog where id=#{id}")
    int deleteBlogById(@Param("id") Long id);

    @Delete("delete from blog where typeId=#{id}")
    int deleteBlogByTypeId(@Param("id") Long id);


    Blog getBlogById(@Param("id") Long id);


    int updateBlog(@Param("blog") Blog blog);

    @Select("select count(*) from blog where published=1")
    int getBlogIssueCount();

    List<Blog> listIssueBlog();

    //最新推荐
    List<RecommendBlog> listRecommendBlog();

    //模糊查找
    List<Blog> query(@Param("query") String query);

    List<Blog> queryBlogByTypeId(@Param("id") Long id);

    @Update("update blog set views=views+1 where id=#{id}")
    int updateBlogViews(@Param("id") Long id);

    @Select("select date_format(createTime,'%Y') as year from blog group by year order by year desc")
    List<String> getBlogYear();


    List<Blog> listBlogByYear(@Param("year") String year);
}
