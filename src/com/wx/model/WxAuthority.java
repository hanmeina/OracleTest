package com.wx.model;

import java.util.HashSet;
import java.util.Set;

/**
 * WxAuthority entity. @author MyEclipse Persistence Tools
 */

public class WxAuthority implements java.io.Serializable,Comparable<Object> {

	// Fields

	private Integer authorityid;//主键
	private WxAuthority wxAuthority;
	private String name; //操作名称
	private String menuurl;//菜单地址
	private String systabs;
	private Set wxRoleAuthorities = new HashSet(0);
	private Set wxUrls = new HashSet(0);
	private Set wxAuthorities = new HashSet(0);

	// Constructors

	/** default constructor */
	public WxAuthority() {
	}

	/** full constructor */
	public WxAuthority(WxAuthority wxAuthority, String name, String menuurl,
			String systabs,Set wxRoleAuthorities, Set wxUrls, Set wxAuthorities) {
		this.wxAuthority = wxAuthority;
		this.name = name;
		this.menuurl = menuurl;
		this.systabs = systabs;
		this.wxRoleAuthorities = wxRoleAuthorities;
		this.wxUrls = wxUrls;
		this.wxAuthorities = wxAuthorities;
	}

	// Property accessors

	public Integer getAuthorityid() {
		return this.authorityid;
	}

	public void setAuthorityid(Integer authorityid) {
		this.authorityid = authorityid;
	}

	public WxAuthority getWxAuthority() {
		return this.wxAuthority;
	}

	public void setWxAuthority(WxAuthority wxAuthority) {
		this.wxAuthority = wxAuthority;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMenuurl() {
		return this.menuurl;
	}

	public void setMenuurl(String menuurl) {
		this.menuurl = menuurl;
	}

	public Set getWxRoleAuthorities() {
		return this.wxRoleAuthorities;
	}

	public void setWxRoleAuthorities(Set wxRoleAuthorities) {
		this.wxRoleAuthorities = wxRoleAuthorities;
	}

	public Set getWxUrls() {
		return this.wxUrls;
	}

	public void setWxUrls(Set wxUrls) {
		this.wxUrls = wxUrls;
	}

	public Set getWxAuthorities() {
		return this.wxAuthorities;
	}

	public void setWxAuthorities(Set wxAuthorities) {
		this.wxAuthorities = wxAuthorities;
	}

	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		WxAuthority a = (WxAuthority)o;
		return this.authorityid-a.authorityid;  //�������������
	}

	public String getSystabs() {
		return systabs;
	}

	public void setSystabs(String systabs) {
		this.systabs = systabs;
	}

	

	

}