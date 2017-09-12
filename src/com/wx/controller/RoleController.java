package com.wx.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wx.model.WxRole;
import com.wx.service.RoleService;

@Controller
public class RoleController {
	@Resource
	private RoleService roleService;
	
	@RequestMapping("Role/getAllRole.action")
	public String getAllUser(HttpServletRequest request){
		List roleList = roleService.getAllRole();
		request.setAttribute("roleList", roleList);
		System.out.println("getAllUser.action");
		return "wx/authority/RoleList";
	}
	
	@RequestMapping(value = "Role/checkRoleName.action", method = RequestMethod.POST)
	public @ResponseBody Integer checkRoleName(String rolename){
		//System.out.println(userService.findByName(username));
		return roleService.findByName(rolename);
	}
	
	@RequestMapping("Role/addRole.action")
	public String addRole(WxRole wxRole) throws UnsupportedEncodingException{
		//wxRole.setRolename(new String(wxRole.getRolename().getBytes("ISO-8859-1"),"utf-8"));
		roleService.addRole(wxRole);
		System.out.println("addRole.action");
		return "wx/success";
	}
	
	@RequestMapping("Role/delRole.action")
	public String delRole(String roleid) throws UnsupportedEncodingException{
		//wxRole.setRolename(new String(wxRole.getRolename().getBytes("ISO-8859-1"),"utf-8"));
		WxRole role = roleService.findById(roleid);
		roleService.delRole(role);
		System.out.println("delRole.action");
		return "wx/success";
	}
	
	@RequestMapping("Role/IntoModifyRole.action")
	public String findById(HttpServletRequest request, String roleid){
		WxRole role = roleService.findById(roleid);
		request.setAttribute("role", role);
		return "wx/authority/RoleModify";
	}
	
	@RequestMapping("Role/ModifyRole.action")
	public String modifyRole(String rolename, String roleid){
		WxRole role = roleService.findById(roleid);
		role.setRolename(rolename);
		roleService.modifyRole(role);
		//request.setAttribute("role", role);
		return "wx/authority/RoleModify";
	}
}
