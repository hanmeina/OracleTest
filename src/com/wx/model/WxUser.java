package com.wx.model;

/**
 * WxUser entity. @author MyEclipse Persistence Tools
 */

public class WxUser implements java.io.Serializable {

	// Fields

	private String userid;//主键
	private WxRole wxRole;//角色权限
	private String username;//用户名
	private String pwd;//密码

	// Constructors

	/** default constructor */
	public WxUser() {
	}

	/** full constructor */
	public WxUser(WxRole wxRole, String username, String pwd) {
		this.wxRole = wxRole;
		this.username = username;
		this.pwd = pwd;
	}

	// Property accessors

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public WxRole getWxRole() {
		return this.wxRole;
	}

	public void setWxRole(WxRole wxRole) {
		this.wxRole = wxRole;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}