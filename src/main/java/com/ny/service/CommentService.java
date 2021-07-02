package com.ny.service;

import com.ny.po.Comment;

import java.util.List;

/**
 * 评论业务层接口
 */

public interface CommentService {
    //展示该博客下的所有评论
    List<Comment> listCommentByBlogId(Long blogId);

    void saveComment(Comment comment);

    void deleteComment(Comment comment, Long id);

    void deleteCommentByBlogId(Long id);

    Integer getCommentTotal();
}
