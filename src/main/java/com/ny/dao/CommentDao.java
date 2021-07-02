package com.ny.dao;

import com.ny.po.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论持久层接口
 */
@Mapper
@Repository
public interface CommentDao {

    //根据创建时间倒序
    List<Comment> findByBlogIdParentIdNull(@Param("blogId") Long blogId,
                                           @Param("blogParentId") Long blogParentId);

    //查询一级回复
    List<Comment> findByBlogIdParentIdNotNull(@Param("blogId") Long blogId,
                                              @Param("id") Long id);

    //查询二级回复
    List<Comment> findByBlogIdAndReplayId(@Param("blogId") Long blogId,
                                          @Param("childId") Long childId);

    //添加一个评论
    void saveComment(Comment comment);

    //删除
    void deleteComment(Long id);

    void deleteCommentByBlogId(Long id);

    Integer getCommentTotal();
}
