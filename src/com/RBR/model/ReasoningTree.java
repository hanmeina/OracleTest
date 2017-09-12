package com.RBR.model;

public class ReasoningTree {
	private Integer treeId;  //主键ID
	private String treeData; //推理树的数据
	private String usedRules; //推理使用到的规则
	public Integer getTreeId() {
		return treeId;
	}
	public void setTreeId(Integer treeId) {
		this.treeId = treeId;
	}
	public String getTreeData() {
		return treeData;
	}
	public void setTreeData(String treeData) {
		this.treeData = treeData;
	}
	
	public String getUsedRules() {
		return usedRules;
	}
	public void setUsedRules(String usedRules) {
		this.usedRules = usedRules;
	}
	public ReasoningTree() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReasoningTree(Integer treeId, String treeData, String usedRules) {
		super();
		this.treeId = treeId;
		this.treeData = treeData;
		this.usedRules = usedRules;
	}
	
	
}
