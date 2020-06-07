package blog.service;

import blog.pojo.Blogger;

import java.util.List;
import java.util.Map;

public interface BloggerService {
    Blogger getById(Integer id);
    Blogger login(String name, String password);
    Blogger getByName(String name);
    boolean insert(Blogger bean);
    boolean update(Blogger bean);
    List<Blogger> getList();
    List<Blogger> list(Map<String, Object> map);
    Long getTotal(Map<String, Object> map);
}
