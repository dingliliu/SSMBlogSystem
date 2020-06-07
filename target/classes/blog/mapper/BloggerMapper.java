package blog.mapper;

import blog.pojo.Blogger;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface BloggerMapper {
    Blogger getById(Integer id);
    Blogger login(@Param("name") String name, @Param("password") String password);
    Blogger getByName(String name);
    boolean insert(Blogger bean);
    boolean update(Blogger bean);
    List<Blogger> getList();
    List<Blogger> list(Map<String, Object> map);
    Long getTotal(Map<String, Object> map);
}
