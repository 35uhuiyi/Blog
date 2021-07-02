package com.ny.web.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ny.po.Tag;
import com.ny.po.Type;
import com.ny.queryvo.BlogQuery;
import com.ny.po.Blog;
import com.ny.po.User;
import com.ny.queryvo.SearchBlog;
import com.ny.queryvo.ShowBlog;
import com.ny.service.BlogService;
import com.ny.service.CommentService;
import com.ny.service.TagService;
import com.ny.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT="admin/blogs-input";
    private static final String LIST="admin/blogs";
    private static final String REDIRECT_LIST="redirect:/admin/blogs";

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;

    //博客列表
    @GetMapping("/blogs")
    public String list(Model model, @RequestParam(defaultValue = "1", value = "pageNum")Integer pageNum){
        //按照排序字段 倒序排序
        String orderby = "update_time desc";
        PageHelper.startPage(pageNum,5,orderby);
        List<BlogQuery> list = blogService.getAllBlog();
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(list);
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("pageInfo", pageInfo);
        return LIST;
    }

    //搜索博客
    @PostMapping("/blogs/search")
    public String search(SearchBlog searchBlog,Model model,
                         @RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum){
        List<BlogQuery> blogBySearch = blogService.getBlogBySearch(searchBlog);
        PageHelper.startPage(pageNum,10);
        PageInfo<BlogQuery> blogQueryPageInfo = new PageInfo<>(blogBySearch);
        model.addAttribute("pageInfo", blogQueryPageInfo);
        return "admin/blogs :: blogList";//返回的是一个片段
    }

    //跳转博客新增页面
    @GetMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("blog", new Blog());
        model.addAttribute("tags", tagService.getAllTag());
        return INPUT;
    }


    //跳转编辑修改文章
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        ShowBlog blogById = blogService.getBlogById(id);
        List<Tag> tagByString = tagService.getTagByString(blogById.getTagIds());
        blogById.setTags(tagByString);
        List<Type> allType = typeService.getAllType();
        model.addAttribute("tags", tagService.getAllTag());
        model.addAttribute("blog", blogById);
        model.addAttribute("types", allType);
        return INPUT;
    }

    //编辑修改文章
    @PostMapping("/blogs/{id}")
    public String editPost(ShowBlog showBlog,RedirectAttributes attributes){
        String tagIds = showBlog.getTagIds();
        showBlog.setTags(tagService.getTagByString(tagIds));
        int b = blogService.updateBlog(showBlog);
        if(b==0){
            attributes.addFlashAttribute("message", "修改失败");
        }else{
            attributes.addFlashAttribute("message", "修改成功");
        }
        return REDIRECT_LIST;
    }

    //博客新增
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session){
        blog.setUser((User)session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTypeId(blog.getType().getId());
        blog.setUserId(blog.getUser().getId());
        blog.setTags(tagService.getTagByString(blog.getTagIds()));
        int b = blogService.saveBlog(blog);
        if(b==0){
            attributes.addFlashAttribute("message", "操作失败");
        }else{
            attributes.addFlashAttribute("message", "操作成功");
        }
        return REDIRECT_LIST;
    }


    //删除文章
    @GetMapping("/blogs/{id}/delete")
    public String delete( @PathVariable Long id, RedirectAttributes attributes){
        blogService.deleteBlog(id);
        commentService.deleteCommentByBlogId(id);
        attributes.addFlashAttribute("message", "删除成功");
        return REDIRECT_LIST;
    }


}
