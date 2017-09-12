package com.wx.model;

import java.math.BigDecimal;

/**
 * Stu entity. @author MyEclipse Persistence Tools
 */

public class Stu implements java.io.Serializable {

	// Fields

	private BigDecimal id;
	private String name;
	private BigDecimal age;

	// Constructors

	/** default constructor */
	public Stu() {
	}

	/** full constructor */
	public Stu(String name, BigDecimal age) {
		this.name = name;
		this.age = age;
	}

	// Property accessors

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getAge() {
		return this.age;
	}

	public void setAge(BigDecimal age) {
		this.age = age;
	}

}