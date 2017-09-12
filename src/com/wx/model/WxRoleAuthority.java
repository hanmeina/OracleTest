package com.wx.model;

/**
 * WxRoleAuthority entity. @author MyEclipse Persistence Tools
 */

public class WxRoleAuthority implements java.io.Serializable {

	// Fields

	private String id;//主键
	private WxAuthority wxAuthority;
	private WxRole wxRole;

	// Constructors

	/** default constructor */
	public WxRoleAuthority() {
	}

	/** full constructor */
	public WxRoleAuthority(WxAuthority wxAuthority, WxRole wxRole) {
		this.wxAuthority = wxAuthority;
		this.wxRole = wxRole;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public WxAuthority getWxAuthority() {
		return this.wxAuthority;
	}

	public void setWxAuthority(WxAuthority wxAuthority) {
		this.wxAuthority = wxAuthority;
	}

	public WxRole getWxRole() {
		return this.wxRole;
	}

	public void setWxRole(WxRole wxRole) {
		this.wxRole = wxRole;
	}

}