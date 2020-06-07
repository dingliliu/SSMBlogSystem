package blog.mapper;

import java.util.List;
import java.util.Map;

import blog.pojo.Praise;
import org.apache.ibatis.annotations.Param;

public interface PraiseMapper {
    int add(Praise praise);
    int update(Praise praise);
    List<Praise> list(Map<String, Object> map);
    Long getTotal(Map<String, Object> map);
    Integer delete(Integer id);
    Praise getOne(@Param("bloggerId") Integer bloggerId, @Param("objectId") Integer objectId ,@Param("objectType") Integer objectType);
}
