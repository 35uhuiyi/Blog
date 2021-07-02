package com.ny.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 把博客和标签关系存到关系库中的类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogAndTag {
    private Long tagId;
    private Long blogId;
}
