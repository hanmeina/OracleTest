package com.RBR.model;

import java.util.HashSet;
import java.util.Set;

public class Dimension {
	private String dimensionId;//主键
	private String name;//姓名
	private String type;//类型
//	Set<Log> logOne = new HashSet<Log>();
//	Set<Log> logTwo = new HashSet<Log>();
//	Set<Log> logThree = new HashSet<Log>();
	
	public String getName() {
		return name;
	}
	public String getDimensionId() {
		return dimensionId;
	}
	public void setDimensionId(String dimensionId) {
		this.dimensionId = dimensionId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
//	public Set<Log> getLogOne() {
//		return logOne;
//	}
//	public void setLogOne(Set<Log> logOne) {
//		this.logOne = logOne;
//	}
//	public Set<Log> getLogTwo() {
//		return logTwo;
//	}
//	public void setLogTwo(Set<Log> logTwo) {
//		this.logTwo = logTwo;
//	}
//	public Set<Log> getLogThree() {
//		return logThree;
//	}
//	public void setLogThree(Set<Log> logThree) {
//		this.logThree = logThree;
//	}
	public Dimension() {
		super();
		// TODO Auto-generated constructor stub
	}
//	public Dimension(Integer dimensionId, String name, String type, Set logOne,
//			Set logTwo, Set logThree) {
//		super();
//		this.dimensionId = dimensionId;
//		this.name = name;
//		this.type = type;
//		this.logOne = logOne;
//		this.logTwo = logTwo;
//		this.logThree = logThree;
//	}
	
	
}
