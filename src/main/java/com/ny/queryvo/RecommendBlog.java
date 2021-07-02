package com.ny.queryvo;

import lombok.Data;

/**
 * 推荐博客实体类
 */
@Data
public class RecommendBlog {
    private Long id;
    private String title;
    private String firstPicture;
    private boolean recommended;
    public RecommendBlog() {
    }
}
