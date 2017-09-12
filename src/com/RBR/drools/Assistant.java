package com.RBR.drools;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.RBR.drools.TreeService;
import com.RBR.model.Message;;
/**
 * xxxxxxxx
 * @author yupingLiu tangyang
 * @version 1.0
 * @time 2015/12/14
 */
public class Assistant {
	//推出的元数据集
	private Set<Integer> conclusionSet = new HashSet<Integer>();
	
	public Set<Integer> getConclusionSet() {
		return conclusionSet;
	}
	//用到的规则
	private List<Integer> usedRules = new ArrayList<Integer>();
	
	public List<Integer> getUsedRules() {
		return usedRules;
	}
	
	//推理链
	private TreeService tree = new TreeService();
	
	public TreeService getTree() {
		return tree;
	}
	//元数据对应的可信度
	private Map<Integer, Double> reliabilityMap = new HashMap<Integer, Double>();
	
	public Map<Integer, Double> getReliabilityMap() {
		return reliabilityMap;
	}
	//记录一轮推理推出的事实
	private List<Message> messageList = new ArrayList<Message>();
	
	public List<Message> getMessageList() {
		return messageList;
	}
	//进行一步推理
	public void inferenceOneStep(Integer conclusion, Map<Integer, Double> factAndWeight, Integer rulesReliability, Integer rulesId) {
		double r = 0.0;//元数据可信度
		messageList.add(new Message(conclusion));//记录当前结论为本轮推出的事实
		Set<Integer> key = factAndWeight.keySet();//取传进来的事实权重map的key的set集合
		for (Integer condition : key) {
			if (0.0 != factAndWeight.get(condition)) {
				r+=(reliabilityMap.get(condition)*factAndWeight.get(condition));//加权平均，计算条件的可信度
			}
		}
		r = r*rulesReliability/100;// 计算结论的可信度
		if (!reliabilityMap.containsKey(conclusion)) {  // 如果该元数据在本次推理中没有出现过，则将其可信度加入可信度map中
			reliabilityMap.put(conclusion, r);
		}
		else { // 不然，该元数据在本次推理中出现过，则用(f1+f2-f1f2)计算其可信度，修改可信度map
			reliabilityMap.put(conclusion, (reliabilityMap.get(conclusion) * (1 - r) + r));
		}
		tree.adds(conclusion, key);//加入推理链tree
		conclusionSet.add(conclusion);// 加入推出的结论元数据set集
//		if(!usedRules.contains(rulesId)){
			usedRules.add(rulesId);//加入本次推理所用到的规则id到用过的规则list
//		}
	}
}
