package com.ny.po;

import jdk.jfr.Label;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {

    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private String flag;
    private Integer views;//浏览次数
    private boolean appreciation;
    private boolean shareStatement;
    private boolean commentabled;
    private boolean published;
    private boolean recommended;
    private Date createTime;
    private Date updateTime;


    private Long typeId;
    private Long userId;

    private String tagIds;
    private String description;

    private Type type;
    private User user;

    private List<Comment> comments = new ArrayList<>();

    //标签展示
    private List<Tag> tags = new ArrayList<>();

    public void init(){
        this.tagIds = tagsToIds(this.getTags());
    }

    //将tags集合转换为tagIds字符串形式：“1,2,3”,用于编辑博客时显示博客的tag
    private String tagsToIds(List<Tag> tags){
        if(!tags.isEmpty()){
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if(flag){
                    ids.append(",");
                }else{
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        }
        return tagIds;
    }






}
