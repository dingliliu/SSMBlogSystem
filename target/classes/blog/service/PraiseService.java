package blog.service;

import blog.pojo.Praise;

import java.util.List;
import java.util.Map;

public interface PraiseService {
    int add(Praise praise);
    int update(Praise praise);
    List<Praise> list(Map<String, Object> map);
    Long getTotal(Map<String, Object> map);
    Integer delete(Integer id);
    Praise getOne(Integer bloggerId,Integer objectId ,Integer objectType);
}
