package com.fxz.interceptor;

import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//登录拦截器
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // 获取HttpSession
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("username") != null) {
            return true;
        }

        // 获取请求的URI
        String uri = request.getRequestURI();

        if(uri.endsWith(".js")&&uri.endsWith(".css")) {
            return true;
        }

        // 定义需要放行的路径
        String[] allowedPaths = {"/login.html", "/register.html", "/user/login/", "/user/register/",".css",".js"};

        // 使用AntPathMatcher进行路径匹配
        AntPathMatcher pathMatcher = new AntPathMatcher();
        boolean isAllowedPath = false;

        for (String allowedPath : allowedPaths) {
            if (pathMatcher.match(allowedPath, uri)) {
                isAllowedPath = true;
                break;
            }
        }

        // 如果请求的URI不是放行的路径，则重定向到/login.html
        if (!isAllowedPath) {
            response.sendRedirect("/login.html");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // 在请求处理之后进行调用，但是在视图被渲染之前
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // 在整个请求完成之后调用，主要用于清理资源
    }
}