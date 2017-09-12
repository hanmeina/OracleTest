package com.RBR.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.RBR.dao.ReasoningTreeDAO;
import com.RBR.drools.Assistant;
import com.RBR.model.Message;
import com.RBR.model.ReasoningTree;
import com.RBR.model.Rules;


@Service
public class ReasoningTreeService {
	@Resource
	private ReasoningTreeDAO reasoningTreeDao;
	@Resource
	private DroolsService droolsService;
	@Resource
	private LogService logService;
	@Resource
	private DimensionService dimensionService;
	
	/**
	 * ���id��ѯһ��ReasoningTree����
	 * @param id
	 * @return
	 */
	public ReasoningTree getReasoningTreeById(int id) {
		return reasoningTreeDao.getById(id);
	}
	public boolean save(ReasoningTree reasoningTree){
		return reasoningTreeDao.save(reasoningTree);
		
	}
	/**
	 * 保存推理链数据
	 * @param managerName
	 * @param idstr
	 * @param treeData
	 * @param usedRules
	 * @param fastOrAllRule
	 * @return
	 */
	public boolean save(String managerName,String idstr,String treeData,String usedRules,String fastOrAllRule){
		String u = usedRules.substring(1, usedRules.length()-1);
		int treeId = reasoningTreeDao.save(treeData, u);
		if("1".equals(fastOrAllRule)){
			logService.saveLog(managerName, new StringBuilder("快速推理条件：").append(idstr).append(";结论：").append(u).toString(), getReasoningTreeById(treeId), dimensionService.getDimensionById("3"), dimensionService.getDimensionById("10"), dimensionService.getDimensionById("20"));
		}else{
			logService.saveLog(managerName, new StringBuilder("高级推理条件：").append(idstr).append(";结论：").append(u).toString(), getReasoningTreeById(treeId), dimensionService.getDimensionById("3"), dimensionService.getDimensionById("10"), dimensionService.getDimensionById("20"));
		}
		return true;
	}
/**
 * 通过treeID找到推理链
 * @param treeId
 * @return
 */
	public String getReasoningTreeData(String treeId){
		return reasoningTreeDao.getById(Integer.valueOf(treeId)).getTreeData();
	}
	
	
  /**
   * 格式化元数据
   * @param s
   * @param flag
   * @return
   */
	public Assistant formatMetaData(String s,String flag) {
		Set set = new HashSet();
		Assistant assistant = new Assistant();
		if(!set.isEmpty()){
			set.remove(set);
		}
		String[] tempData = s.split(",");
		for(int i = 0 ; i < tempData.length ; i++){
			set.add(Integer.valueOf(tempData[i]));
		}
		assistant = droolsService.inference(set,flag);
		
		return assistant;
	}
	
}
