package com.ny.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ny.po.Tag;
import com.ny.queryvo.FirstPageBlog;
import com.ny.service.BlogService;
import com.ny.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tags(@PathVariable Long id, Model model, @RequestParam(defaultValue = "1", value = "pageNum")Integer pageNum){
        List<Tag> tags = tagService.getBlogTag();
        if(id == -1){
            id = tags.get(0).getId();
        }
        PageHelper.startPage(pageNum, 5);
        List<FirstPageBlog> byTagId = blogService.getByTagId(id);
        for (FirstPageBlog firstPageBlog : byTagId) {
            firstPageBlog.setTags(tagService.getTagByString(firstPageBlog.getTagids()));
        }
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(byTagId);
        model.addAttribute("activeTagId", id);
        model.addAttribute("tags", tags);
        model.addAttribute("pageInfo", pageInfo);
        return "tags";
    }

}
