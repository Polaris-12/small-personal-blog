package com.zhiqian.service;

import com.zhiqian.mapper.CommentMapper;
import com.zhiqian.pojo.Comment;
import com.zhiqian.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Value("${comment.avatar}")
    private String avatar;
    @Override
    public int getCommentCount() {
        return commentMapper.getCommentCount();
    }

    @Transactional
    @Override
    public int saveComment(Comment comment) {
        if (comment.getParentId()==-1){
            comment.setParentId(null);
        }
        comment.setCreateTime(new Date());
        if (comment.getAvatar()==null){
            //String email = comment.getEmail();
            //comment.setAvatar(MD5Utils.getGravatar(email));
            comment.setAvatar(avatar);
        }
        return commentMapper.saveComment(comment);
    }

    @Override
    public List<Comment> getCommentsByBlogId(Long blogId) {
        List<Comment> comments = commentMapper.getCommentsByBlogIdAndParentCommentNull(blogId);
        if (comments.size()!=0){
            combineChildren(comments);
        }
        return comments;
    }

    private void combineChildren(List<Comment> comments){
        for(Comment comment:comments){
            List<Comment> replys1=comment.getReplyComments();
            for (Comment reply1:replys1){
                recursively(reply1);
            }

            comment.setReplyComments(tempReplys);
            tempReplys=new ArrayList<>();
        }
    }

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys=new ArrayList<>();

    /**
     * 递归迭代，剥洋葱
     * @param comment 被迭代的对象
     */
    private void recursively(Comment comment){
        tempReplys.add(comment);
        if (comment.getReplyComments().size()>0){
            List<Comment> replys=comment.getReplyComments();
            for (Comment reply:replys){
                tempReplys.add(reply);
                if (reply.getReplyComments().size()>0){
                    recursively(reply);
                }
            }
        }
    }
}
