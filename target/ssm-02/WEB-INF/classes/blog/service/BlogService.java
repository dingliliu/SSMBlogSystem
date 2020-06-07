package blog.service;

import blog.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogService {
    //根据日期月份分组查询
     List<Blog> countList();
     List<Blog> list(Map<String, Object> map);
     Long getTotal(Map<String, Object> map);
     Blog getById(Integer id);
     Integer update(Blog blog);
     Blog getLastBlog(Integer id);
     Blog getNextBlog(Integer id);
     Integer add(Blog blog);
     Integer delete(Integer id);
    //查询指定的博客类别下的博客数量
     Integer getBlogByTypeId(Integer typeId);
}
