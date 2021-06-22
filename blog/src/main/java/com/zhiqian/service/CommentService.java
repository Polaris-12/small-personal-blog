package com.zhiqian.service;

import com.zhiqian.pojo.Comment;

import java.util.List;

public interface CommentService {

    int getCommentCount();

    List<Comment> getCommentsByBlogId(Long blogId);

    int saveComment(Comment comment);
}
