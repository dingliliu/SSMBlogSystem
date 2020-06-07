package blog.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle:::" + request.getRequestURL());
        StringBuffer url = request.getRequestURL();
        if (url.toString().endsWith("blogger/login")||url.toString().endsWith("blogger/reg")||url.toString().endsWith("index.html")) {
            return true;
        }else {
            Object o = request.getSession().getAttribute("loginBlogger");
            if (o == null) {
                request.setAttribute("msg", "请先登录");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }else {
                return true;
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}