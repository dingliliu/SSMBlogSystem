package blog.service;

import blog.pojo.Link;

import java.util.List;
import java.util.Map;

public interface LinkService {
    int add(Link link);
    int update(Link link);
    List<Link> list(Map<String,Object> map);
    Integer delete(Integer id);
    Long getTotal(Map<String,Object> map);
}
