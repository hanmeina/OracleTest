package com.wx.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.RBR.util.SystemContext;




public class SessionFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;  
        HttpServletResponse httpServletResponse = (HttpServletResponse) response; 
        SystemContext.setOffset(getOffset(httpServletRequest));  
        SystemContext.setPageSize(getPageSize(httpServletRequest)); 
        String url = httpServletRequest.getRequestURI();
        System.out.println(url);
        try{  
            chain.doFilter(request, response);  
        }finally{  
            //清空ThreadLocal中的值  
            SystemContext.removeOffset();  
            SystemContext.removePageSize();  
        }    
	}
	 public int getOffset(HttpServletRequest request){  
	      int offset = 0;  
	      try {  
	            offset = Integer.parseInt(request.getParameter("pager.offset"));  
	        } catch (NumberFormatException ignore) {  
	        }  
	     return offset;  
	  }    
	 //设置每页显示多少条记录  
	    public int getPageSize(HttpServletRequest request){  
	        return 13;  
	    }  
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
