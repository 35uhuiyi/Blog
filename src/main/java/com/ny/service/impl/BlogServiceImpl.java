package com.ny.service.impl;

import com.ny.NotFoundException;
import com.ny.dao.BlogDao;
import com.ny.po.Blog;
import com.ny.po.BlogAndTag;
import com.ny.po.Tag;
import com.ny.queryvo.*;
import com.ny.service.BlogService;
import com.ny.utils.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Override
    public ShowBlog getBlogById(Long id) {
        return blogDao.getBlogById(id);
    }

    @Override
    public List<BlogQuery> getAllBlog() {
        return blogDao.getAllBlogQuery();
    }

    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        //保存博客后才能获取自增的id
        blogDao.saveBlog(blog);
        Long id = blog.getId();
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            blogAndTag = new BlogAndTag(tag.getId(), id);
            blogDao.saveBlogAndTag(blogAndTag);
        }
        return 1;

    }

    @Override
    public int updateBlog(ShowBlog showBlog) {
        Long id = showBlog.getId();
        List<Tag> tags = showBlog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            blogAndTag = new BlogAndTag(tag.getId(), id);
            blogDao.deleteBlogAndTag(id);
            blogDao.saveBlogAndTag(blogAndTag);
        }
        showBlog.setUpdateTime(new Date());
        return blogDao.updateBlog(showBlog);
    }

    @Override
    public void deleteBlog(Long id) {
        blogDao.deleteBlog(id);
    }

    @Override
    public List<BlogQuery> getBlogBySearch(SearchBlog searchBlog) {
        return blogDao.searchByTitleOrTypeOrRecommend(searchBlog);
    }

    @Override
    public List<FirstPageBlog> getFirstPageBlog() {
        return blogDao.getFirstPageBlog();
    }


    @Override
    public List<RecommendBlog> getRecommendedBlog() {
        return blogDao.getAllRecommendBlog();
    }

    @Override
    public List<FirstPageBlog> getSearchBlog(String query) {
        return blogDao.getSearchBlog(query);
    }

    @Override
    public DetailedBlog getDetailedBlog(Long id) {
        DetailedBlog detailedBlog = blogDao.getDetailedBlog(id);
        if(detailedBlog == null){
            throw new NotFoundException("该博客不存在！");
        }
        String content = detailedBlog.getContent();
        detailedBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        blogDao.updateViews(id);
        return detailedBlog;
    }

    @Override
    public List<FirstPageBlog> getByTypeId(Long typeId) {
        return blogDao.getByTypeId(typeId);
    }

    @Override
    public List<FirstPageBlog> getByTagId(Long tagId) {
        return blogDao.getByTagId(tagId);
    }

    @Override
    public Integer getBlogTotal() {
        return blogDao.getBlogTotal();
    }

    @Override
    public Integer getBlogViewTotal() {
        return blogDao.getBlogViewTotal();
    }

    @Override
    public List<Long> getBlogByTypeId(long id) {
        return blogDao.getBlogByTypeId(id);
    }

    @Override
    public int getSumNum() {
        return blogDao.getSumNum();
    }

    @Override
    public void updateViews(Long id) {
        blogDao.updateViews(id);
    }


    //归档
    @Override
    public Map<String, List<FirstPageBlog>> archiveBlog() {
        List<String> group = blogDao.findGroupYearAndMonth();
        HashSet<String> set = new HashSet<>(group);//去掉重复
        Map<String, List<FirstPageBlog>> map = new HashMap<>();
        for (String s : set) {
            map.put(s, blogDao.findByYearAndMonth(s));
        }
        return map;
    }

    @Override
    public List<BlogQuery> getThreeNewBlogs() {
        return blogDao.getThreeNewBlogs();
    }

}
