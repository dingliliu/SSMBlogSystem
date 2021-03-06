package blog.service;


import java.util.List;
import java.util.Map;

import blog.pojo.Message;

public interface MessageService {
    int add(Message message);
    int update(Message message);
    List<Message> list(Map<String, Object> map);
    Long getTotal(Map<String, Object> map);
    Integer delete(Integer id);
}

