package com.RBR.model;

import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Rules implements Comparator<Rules>,java.io.Serializable{
	
	
	private Integer id;  //主键id
	private Set<Condition> conditionSet = new HashSet<Condition>();  //一对多 对应前件集合表
	private Set<Conclusion> conclusionSet = new HashSet<Conclusion>(); 	//一对多 对应后件集合表
	private Integer reliability;	//规则可信度
	private String userName;  //提交规则的用户的用户名
	private Date submitTime;  //提交规则时间
	private String managerName;  //审核规则的管理员的用户名
	private Date passTime;  //进行审核的时间
	private Integer state; //是否通过审核状态  0.待审核  1.审核通过  2.审核未通过 3.休眠规则 4.规则使用过
	private String info; //备注信息
	private Integer sort;//类别
	
	
	public String getInfo() {
		return info;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Rules() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Set<Condition> getConditionSet() {
		return conditionSet;
	}
	public void setConditionSet(Set<Condition> conditionSet) {
		this.conditionSet = conditionSet;
	}
	public Set<Conclusion> getConclusionSet() {
		return conclusionSet;
	}
	public void setConclusionSet(Set<Conclusion> conclusionSet) {
		this.conclusionSet = conclusionSet;
	}
	public Integer getReliability() {
		return reliability;
	}
	public void setReliability(Integer reliability) {
		this.reliability = reliability;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public Date getPassTime() {
		return passTime;
	}
	public void setPassTime(Date passTime) {
		this.passTime = passTime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	@Override
	public String toString() {
		
		return "rules_id:" + id + " condition:" + conditionSet + " conclusion:" + conclusionSet + " username:" + userName + " submittime:" + submitTime;
	}
	public int compare(Rules o1, Rules o2) {
		return  o1.getId().compareTo(o2.getId());
		
	}
	
	

}
