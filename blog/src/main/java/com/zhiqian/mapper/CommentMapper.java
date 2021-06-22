package com.zhiqian.mapper;

import com.zhiqian.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {

    @Select("select count(*) from comment")
    int getCommentCount();


    int saveComment(@Param("comment") Comment comment);


    List<Comment> getCommentsByBlogIdAndParentCommentNull(@Param("blogId") Long blogId);

    @Select("select * from comment where id=#{parentId}")
    Comment getParentCommentById(@Param("parentId") Long parentId);


    @Select("select * from comment where parentId=#{id}")
    List<Comment> getReplyComment(@Param("id") Long id);
}
