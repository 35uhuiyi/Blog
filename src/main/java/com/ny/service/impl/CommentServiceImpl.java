package com.ny.service.impl;

import com.ny.dao.BlogDao;
import com.ny.dao.CommentDao;
import com.ny.po.Comment;
import com.ny.service.CommentService;
import com.ny.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    private BlogDao blogDao;

    @Autowired
    private CommentDao commentDao;

    //存放一个顶级评论下的所有子评论
    private List<Comment> tempReplys = new ArrayList<>();


    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        //查询父节点
        List<Comment> comments = commentDao.findByBlogIdParentIdNull(blogId, Long.parseLong("-1"));
        for (Comment comment : comments) {
            Long id = comment.getId();
            String parentNickname = comment.getNickname();
            List<Comment> childComments = commentDao.findByBlogIdParentIdNotNull(blogId, id);
            //查询出子评论
            combineChildren(blogId, childComments,parentNickname);
            comment.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return comments;
    }

    private void combineChildren(Long blogId, List<Comment> childComments, String parentNickname) {
        //判断是否有一级子评论
        if(childComments.size()>0){
            //循环找出子评论的id
            for (Comment childComment : childComments) {
                String pNickname = childComment.getNickname();
                childComment.setParentNickname(parentNickname);
                tempReplys.add(childComment);
                Long childId = childComment.getId();
                recursively(blogId, childId, pNickname);

            }
        }
    }

    private void recursively(Long blogId, Long childId, String pNickname) {
        //根据子一级评论的id找到子二级评论
        List<Comment> replayComments = commentDao.findByBlogIdAndReplayId(blogId, childId);
        if(replayComments.size()>0){
            for (Comment replayComment : replayComments) {
                String parentNickname = replayComment.getNickname();
                replayComment.setParentNickname(pNickname);
                Long replayId = replayComment.getId();
                tempReplys.add(replayComment);
                recursively(blogId, replayId, parentNickname);
            }
        }
    }

    @Override
    public void saveComment(Comment comment) {
        Date date = new Date();//评论时间
        comment.setCreateTime(date);
        commentDao.saveComment(comment);
    }


    @Override
    public void deleteComment(Comment comment, Long id) {
        commentDao.deleteComment(id);
    }

    @Override
    public void deleteCommentByBlogId(Long id) {
        commentDao.deleteCommentByBlogId(id);
    }

    @Override
    public Integer getCommentTotal() {
        return commentDao.getCommentTotal();
    }


}
