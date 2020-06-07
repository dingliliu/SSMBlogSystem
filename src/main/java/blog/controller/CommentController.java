package blog.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.pojo.Blog;
import blog.pojo.Blogger;
import blog.pojo.Comment;
import blog.service.BlogService;
import blog.service.CommentService;
import blog.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Resource
	private CommentService commentService;

	@Resource
	private BlogService blogService;

	@RequestMapping("/save")
	public String save(Comment comment, @RequestParam("imageCode") String imageCode, @RequestParam("rid") String rid,@RequestParam("reviewName") String reviewName,HttpServletRequest request, HttpServletResponse response, HttpSession session)throws Exception{
		String sRand=(String) session.getAttribute("sRand"); // 获取系统生成的验证码
		System.out.println("验证码为:"+sRand);
		System.out.println("imageCode为:"+imageCode);
		System.out.println("Rid为:"+rid);
		System.out.println("ReviewName为:"+reviewName);
		JSONObject result=new JSONObject();
		int resultTotal=0; // 操作的记录条数
		if(!imageCode.equals(sRand)){
			result.put("success", false);
			result.put("errorInfo", "验证码填写错误！");
		}else{
			String userIp=request.getRemoteAddr(); // 获取用户IP
			comment.setUserIp(userIp);
			Blogger b = (Blogger)request.getSession().getAttribute("loginBlogger");
			comment.setBlogger(b.getName());
			comment.setImageName(b.getImageName());
			comment.setRid(Integer.parseInt(rid));
			comment.setReviewName(reviewName);
			System.out.println("BLoggernameis:   "+comment.getBlogger());
			if(comment.getId()==null){
				resultTotal=commentService.add(comment);
				Blog blog=blogService.getById(comment.getBlog().getId());
				blog.setReplyHit(blog.getReplyHit()+1);
				blogService.update(blog);
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

}
