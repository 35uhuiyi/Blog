package com.ny.web.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ny.po.Tag;
import com.ny.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    //分页查询标签列表    @RequestParam：将请求参数绑定到你控制器的方法参数上(是springmvc中接收普通参数的注解，name属性)
    public String tags(@RequestParam(required = false, defaultValue = "1", value = "pageNum")int pageNum, Model model){
        PageHelper.startPage(pageNum, 5);
        List<Tag> allTag = tagService.getAllTag();
        PageInfo<Tag> pageInfo = new PageInfo<>(allTag);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/tags";
    }


    //新增标签
    @GetMapping("tags/input")
    public String input(Model model){
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }


    //跳转修改页面
    @GetMapping("tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/tags-input";
    }


    //新增标签
    @PostMapping("/tags")
    public String post(Tag tag, RedirectAttributes attributes){
        Tag t = tagService.getTagByName(tag.getName());
        if(t !=null){
            //已有该标签
            attributes.addFlashAttribute("msg", "不能添加重复的标签");
            return "redirect:/admin/tags/input";
        }else{
            attributes.addFlashAttribute("msg", "添加成功！");
        }
        tagService.saveTag(tag);
        return "redirect:/admin/tags";  //不能直接跳转到tags页面，否则不会显示tag数据(没经过tags方法)
    }


    //编辑修改
    @PostMapping("/tags/{id}")
    public String editTag(@PathVariable Long id, Tag tag, RedirectAttributes attributes){
        Tag t = tagService.getTagByName(tag.getName());
        if(t != null){
            attributes.addFlashAttribute("msg", "不能添加重复的标签");
            return "redirect:/admin/tags/input";
        }else{
            attributes.addFlashAttribute("msg", "修改成功");
        }
        tagService.updateTag(tag);
        return "redirect:/admin/tags";
    }


    //删除标签
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("msg", "删除成功！");
        return "redirect:/admin/tags";
    }

}
