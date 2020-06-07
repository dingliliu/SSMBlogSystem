package blog.mapper;

import java.util.List;
import java.util.Map;

import blog.pojo.Comment;

public interface CommentMapper {
    int add(Comment comment);
    int update(Comment comment);
    List<Comment> list(Map<String, Object> map);
    Long getTotal(Map<String, Object> map);
    Integer delete(Integer id);
}
