package com.wx.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class JspFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}
/*	HttpSession session = httpServletRequest.getSession();
    String user = (String) session.getAttribute("userId");
    Set<String> urlSet = (HashSet<String>) session.getAttribute("urlSet");*/
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;  
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;  
        String url = httpServletRequest.getRequestURI();
        System.out.println(url);
        HttpSession session = httpServletRequest.getSession();
        //登录的jsp和action不过滤
        if(url.endsWith(".jsp")||url.endsWith(".action")){
		    if(url.indexOf("wx/UserLogin.jsp")==-1 && url.indexOf("User/login.action")==-1 
		    		&& url.indexOf("wx/noAccess.jsp")==-1){
		    	//获取用户id
		        String user = (String) session.getAttribute("userId");
		        //未登录
		    	if(user == null){
		        	System.out.println("未登录！");
		        	httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/wx/UserLogin.jsp");
		        	return;
		        }
		    	//已登录
		    	else{
		    		//获取权限列表
			    	Set<String> urlSet = (HashSet<String>) session.getAttribute("urlSet");
			        //在权限集合中未找到，提示不具有访问权限
			        if(!isAccess(urlSet, url)){
			        	System.out.println("无访问权限！");
			        	httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/wx/noAccess.jsp");
			        	return;
			        }
		        }
		    }
		}
        chain.doFilter(request, response);
	}

	//在urlSet中查找是否有与url对应的链接，若有则表示有权限
	public boolean isAccess(Set<String> urlSet, String url){
		boolean isFind = false;
		for(String s:urlSet){
			if(url.indexOf(s)!=-1){
				isFind = true;
				break;
			}
		}
		return isFind;
	}
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
