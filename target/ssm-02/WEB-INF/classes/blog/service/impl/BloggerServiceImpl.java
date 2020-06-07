package blog.service.impl;

import blog.mapper.BloggerMapper;
        import blog.pojo.Blogger;
        import blog.service.BloggerService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;
        import java.util.Map;

@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService {
    @Autowired
    private BloggerMapper bloggerMapper;

    @Override
    public Blogger getById(Integer id) {
        return bloggerMapper.getById(id);
    }

    @Override
    public Blogger login(String name, String password) {
        return bloggerMapper.login(name, password);
    }

    @Override
    public Blogger getByName(String name) {
        return bloggerMapper.getByName(name);
    }

    @Override
    public boolean insert(Blogger bean) {
        return bloggerMapper.insert(bean);
    }

    @Override
    public boolean update(Blogger bean) {
        return bloggerMapper.update(bean);
    }

    @Override
    public List<Blogger> getList() {
        return bloggerMapper.getList();
    }

    @Override
    public List<Blogger> list(Map<String, Object> map) {
        return bloggerMapper.list(map);
    }
    @Override
    public Long getTotal(Map<String, Object> map) {
        return bloggerMapper.getTotal(map);
    }

}
