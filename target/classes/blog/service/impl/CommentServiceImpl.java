package blog.service.impl;

import java.util.List;
        import java.util.Map;

        import javax.annotation.Resource;

        import org.springframework.stereotype.Service;

        import blog.mapper.CommentMapper;
        import blog.pojo.Comment;
        import blog.service.CommentService;

@Service("commentService")
public class CommentServiceImpl implements CommentService{

    @Resource
    private CommentMapper commentMapper;

    public int add(Comment comment) {
        return commentMapper.add(comment);
    }

    public List<Comment> list(Map<String, Object> map) {
        return commentMapper.list(map);
    }

    public Long getTotal(Map<String, Object> map) {
        return commentMapper.getTotal(map);
    }

    public Integer delete(Integer id) {
        return commentMapper.delete(id);
    }

    public int update(Comment comment) {
        return commentMapper.update(comment);
    }

}
