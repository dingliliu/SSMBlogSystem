package blog.service;

import blog.pojo.BlogType;

import java.util.List;
import java.util.Map;


public interface BlogTypeService {
    //查询所有博客类型 以及对应的博客数量
    List<BlogType> countList();
    List<BlogType> list(Map<String,Object> map);
    Long getTotal(Map<String,Object> map);
    Integer add(BlogType blogType);
    Integer update(BlogType blogType);
    Integer delete(Integer id);
    Integer getBlogByTypeId(Integer typeId);
    BlogType getById(Integer id);
}
