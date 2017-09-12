package com.wx.model;

import java.util.HashSet;
import java.util.Set;

/**
 * WxRole entity. @author MyEclipse Persistence Tools
 */

public class WxRole implements java.io.Serializable {

	// Fields

	private String roleid;//主键	
	private String rolename;//角色权限名称
	private Set wxRoleAuthorities = new HashSet(0);
	private Set wxUsers = new HashSet(0);

	// Constructors

	/** default constructor */
	public WxRole() {
	}

	/** full constructor */
	public WxRole(String rolename, Set wxRoleAuthorities, Set wxUsers) {
		this.rolename = rolename;
		this.wxRoleAuthorities = wxRoleAuthorities;
		this.wxUsers = wxUsers;
	}

	// Property accessors

	public String getRoleid() {
		return this.roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Set getWxRoleAuthorities() {
		return this.wxRoleAuthorities;
	}

	public void setWxRoleAuthorities(Set wxRoleAuthorities) {
		this.wxRoleAuthorities = wxRoleAuthorities;
	}

	public Set getWxUsers() {
		return this.wxUsers;
	}

	public void setWxUsers(Set wxUsers) {
		this.wxUsers = wxUsers;
	}

}