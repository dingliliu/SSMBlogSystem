package blog.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.pojo.Blog;
import blog.pojo.BlogType;
import blog.pojo.Blogger;
import blog.pojo.Link;
import blog.service.BlogService;
import blog.service.BlogTypeService;
import blog.service.BloggerService;
import blog.service.LinkService;
import blog.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin/system")
public class SystemAdminController {

	@Resource
	private BloggerService bloggerService;
	
	@Resource
	private BlogTypeService blogTypeService;
	
	@Resource
	private BlogService blogService;
	
	@Resource
	private LinkService linkService;

	@RequestMapping("/refreshSystem")
	public String refreshSystem(HttpServletResponse response,HttpServletRequest request)throws Exception{
		System.out.println("id:::");
		ServletContext application=RequestContextUtils.findWebApplicationContext(request).getServletContext();
		Blogger blogger=bloggerService.getById(1);
		blogger.setPassword(null);
		application.setAttribute("blogger", blogger);
		
		List<BlogType> blogTypeCountList=blogTypeService.countList();
		assert application != null;
		application.setAttribute("blogTypeCountList", blogTypeCountList);
		
		List<Blog> blogCountList=blogService.countList();
		application.setAttribute("blogCountList", blogCountList);

	    List<Link> linkList=linkService.list(null);
		application.setAttribute("linkList", linkList);

		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
}
