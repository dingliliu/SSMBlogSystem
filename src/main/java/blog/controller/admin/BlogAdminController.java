package blog.controller.admin;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.pojo.Blog;
import blog.pojo.PageBean;
import blog.service.BlogService;
import blog.util.BlogIndex;
import blog.util.ResponseUtil;
import blog.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController {

	@Autowired
	private BlogService blogService;

	// 博客索引
	private BlogIndex blogIndex=new BlogIndex();

	@RequestMapping("/save")
	public String save(Blog blog, HttpServletResponse response ,HttpSession session )throws Exception{
		int resultTotal=0; // 操作的记录条数
		if(blog.getId()==null){
			resultTotal=blogService.add(blog);
			blogIndex.addIndex(blog); // 添加博客索引
		}else{
			resultTotal=blogService.update(blog);
			blogIndex.updateIndex(blog); // 更新博客索引
			System.out.println(blog);
		}
		JSONObject result=new JSONObject();
		if(resultTotal>0){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}

	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows,Blog s_blog,HttpServletResponse response)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("title", StringUtil.formatLike(s_blog.getTitle()));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Blog> blogList=blogService.list(map);
		Long total=blogService.getTotal(map);
		JSONObject result=new JSONObject();
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray=JSONArray.fromObject(blogList,jsonConfig);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids")String ids,HttpServletResponse response)throws Exception{
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			blogService.delete(Integer.parseInt(idsStr[i]));
			blogIndex.deleteIndex(idsStr[i]); // 删除对应博客的索引
		}
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}

	@RequestMapping("/getById")
	public String getById(@RequestParam(value="id")String id,HttpServletResponse response)throws Exception{
		Blog blog=blogService.getById(Integer.parseInt(id));
		JSONObject jsonObject=JSONObject.fromObject(blog);
		ResponseUtil.write(response, jsonObject);
		return null;
	}
}