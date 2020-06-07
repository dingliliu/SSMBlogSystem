package blog.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;

import blog.pojo.Blogger;
import blog.pojo.Message;
import blog.service.MessageService;
import blog.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.sf.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Resource
    private MessageService messageService;

    @Resource
    private blog.service.PraiseService PraiseService;

    @RequestMapping("/save")
    public String save(Message message, @RequestParam("imageCode") String imageCode, @RequestParam("rid") String rid,@RequestParam("reviewName") String reviewName,HttpServletRequest request, HttpServletResponse response, HttpSession session)throws Exception{
        String sRand=(String) session.getAttribute("sRand"); // 获取系统生成的验证码
        JSONObject result=new JSONObject();
        int resultTotal=0; // 操作的记录条数
        if(!imageCode.equals(sRand)){
            result.put("success", false);
            result.put("errorInfo", "验证码填写错误！");
        }else{
            Blogger b = (Blogger)request.getSession().getAttribute("loginBlogger");
            message.setBloggerName(b.getName());
            message.setBloggerId(b.getId());
            message.setImageName(b.getImageName());
            message.setRid(Integer.parseInt(rid));
            message.setReviewName(reviewName);
             if(message.getId()==null){
                resultTotal=messageService.add(message);
            }else{

            }
            if(resultTotal>0){
                result.put("success", true);
            }else{
                result.put("success", false);
            }
        }
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * 请求留言详细信息
     * @return
     * @throws Exception
     */
    @RequestMapping("/messages")
    public ModelAndView details(HttpServletRequest request)throws Exception{
        ModelAndView mav=new ModelAndView();
        Map<String,Object> mapTemp=new HashMap<String,Object>();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("state", 1); // 查询审核通过的留言
        List<Message> messageList=messageService.list(map);
        Message message;
        for (int i = 0; i < messageList.size(); i++) {
            message = messageList.get(i);
            mapTemp.put("objectId", message.getId()); // 查询审核通过的留言
            mapTemp.put("objectType", 3); // 查询审核通过的留言
            Integer praise=Integer.valueOf(PraiseService.getTotal(mapTemp).toString());
            message.setPraise(praise);
        }
        mav.addObject("messageList", messageList);
        mav.addObject("mainPage", "foreground/message/view.jsp");
        mav.setViewName("mainTemp");
        return mav;
    }

}
