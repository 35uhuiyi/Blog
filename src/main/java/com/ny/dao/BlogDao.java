package com.ny.dao;

import com.ny.po.Blog;
import com.ny.po.BlogAndTag;
import com.ny.queryvo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 博客持久层接口
 */
@Mapper
@Repository
public interface BlogDao {

    ShowBlog getBlogById(Long id);

    List<Blog> getAllBlog();

    List<BlogQuery> getAllBlogQuery();

    List<Long>getBlogByTypeId(Long id);  //分类

    List<Long>getBlogByTagId(Long id); //标签

    int saveBlogAndTag(BlogAndTag blogAndTag);

    void deleteBlogAndTag(Long id);

    int saveBlog(Blog blog);

    int updateBlog(ShowBlog showBlog);

    void deleteBlog(Long id);

    List<BlogQuery> searchByTitleOrTypeOrRecommend(SearchBlog searchBlog);

    List<FirstPageBlog> getFirstPageBlog();

    List<RecommendBlog> getAllRecommendBlog();

//    List<FirstPageBlog> getNewBlog();

    List<FirstPageBlog> getSearchBlog(String query);

    DetailedBlog getDetailedBlog(Long id);

    void updateViews(Long id);

//    //    根据博客id查询出评论数量
//    int getCommentCountById(Long id);

    List<FirstPageBlog> getByTypeId(Long typeId);
    List<FirstPageBlog> getByTagId(Long tagId);


    Integer getBlogTotal();

    Integer getBlogViewTotal();

    int getSumNum();



    List<String> findGroupYearAndMonth();  //查询所有年月，返回一个集合

    List<FirstPageBlog> findByYearAndMonth(@Param("yam")String s);  //按年月查询博客

    List<BlogQuery> getThreeNewBlogs();

    //Integer getBlogCommentTotal();

}
