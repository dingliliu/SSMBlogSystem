package blog.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import blog.mapper.PraiseMapper;
import blog.pojo.Praise;
import blog.service.PraiseService;
@Service("praiseService")
public class PraiseServiceImpl implements PraiseService{

    @Resource
    private PraiseMapper PraiseMapper;

    public int add(Praise praise) {
        return PraiseMapper.add(praise);
    }

    public List<Praise> list(Map<String, Object> map) {
        return PraiseMapper.list(map);
    }

    public Long getTotal(Map<String, Object> map) {
        return PraiseMapper.getTotal(map);
    }

    public Integer delete(Integer id) {
        return PraiseMapper.delete(id);
    }

    public int update(Praise praise) {
        return PraiseMapper.update(praise);
    }

    public Praise getOne(Integer bloggerId,Integer objectId ,Integer objectType){
        return PraiseMapper.getOne(bloggerId, objectId, objectType);
    }

}
