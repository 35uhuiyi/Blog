package com.ny.service;

import com.ny.queryvo.*;
import com.ny.po.Blog;

import java.util.List;
import java.util.Map;

/**
 * 博客列表的业务层接口
 */
public interface BlogService {

    ShowBlog getBlogById(Long id);

    List<BlogQuery> getAllBlog();

    int saveBlog(Blog blog);

    int updateBlog(ShowBlog showBlog);

    void deleteBlog(Long id);

    List<BlogQuery> getBlogBySearch(SearchBlog searchBlog);

    List<FirstPageBlog> getFirstPageBlog();

    List<RecommendBlog> getRecommendedBlog();

    List<FirstPageBlog> getSearchBlog(String query);

    DetailedBlog getDetailedBlog(Long id);

    //根据TypeId获取博客列表，在分类页进行的操作
    List<FirstPageBlog> getByTypeId(Long typeId);
    List<FirstPageBlog> getByTagId(Long tagId);

    Integer getBlogTotal();

    Integer getBlogViewTotal();


    //根据typeId查找所有博客ids
    List<Long>getBlogByTypeId(long id);

    int getSumNum();

    void updateViews(Long id);

    Map<String,List<FirstPageBlog>> archiveBlog();  //归档博客

    List<BlogQuery> getThreeNewBlogs();

    //Integer getBlogCommentTotal();

    //Integer getBlogMessageTotal();

}
