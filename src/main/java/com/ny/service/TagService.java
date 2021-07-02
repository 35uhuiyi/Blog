package com.ny.service;

import com.ny.po.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TagService {

    int saveTag(Tag tag);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    List<Tag> getAllTag();

    List<Tag> getBlogTag();

    List<Tag> getTagByString(String text); //从字符串中获取tag集合

    int updateTag(Tag tag);

    int deleteTag(Long id);

}
