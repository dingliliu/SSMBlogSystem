package blog.controller;

import blog.pojo.Blogger;
import blog.service.BloggerService;
import blog.util.DateUtil;
import blog.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/blogger")
public class BloggerController {
    @Autowired
    private BloggerService bloggerService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String name, String password, Model model, HttpSession session) {
        System.out.println("login:::name:::" + name + ", password:::" + password);
        Blogger bean = bloggerService.login(name, password);
        if (bean != null && bean.getId() == 1) {
            session.setAttribute("loginBlogger", bean);
            return "forward:../admin/main.jsp";
        }
        else if (bean != null){
            session.setAttribute("loginBlogger", bean);
            return "forward:../index.html";
        }
        else {
            model.addAttribute("msg", "用户名或密码错误");
            return "forward:../login.jsp";
        }
    }

    @PostMapping("/reg")
    public String reg(@Valid Blogger bean, BindingResult br, String cfmpassword, Model model) {
        //判断两次密码是否一致
        if(!bean.getPassword().equals(cfmpassword)) {
            model.addAttribute("msg", "两次输入密码不一致");
            return "forward:../register.jsp";
        }
        //数据较验
        if(br.hasErrors()) {
            Map<String, String> errors = new HashMap<String, String>();
            System.out.println("==============================表单数据出错=================================");
            List<FieldError> fieldErrors = br.getFieldErrors();
            for (FieldError item : fieldErrors) {
                System.out.println(item.getField());
                System.out.println(item.getDefaultMessage());
                errors.put(item.getField(), item.getDefaultMessage());
            }
            model.addAttribute("errors", errors);
            return "forward:../register.jsp";
        }

        //判断用户名重复
        if (bloggerService.getByName(bean.getName()) != null) {
            model.addAttribute("msg", "该用户名已经被使用");
            return "forward:../register.jsp";
        }

        boolean result = bloggerService.insert(bean);
        if (result) {
            System.out.println("注册成功");
            return "forward:../login.jsp";
        }
        System.out.println("注册失败");
        model.addAttribute("msg", "注册失败");
        return "forward:../register.jsp";
    }

    @RequestMapping("/aboutMe")
    public ModelAndView aboutMe(int id)throws Exception{
        ModelAndView mav=new ModelAndView();
        mav.addObject("blogger",bloggerService.getById(id));
        mav.addObject("mainPage", "../foreground/blogger/info.jsp");
        mav.addObject("pageTitle","关于博主");
        mav.setViewName("mainTemp");
        return mav;
    }

    @RequestMapping("/info")
    public ModelAndView details(HttpServletRequest request)throws Exception{
        ModelAndView mav=new ModelAndView();
        mav.addObject("mainPage", "foreground/blogger/modifyInfo.jsp");
        mav.setViewName("mainTemp");
        return mav;
    }

    @RequestMapping("/save")
    public String save(@RequestParam("imageFile") MultipartFile imageFile, Blogger blogger, Model model,
                       BindingResult br, HttpServletRequest request, HttpServletResponse response)throws Exception{
        if(!imageFile.isEmpty()){
            String filePath=request.getServletContext().getRealPath("/");
            String imageName= DateUtil.getCurrentDateStr()+"."+imageFile.getOriginalFilename().split("\\.")[1];
            imageFile.transferTo(new File(filePath+"static/userImages/"+imageName));
            blogger.setImageName(imageName);
        }
        System.out.println(blogger);
        boolean resultTotal=bloggerService.update(blogger);
        System.out.println(resultTotal);
        StringBuffer result=new StringBuffer();
        if(resultTotal){
            result.append("<script language='javascript'>alert('修改成功');window.location.href='../index.html'</script>");
        }else{
            result.append("<script language='javascript'>alert('修改失败');</script>");
        }
        ResponseUtil.write(response, result);
        return null;
    }
}
