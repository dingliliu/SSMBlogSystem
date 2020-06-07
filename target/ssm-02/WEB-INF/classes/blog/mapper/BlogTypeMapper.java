package blog.mapper;

import blog.pojo.BlogType;

import java.util.List;
import java.util.Map;

public interface BlogTypeMapper {
    List<BlogType> countList();
    List<BlogType> list(Map<String,Object> map);
    Long getTotal(Map<String,Object> map);
    Integer add(BlogType blogType);
    Integer update(BlogType blogType);
    Integer delete(Integer id);
    Integer getBlogByTypeId(Integer typeId);
    BlogType getById(Integer id);
}
