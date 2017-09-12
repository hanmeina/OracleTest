package com.RBR.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.wx.model.WxAuthority;

public class Log implements java.io.Serializable,Comparable<Object>{
	private Integer id;//日志id
	private String userName;//操作人
	private Date operateTime;//操作时间
	private String content;//内容
	private ReasoningTree reasoningTree;//推理树
	private Dimension subSystem;//子系统
	private Dimension operate;//操作
	private Dimension operateSubject;//操作对象
	private Integer authorityId;
	public Log() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Integer getAuthorityId() {
		return authorityId;
	}



	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}



	public String getUserName() {
		return userName;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public ReasoningTree getReasoningTree() {
		return reasoningTree;
	}
	public void setReasoningTree(ReasoningTree reasoningTree) {
		this.reasoningTree = reasoningTree;
	}
	public Dimension getSubSystem() {
		return subSystem;
	}
	public void setSubSystem(Dimension subSystem) {
		this.subSystem = subSystem;
	}
	public Dimension getOperate() {
		return operate;
	}
	public void setOperate(Dimension operate) {
		this.operate = operate;
	}
	public Dimension getOperateSubject() {
		return operateSubject;
	}
	public void setOperateSubject(Dimension operateSubject) {
		this.operateSubject = operateSubject;
	}
	public Log(Integer id, String userName, Date operateTime, String content,
			ReasoningTree reasoningTree, Dimension subSystem,
			Dimension operate, Dimension operateSubject,Integer authorityId) {
		super();
		this.id = id;
		this.userName = userName;
		this.operateTime = operateTime;
		this.content = content;
		this.reasoningTree = reasoningTree;
		this.subSystem = subSystem;
		this.operate = operate;
		this.operateSubject = operateSubject;
		this.authorityId = authorityId;
	}
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		Log a = (Log)o;
		return this.id-a.id;  //设计有序PLog类，以id为关键字，实现Comparable接口的抽象方法，定义排序规则
	}
	@Override
	public String toString() {
		return "Log [id=" + id + ", userName=" + userName + ", operateTime="
				+ operateTime + ", content=" + content +  "]";
	}
	
	
	
	
}
