package blog.service.impl;

import blog.mapper.BlogTypeMapper;
import blog.pojo.BlogType;
import blog.service.BlogTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService {

    @Resource
    private BlogTypeMapper blogTypeMapper;

    @Override
    public List<BlogType> countList() {
        return blogTypeMapper.countList();
    }

    @Override
    public List<BlogType> list(Map<String, Object> map) {
        return blogTypeMapper.list(map);
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return blogTypeMapper.getTotal(map);
    }

    @Override
    public Integer add(BlogType blogType) {
        return blogTypeMapper.add(blogType);
    }

    @Override
    public Integer update(BlogType blogType) {
        return blogTypeMapper.update(blogType);
    }

    @Override
    public Integer delete(Integer id) {
        return blogTypeMapper.delete(id);
    }

    @Override
    public Integer getBlogByTypeId(Integer typeId) {
        return blogTypeMapper.getBlogByTypeId(typeId);
    }

    @Override
    public BlogType getById(Integer id) {
        return blogTypeMapper.getById(id);
    }
}
