package com.ny.queryvo;

import lombok.Data;

/**
 * 搜索博客管理列表
 */
@Data
public class SearchBlog {
    private String title;
    private Long typeId;

    public SearchBlog() {
    }
}
