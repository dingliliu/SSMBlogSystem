package blog.mapper;

import blog.pojo.Link;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface LinkMapper {
        int add(Link link);
        int update(Link link);
         List<Link> list(Map<String,Object> map);
         Long getTotal(Map<String,Object> map);
         Integer delete(Integer id);
    }

