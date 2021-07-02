package com.ny.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ny.po.Type;
import com.ny.queryvo.FirstPageBlog;
import com.ny.service.BlogService;
import com.ny.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;


    @GetMapping("/types/{id}")
    public String types(@PathVariable Long id, Model model,
        @RequestParam(required = false,defaultValue = "1", value = "pageNum")int pageNum){
        List<Type> types = typeService.getAllTypeAndBlog();
        if (id==-1){
            //点击more,,默认第一个分类
            id = types.get(0).getId();
        }
        PageHelper.startPage(pageNum, 5);
        List<FirstPageBlog> byTypeId = blogService.getByTypeId(id);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(byTypeId);
        model.addAttribute("activeTypeId", id);
        model.addAttribute("types", types);
        model.addAttribute("pageInfo", pageInfo);
        return "types";
    }
}
