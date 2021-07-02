package com.ny.web;

import com.ny.po.Tag;
import com.ny.po.Type;
import com.ny.service.TagService;
import com.ny.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AboutShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private TypeService typeService;

    @GetMapping("/about")
    public String about(Model model){
        List<Tag> blogTag = tagService.getBlogTag();
        List<Type> blogType = typeService.getBlogType();
        model.addAttribute("types", blogType);
        model.addAttribute("tags", blogTag);
        return "about";
    }

    @GetMapping("/jfczG")
    public String jfcz(){
        return "jfczG";
    }

    @GetMapping("/elsG")
    public String elsG(){
        return "elsG";
    }
}
