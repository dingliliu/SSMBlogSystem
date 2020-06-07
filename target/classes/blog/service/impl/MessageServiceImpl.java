package blog.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import blog.mapper.MessageMapper;
import blog.pojo.Message;
import blog.service.MessageService;

@Service("messageService")
public class MessageServiceImpl implements MessageService{

    @Resource
    private MessageMapper messageMapper;

    public int add(Message message) {
        return messageMapper.add(message);
    }

    public List<Message> list(Map<String, Object> map) {
        return messageMapper.list(map);
    }

    public Long getTotal(Map<String, Object> map) {
        return messageMapper.getTotal(map);
    }

    public Integer delete(Integer id) {
        return messageMapper.delete(id);
    }

    public int update(Message message) {
        return messageMapper.update(message);
    }

}
