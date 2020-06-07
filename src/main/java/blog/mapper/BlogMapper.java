package blog.mapper;

import blog.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface BlogMapper{
     List<Blog> countList();
     List<Blog> list(Map<String, Object> map);
     Long getTotal(Map<String, Object> map);
     Blog getById(Integer id);
     Integer update(Blog blog);
     Blog getLastBlog(Integer id);
     Blog getNextBlog(Integer id);
     Integer add(Blog blog);
     Integer delete(Integer id);
     Integer getBlogByTypeId(Integer typeId);
}
