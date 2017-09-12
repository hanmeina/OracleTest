package com.RBR.model;

import java.util.Date;

import java.util.HashSet;
import java.util.Set;

public class MetaData implements java.io.Serializable{
	private Integer id;// 主键
	private Integer pid;// 父id
	private String name;// 元数据名称
	private Integer level;// 元数据层级
	private boolean leaf;//是否为叶子节点标记
	private Integer reliability;//元数据初始可信度
	private String managerName;  //审核规则的管理员的用户名
	private Date passTime;  //进行审核的时间
	private Integer inverseid;//相反元数据
	private Set<Condition> conditionSet = new HashSet<Condition>();// 一对多 对应规则前件表
	private Set<Conclusion> conclusionSet = new HashSet<Conclusion>();// 一对多 对应规则后件表
	public MetaData(Integer id, Integer pid, String name, Integer level,
			boolean leaf, Integer reliability, String managerName,
			Date passTime, Integer inverseid, Set<Condition> conditionSet,
			Set<Conclusion> conclusionSet) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.level = level;
		this.leaf = leaf;
		this.reliability = reliability;
		this.managerName = managerName;
		this.passTime = passTime;
		this.inverseid = inverseid;
		this.conditionSet = conditionSet;
		this.conclusionSet = conclusionSet;
	}

	
	
	public MetaData() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Integer getInverseid() {
		return inverseid;
	}


	public void setInverseid(Integer inverseid) {
		this.inverseid = inverseid;
	}


	public Integer getId() {
		return id;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getPid() {
		return pid;
	}

	
	public void setPid(Integer pid) {
		this.pid = pid;
	}


	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public Integer getLevel() {
		return level;
	}

	
	public void setLevel(Integer level) {
		this.level = level;
	}


	public boolean isLeaf() {
		return leaf;
	}


	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}


	public Integer getReliability() {
		return reliability;
	}


	public void setReliability(Integer reliability) {
		this.reliability = reliability;
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


	@Override
	public String toString() {
		return "(metaData_id:"+id+", pid:"+pid+", name:"+name+", level:"+level + ")";
	}
	
	
}
