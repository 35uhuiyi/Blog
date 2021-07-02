package com.ny.queryvo;

import com.ny.po.Comment;
import com.ny.po.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailedBlog {
    private Long id;
    private String firstPicture;
    private String title;
    private String content;

    private Integer views;
    private Date updateTime;
    private boolean commentabled;
    private boolean shareStatement;
    private boolean appreciation;
    private String nickname;
    private String avatar;

    private String typeName;

    private String tagIds;
    private List<Tag> tags;

    private List<Comment> comments = new ArrayList<>();
}
