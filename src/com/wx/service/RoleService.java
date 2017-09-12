package com.wx.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wx.dao.WxRoleDAO;
import com.wx.model.WxRole;

@Service
public class RoleService {
	@Resource
	private WxRoleDAO wxRoleDAO;
	
	public List getAllRole(){
		return wxRoleDAO.findAll();
	}
	
	public WxRole findById(String id){
		return wxRoleDAO.findById(id);
	}
	
	//判断用户名是否存在
	public Integer findByName(String name){
		List list = wxRoleDAO.findByRolename(name);
		return list.size();
	}
	
	public void addRole(WxRole role){
		wxRoleDAO.save(role);
	}
	
	public void delRole(WxRole role){
		wxRoleDAO.delete(role);
	}
	public void modifyRole(WxRole role){
		wxRoleDAO.modify(role);
	}
}
