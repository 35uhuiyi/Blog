package com.ny.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ny.po.Tag;
import com.ny.po.Type;
import com.ny.queryvo.DetailedBlog;
import com.ny.queryvo.FirstPageBlog;
import com.ny.queryvo.RecommendBlog;
import com.ny.service.BlogService;
import com.ny.service.CommentService;
import com.ny.service.TagService;
import com.ny.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * 首页控制器
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "1", value = "pageNum")Integer pageNum,
                        RedirectAttributes attributes){
        PageHelper.startPage(pageNum, 5);
        List<FirstPageBlog> PageBlog = blogService.getFirstPageBlog();
        int SumNum = blogService.getSumNum();
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(PageBlog);
        List<Type> allType = typeService.getBlogType();
        List<Tag> allTag = tagService.getBlogTag();
        List<RecommendBlog> recommendedBlog = blogService.getRecommendedBlog();
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("SumNum", SumNum);
        model.addAttribute("types", allType);
        model.addAttribute("tags", allTag);
        model.addAttribute("recommendBlogs", recommendedBlog);
        return "index";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model){
        DetailedBlog detailedBlog = blogService.getDetailedBlog(id);
        detailedBlog.setTags(tagService.getTagByString(detailedBlog.getTagIds()));
        model.addAttribute("blog", detailedBlog);
        return "blog";
    }

    @PostMapping("/search")
    public String search(Model model,@RequestParam(defaultValue = "1", value = "pageNum")Integer pageNum,
                         @RequestParam String query){
        PageHelper.startPage(pageNum, 5);
        List<FirstPageBlog> searchBlog = blogService.getSearchBlog(query);
        for (FirstPageBlog firstPageBlog : searchBlog) {
            firstPageBlog.setTags(tagService.getTagByString(firstPageBlog.getTagids()));
        }
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(searchBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/footer/newblogs")
    public String newblogList(Model model){
        model.addAttribute("newBlogs", blogService.getThreeNewBlogs());
        return "_fragments :: newblogList";
    }

    @GetMapping("/footer/blogMessage")
    public String blogMessage(Model model){
        model.addAttribute("totalBlogs", blogService.getBlogTotal());
        model.addAttribute("totalViews", blogService.getBlogViewTotal());
        model.addAttribute("totalComments", commentService.getCommentTotal());
        return "_fragments :: blogMessage";
    }
}
