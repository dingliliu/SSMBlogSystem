package blog.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.pojo.Blogger;
import blog.pojo.Praise;
import blog.service.PraiseService;
import blog.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.sf.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/praise")
public class PraiseController {

    @Resource
    private PraiseService PraiseService;

    @RequestMapping("/save")
    public String save(Praise praise,@RequestParam("objectId") String objectId,@RequestParam("objectType") String objectType,HttpServletRequest request, HttpServletResponse response, HttpSession session)throws Exception{
        JSONObject result=new JSONObject();
        int resultTotal=0; // 操作的记录条数
        Blogger b = (Blogger)request.getSession().getAttribute("loginBlogger");
        praise.setBloggerId(b.getId());
        praise.setObjectId(Integer.valueOf(objectId));
        praise.setObjectType(Integer.valueOf(objectType));
        Praise praiseGet = PraiseService.getOne(b.getId(), Integer.valueOf(objectId),Integer.valueOf(objectType));
        if(praiseGet == null && praise.getId()==null){
            resultTotal=PraiseService.add(praise);
        }
        if(resultTotal>0){
            result.put("success", true);
        }else{
            result.put("success", false);
            PraiseService.delete(praiseGet.getId());
        }
        ResponseUtil.write(response, result);
        return null;
    }

    @RequestMapping("/Praises")
    public ModelAndView details(HttpServletRequest request)throws Exception{
        ModelAndView mav=new ModelAndView();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("state", 1);
        mav.addObject("PraiseList", PraiseService.list(map));
        mav.addObject("mainPage", "foreground/Praise/view.jsp");
        mav.setViewName("mainTemp");
        return mav;
    }

    @RequestMapping("/PraisesByObject")
    public  ModelAndView detailsById(@RequestParam("objectId") String objectId,@RequestParam("objectType") String objectType,HttpServletRequest request)throws Exception{
        ModelAndView mav=new ModelAndView();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("objectId", objectId);
        map.put("objectType", objectType);
        mav.addObject("total", PraiseService.getTotal(map));
        mav.addObject("mainPage", "foreground/Praise/view.jsp");
        mav.setViewName("mainTemp");
        return mav;
    }
}

