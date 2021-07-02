package com.ny.queryvo;

import com.ny.po.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 首页数据实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FirstPageBlog {
    //Blog
    private Long id;
    private String title;
    private String firstPicture;
    private Integer views;
    private Date updateTime;
    private String description;

    //Type
    private String typeName;

    //User
    private String nickname;
    private String avatar;

    private String Tagids;
    private List<Tag> tags;


}
