package com.wx.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wx.service.AuthorityService;

@Controller
public class AuthorityController {
	@Resource
	private AuthorityService authorityService;
	
	//获取权限树
	@RequestMapping("Authority/getAuthorityTree.action")
	public String getAuthorityTree(HttpServletRequest request,String roleid){
		String js = authorityService.createAuthorityTree(roleid);
		request.setAttribute("js", js);
		request.setAttribute("roleid", roleid);
		return "wx/authority/AuthorityEdit";
	} 
	
	@RequestMapping("Authority/modifyAuthority.action")
	public String modifyAuthority(String roleid, String authoritys){
		System.out.println(roleid+":"+authoritys);
		authorityService.modifyAuthority(roleid,authoritys);
		return "wx/success";
	}
}
