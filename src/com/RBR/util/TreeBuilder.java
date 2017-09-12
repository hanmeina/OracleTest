package com.RBR.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.RBR.model.TreeNode;

public class TreeBuilder {
	List<TreeNode> nodes = new ArrayList<TreeNode>();
	  /**
     * 获取集合中所有的根节点
     * @param nodes
     * @return
     */
	 public TreeBuilder(List<TreeNode> nodes) {
         super();
         this.nodes= nodes;
}
    public List<TreeNode> getRootNodes() {
              List<TreeNode> rootNodes = new ArrayList<TreeNode>();
              for (TreeNode n : nodes){
                       if (rootNode(n)) {
                                rootNodes.add(n);
                       }
              }
              return rootNodes;
    }
    /**
     * 构建JSON树形结构
     * @return
     */
    public String buildJSONTree() {
              List<TreeNode> nodeTree = buildTree();
              JSONArray jsonArray = JSONArray.fromObject(nodeTree);
              return jsonArray.toString();
    }
   
    /**
     * 构建树形结构
     * @return
     */
    public List<TreeNode> buildTree() {
              List<TreeNode> treeNodes = new ArrayList<TreeNode>();
              List<TreeNode> rootNodes = getRootNodes();
              for (TreeNode rootNode : rootNodes) {
                       buildChildNodes(rootNode);
                       treeNodes.add(rootNode);
              }
              return treeNodes;
    }
   
    /**
     * 递归子节点
     * @param node
     */
    public void buildChildNodes(TreeNode node) {
	  List<TreeNode> children = getChildNodes(node); 
	  if (!children.isEmpty()) {
	       for(TreeNode child : children) {
	                buildChildNodes(child);
	       } 
	       node.setChildren(children);
	  }
    }

    /**
     * 获取父节点下所有的子节点
     * @param nodes
     * @param pnode
     * @return
     */
    public List<TreeNode> getChildNodes(TreeNode pnode) {
              List<TreeNode>childNodes = new ArrayList<TreeNode>();
              for (TreeNode n : nodes){
                       if (pnode.getId() == n.getPid()) {
                                childNodes.add(n);
                       }
              }
              return childNodes;
    }
   
    /**
     * 判断是否为根节点
     * @param nodes
     * @param inNode
     * @return
     */
    public boolean rootNode(TreeNode node) {
              boolean isRootNode = true;
              for (TreeNode n : nodes){
                       if (node.getPid() == n.getId()) {
                                isRootNode= false;
                                break;
                       }
              }
              return isRootNode;
    }
}
