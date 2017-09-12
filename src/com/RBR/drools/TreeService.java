package com.RBR.drools;

import java.util.ArrayList;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.RBR.model.TreeNode;
import com.RBR.service.MetaDataService;


public class TreeService {
	private List<TreeNode> list = new ArrayList<TreeNode>(); //整个树形关系的链表
//	private List<Integer> formatList = new ArrayList<Integer>(); //展现用格式化树的链表
	Integer level = 0; //格式化用层级
	private List<TreeNode> treeList = new ArrayList<TreeNode>();
	public List<TreeNode> getTreeList(){
		return treeList;
	}
	/**
	 * 树形关系链表节点类型
	 * @author Administrator
	 * 父节点值和当前节点值
	 */
	class Node {
		Integer data;//数据
		Integer parent;//父节点
		Set<Integer> brothers = new HashSet<Integer>();//兄弟节点
		double r;
	}
	
	
	public List<TreeNode> getTreeNode(){
		return list;
	}
//	public List<Integer> getFormatList(){
//		return formatList;
//	}
	/**
	 * 格式化树成展示列表，一个值一个级
	 * @param parent
	 * @param lel
	 * @return
	 */
//	public List<Integer> formatList(Integer parent, Integer lel) {
//		Set<Integer> set = getChild(parent);
//		if(false == set.isEmpty()) {
//			for (Integer integer : set) {
//				formatList.add(integer);
//				level = lel + 1;
//				formatList.add(level);
//				formatList(integer, level);
//			}
//		}
//		return formatList;
//	}
	public List<TreeNode> formatTreeList(String pid,Integer lel){
		
		Set<TreeNode> set = getChild(pid);
		if(false == set.isEmpty()){
			for(TreeNode tn : set){
				level = lel+1;
				tn.setColor(getTreeColor(level));
				treeList.add(tn);
				formatTreeList(tn.getId(),level);
			}
		}
		return treeList;
	}
	/*public void addBrother(Integer data, Integer brother) {
		for (int i = 0; i < list.size(); i++) {
			if (data == list.get(i).parent) {
				list.get(i).brothers.add(brother);
				System.out.println("!!");
			}
		}
	}*/
	
	/**
	 * 添加多个相同父节点的子类节点
	 * @param parent
	 * @param child
	 */
//	public void adds(Integer parent, Integer...child) {
//		for (int i = 0; i < child.length; i++) {
//			add(parent, child[i]);
//		}
//	}
//	public int adds(Integer parent, Set<Integer> child) {
//		int index = 0;
//		for (Integer integer : child) {
//			index = add(parent, integer);
//		}
//		return index;
//	}
	public void adds(Integer parent, Set<Integer> child) {
		for (Integer integer : child) {
			add(integer,parent, integer,"");
		}
	}
	/**
	 * 添加一个父子关系
	 * @param parent
	 * @param child
	 */
//	public int add(Integer parent, Integer child) {
//		Node node = new Node();
//		node.parent = parent;
//		node.data = child;
////		if(list.isEmpty()){
////			list.add(node);
////		}else{
////			for(Node n : list){
////				if((n.parent == parent) && (n.data == child)){
//					
//					list.add(node);
////				}
////			}
////		}
//		
//		return list.size();
//	}
	public void add(Integer id, Integer parent, Integer child,String symbol){
//		TreeNode tn = new TreeNode(String.valueOf(id), String.valueOf(parent), String.valueOf(child));
		list.add(new TreeNode(String.valueOf(id), String.valueOf(parent), String.valueOf(child),""));
	}
	
	/**
	 * 用子节点值获取其父节点值
	 * @param data
	 * @return 获取到返回值，没有返回null
	 */
//	public Integer getParent(Integer data) {
//		for (int i = 0; i < list.size(); i++) {
//			if (list.get(i).data == data) {
//				return list.get(i).parent;
//			}
//		}
//		return null;
//	}
	public String getParent(String data) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId().equals(data)) {
				return list.get(i).getPid();
			}
		}
		return null;
	}
	/**
	 * 判断一个子节点是否有父节点
	 * @param child
	 * @return 有返回true，没有返回false
	 */
//	public boolean hasParent(Integer child) {
//		if(null == getParent(child)) {
//			return false;
//		}
//		return true;
//	}
	public boolean hasParent(String child) {
		if(null == getParent(child)) {
			return false;
		}
		return true;
	}
	
//	public Set<Integer> getBrother(Integer data) {
//		for (int i = 0; i < list.size(); i++) {
//			if (data == list.get(i).data) {
//				return list.get(i).brothers;
//			}
//		}
//		return new HashSet<Integer>();
//	}
	
	/**
	 * 获取一个父节点的所有子节点
	 * @param parent
	 * @return 返回子节点值set集
	 */
//	public Set<Integer> getChild(Integer parent) {
//		Set<Integer> set = new HashSet<Integer>();
//		for (int i = 0; i < list.size(); i++) {
//			if (list.get(i).parent == parent) {
//				set.add(list.get(i).data);
//			}
//		}
//		return set;
//	}
	/**
	 * 通过parent找到孩子节点集合
	 * @param parent
	 * @return
	 */
	public Set<TreeNode> getChild(String parent) {
		Set<TreeNode> set = new HashSet<TreeNode>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getPid().equals(parent)) {
				set.add(list.get(i));
			}
		}
		return set;
	}
	
	/**
	 * 判断一个父节点是否有子节点
	 * @param parent
	 * @return 有返回true，没有返回false
	 */
//	public boolean hasChild(Integer parent) {
//		if (getChild(parent).isEmpty()) {
//			return true;
//		}
//		return false;
//	}
	public boolean hasChild(String parent) {
		if (getChild(parent).isEmpty()) {
			return true;
		}
		return false;
	}
	
	public String getTreeColor(int count){
		String color = "#006699";
		switch (count%2) {
		case 1:
			color = "#FF6666";
			break;
		default:
			color = "#006699";
			break;
		}
		return color;
	}
}

