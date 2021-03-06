package blog.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import blog.pojo.Blog;
import blog.pojo.Comment;
import blog.service.BlogService;
import blog.service.CommentService;
import blog.util.BlogIndex;
import blog.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Resource
    private BlogService blogService;

    @Resource
    private CommentService commentService;

    private BlogIndex blogIndex=new BlogIndex();


    @Resource
    private blog.service.PraiseService PraiseService;

    @RequestMapping("/articles/{id}")
    public ModelAndView details(@PathVariable("id") Integer id,HttpServletRequest request)throws Exception{
        ModelAndView mav=new ModelAndView();
        Blog blog=blogService.getById(id);
        String keyWords=blog.getKeyWord();
        if(StringUtil.isNotEmpty(keyWords)){
            String arr[]=keyWords.split(" ");
            mav.addObject("keyWords",StringUtil.filterWhite(Arrays.asList(arr)));
        }else{
            mav.addObject("keyWords",null);
        }
        Map<String,Object> mapTemp=new HashMap<String,Object>();
        mapTemp.put("objectId", id);
        mapTemp.put("objectType", 1);
        Integer praise=Integer.valueOf(PraiseService.getTotal(mapTemp).toString());
        blog.setPraise(praise);
        mav.addObject("blog", blog);
        blog.setClickHit(blog.getClickHit()+1); // 博客点击次数加1
        blogService.update(blog);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("blogId", blog.getId());
        map.put("state", 1); // 查询审核通过的评论
        List<Comment> commentList=commentService.list(map);
        Comment comment;
        for (int i = 0; i < commentList.size(); i++) {
            comment = commentList.get(i);
            mapTemp.put("objectId", comment.getId());
            mapTemp.put("objectType", 2);
            praise=Integer.valueOf(PraiseService.getTotal(mapTemp).toString());
            comment.setPraise(praise);
        }

        mav.addObject("commentList", commentList);
        mav.addObject("pageCode", this.genUpAndDownPageCode(blogService.getLastBlog(id),blogService.getNextBlog(id),request.getServletContext().getContextPath()));
        mav.addObject("mainPage", "foreground/blog/view.jsp");
        mav.addObject("pageTitle",blog.getTitle()+"_Java开源博客系统");
        mav.setViewName("mainTemp");
        return mav;
    }

    @RequestMapping("/q")
    public ModelAndView search(@RequestParam(value="q",required=false)String q,@RequestParam(value="page",required=false)String page,HttpServletRequest request)throws Exception{
        if(StringUtil.isEmpty(page)){
            page="1";
        }
        ModelAndView mav=new ModelAndView();
        mav.addObject("mainPage", "foreground/blog/result.jsp");
        List<Blog> blogList=blogIndex.searchBlog(q.trim());
        Integer toIndex=blogList.size()>=Integer.parseInt(page)*10?Integer.parseInt(page)*10:blogList.size();
        mav.addObject("blogList",blogList.subList((Integer.parseInt(page)-1)*10, toIndex));
        mav.addObject("pageCode",this.genUpAndDownPageCode(Integer.parseInt(page), blogList.size(), q,10,request.getServletContext().getContextPath()));
        mav.addObject("q",q);
        mav.addObject("resultTotal",blogList.size());
        mav.addObject("pageTitle","搜索关键字'"+q+"'结果页面");
        mav.setViewName("mainTemp");
        return mav;
    }

    private String genUpAndDownPageCode(Blog lastBlog,Blog nextBlog,String projectContext){
        StringBuffer pageCode=new StringBuffer();
        if(lastBlog==null || lastBlog.getId()==null){
            pageCode.append("<p>上一篇：没有了</p>");
        }else{
            pageCode.append("<p>上一篇：<a href='"+projectContext+"/blog/articles/"+lastBlog.getId()+".html'>"+lastBlog.getTitle()+"</a></p>");
        }
        if(nextBlog==null || nextBlog.getId()==null){
            pageCode.append("<p>下一篇：没有了</p>");
        }else{
            pageCode.append("<p>下一篇：<a href='"+projectContext+"/blog/articles/"+nextBlog.getId()+".html'>"+nextBlog.getTitle()+"</a></p>");
        }
        return pageCode.toString();
    }

    private String genUpAndDownPageCode(Integer page,Integer totalNum,String q,Integer pageSize,String projectContext){
        long totalPage=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        StringBuffer pageCode=new StringBuffer();
        if(totalPage==0){
            return "";
        }else{
            pageCode.append("<nav>");
            pageCode.append("<ul class='pager' >");
            if(page>1){
                pageCode.append("<li><a href='"+projectContext+"/blog/q.html?page="+(page-1)+"&q="+q+"'>上一页</a></li>");
            }else{
                pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
            }
            if(page<totalPage){
                pageCode.append("<li><a href='"+projectContext+"/blog/q.html?page="+(page+1)+"&q="+q+"'>下一页</a></li>");
            }else{
                pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
            }
            pageCode.append("</ul>");
            pageCode.append("</nav>");
        }
        return pageCode.toString();
    }
}
