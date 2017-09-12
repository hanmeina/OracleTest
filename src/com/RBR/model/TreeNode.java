package com.RBR.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TreeNode {
	private String id;
	private String pid;//父id
	private String name;//元数据名称
	private String color;//颜色 
	private List<TreeNode> children = new ArrayList<TreeNode>();//子孙节点
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<TreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	public TreeNode(String id, String pid, String name,String color) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.color = color;
		
	}
	
	@Override
	public String toString() {
		return "TreeNode [id=" + id + ", pid=" + pid + ", name=" + name + "]";
	}
	public void add(TreeNode node){
		
		if("0".equals(node.pid)){
			if(children == null){
				children = new ArrayList<TreeNode>();
			}
			this.children.add(node);
		}else if(node.pid.equals(this.id)){
			if(children == null){
				children = new ArrayList<TreeNode>();
			}
			this.children.add(node);
		}
		else{
			for(TreeNode tmpNode : children){
				tmpNode.add(node);
			}
			
		}
	}
	
	
	
}
