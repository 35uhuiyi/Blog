package com.ny.aspect;

import com.ny.po.Comment;
import com.ny.utils.MailUtils;
import com.ny.utils.ThreadUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yang
 * @version 1.0
 * @date 2021/4/14 17:56
 */
@Aspect
@Component
public class SendMailAspect {

    @Pointcut("execution(* com.ny.service.impl.CommentServiceImpl.saveComment(..))")//声明切面
    public void sendMail(){}
    @After("sendMail()")
    public void doAfter(JoinPoint joinPoint){
        Comment comment = (Comment) joinPoint.getArgs()[0];
        Date date = new Date();//评论时间
        String content = comment.getContent();//获取评论内容
        Long blogId = comment.getBlogId();//Blog
        String nickname = comment.getNickname();//该评论者的昵称
        String email = comment.getEmail();//该评论者的email
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String formatDate = sdf.format(date);
        String emailContent = formatDate+" 用户："+nickname+", Email:"+email+" 在博客:/blog/"+blogId+"中评论了："+content+"。";
        asyncExcute(emailContent);
    }
    private void asyncExcute(String emailContent){
        ThreadUtils.execute(()->send(emailContent));
    }
    private void send(String context){
        MailUtils.sendMail("2665297762@qq.com",context,"博客评论信息");
    }
}
