package com.ny.queryvo;

import com.ny.po.Tag;
import com.ny.po.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 显示数据实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogQuery {

    private Long id;
    private String title;
    private Date updateTime;
    private Long typeId;
    private Type type;
    private boolean recommended;
    private boolean published;

}
