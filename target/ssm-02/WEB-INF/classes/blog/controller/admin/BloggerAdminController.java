package blog.controller.admin;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.pojo.Blogger;
import blog.pojo.PageBean;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.service.BloggerService;
import blog.util.DateUtil;
import blog.util.ResponseUtil;

import net.sf.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/blogger")
public class BloggerAdminController {

	@Resource
	private BloggerService bloggerService;


	@RequestMapping("/save")
	public String save(@RequestParam("imageFile") MultipartFile imageFile, Blogger blogger, Model model,
					   BindingResult br, HttpServletRequest request, HttpServletResponse response)throws Exception{
		if(!imageFile.isEmpty()){
			String filePath=request.getServletContext().getRealPath("/");
			String imageName=DateUtil.getCurrentDateStr()+"."+imageFile.getOriginalFilename().split("\\.")[1];
			imageFile.transferTo(new File(filePath+"static/userImages/"+imageName));
			blogger.setImageName(imageName);
		}
		System.out.println(blogger);
		boolean resultTotal=bloggerService.update(blogger);
		System.out.println(resultTotal);
		StringBuffer result=new StringBuffer();
		if(resultTotal){
			result.append("<script language='javascript'>alert('修改成功');window.location.href='../modifyInfo.jsp'</script>");
		}else{
			result.append("<script language='javascript'>alert('修改失败');</script>");
		}
		ResponseUtil.write(response, result);
		return null;
	}

	@RequestMapping("/find")
	public String find(HttpServletResponse response, int id)throws Exception{
		Blogger blogger=bloggerService.getById(id);
		JSONObject jsonObject=JSONObject.fromObject(blogger);
		ResponseUtil.write(response, jsonObject);
		return null;
	}
	

	@RequestMapping("/modifyPassword")
	public String modifyPassword(String newPassword, int id, HttpServletResponse response)throws Exception{
		Blogger blogger=new Blogger();
		blogger.setPassword(newPassword);
		blogger.setId(id);
		System.out.println(blogger.getPassword());
		System.out.println(blogger.getId());
		boolean resultTotal=bloggerService.update(blogger);
		JSONObject result=new JSONObject();
		if(resultTotal){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}

	@RequestMapping("/logout")
	public String  logout()throws Exception{
		return "redirect:../../login.jsp";
	}
	@RequestMapping("/indexout")
	public String  indexout()throws Exception{
		return "redirect:../index.html";
	}

	/**
	 * 分页查询用户信息
	 * @param page
	 * @param rows
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows,@RequestParam(value="state",required=false)String state,HttpServletResponse response)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("state", state); // 封禁状态
		List<Blogger> bloggerList=bloggerService.list(map);
		Long total=bloggerService.getTotal(map);
		JSONObject result=new JSONObject();
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray=JSONArray.fromObject(bloggerList,jsonConfig);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}

	@RequestMapping("/ban")
	public String ban(Blogger blogger,HttpServletResponse response)throws Exception{
		boolean resultTotal=true;
		blogger.setState(2);
		resultTotal=bloggerService.update(blogger);
		JSONObject result=new JSONObject();
		if(resultTotal==true){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}

	@RequestMapping("/lift")
	public String lift(Blogger blogger,HttpServletResponse response)throws Exception{
		boolean resultTotal=true;
		blogger.setState(1);
		resultTotal=bloggerService.update(blogger);
		JSONObject result=new JSONObject();
		if(resultTotal==true){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}
}
