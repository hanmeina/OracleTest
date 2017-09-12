package com.wx.model;

/**
 * WxUrl entity. @author MyEclipse Persistence Tools
 */

public class WxUrl implements java.io.Serializable {

	// Fields

	private Integer id;
	private WxAuthority wxAuthority;
	private String url;

	// Constructors

	/** default constructor */
	public WxUrl() {
	}

	/** full constructor */
	public WxUrl(WxAuthority wxAuthority, String url) {
		this.wxAuthority = wxAuthority;
		this.url = url;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public WxAuthority getWxAuthority() {
		return this.wxAuthority;
	}

	public void setWxAuthority(WxAuthority wxAuthority) {
		this.wxAuthority = wxAuthority;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}