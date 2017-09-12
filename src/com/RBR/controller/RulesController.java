package com.RBR.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.RBR.drools.Assistant;
import com.RBR.model.Conclusion;
import com.RBR.model.Condition;
import com.RBR.model.Log;
import com.RBR.model.Rules;
import com.RBR.modelEncapsulation.RulesContainList;
import com.RBR.service.DroolsService;
import com.RBR.service.LogService;
import com.RBR.service.MetaDataService;
import com.RBR.service.RulesService;



/**
 * 与规则操作相关的操作，规则的增删查改
 * 
 * @author Gao Haoyang
 * 
 */
@Controller
@RequestMapping("/rules")
public class RulesController {
	@Resource
	private RulesService rulesService;
	@Resource
	private MetaDataService metaDataService;
	@Resource
	private DroolsService droolsService;
	@Resource
	private LogService logService;
	private int flag;
	
	@RequestMapping(value = "/tab.action")
	public String tab() {
		return "/RBR/rules/tab";
	}

	/**
	 * 查看所有规则
	 * 
	 * @return
	 */
	@RequestMapping(value = "/query.action")
	public String query(Model model) {
		
		model.addAttribute("allRulesList", rulesService.getByPassedState());
		 List<Rules> list=rulesService.getByPassedState();
		 for (Rules s : list) {
		System.out.println(" 规则状态："+s.getState());	
			
		}
		 
		return "/RBR/rules/query";
	}
	
	/**
	 * 修改规则
	 * @param model返回审通过的规则
	 * @return
	 */
	@RequestMapping(value = "/change.action")
	public String change(Model model) {
		model.addAttribute("allRulesList", rulesService.getByPassedState());
		return "/RBR/rules/change";
	}
	
	/**
	 * 修改时的检查，查看规则是否使用过
	 * @param id，规则id
	 * @return
	 */
	@RequestMapping(value = "/changeCheck.action")
	public String rulesChangeCheck(String id,Model model){
		System.out.println("修改检查传入的id："+id);
		Rules rule = rulesService.getRuleById(Integer.valueOf(id));
		List<Log> logList = logService.findByUsedRules(id);
		if(rule.getState() == RulesService.USEDRULE ){
			model.addAttribute("used", 1);//规则使用过
		}else{
			model.addAttribute("used", 0);//规则未被使用
		}
		model.addAttribute("rule", rule);
		model.addAttribute("logList", logList);
		return "/RBR/rules/rulesChangeCheck";
	}
	
	/**
	 * 修改规则可信度或将基础规则改成高级规则、将高级规则改成基础规则
	 * @param id：规则id
	 * @param reliability:可信度
	 */
	@RequestMapping(value = "/changeCheckPOST.action", method = RequestMethod.POST )
	public String rulesChangeCheck(HttpSession session ,String id, String reliability,String sort){
		boolean flag = false;
//		String userName = (String) session.getAttribute("username");
		String userName = "ty";
		System.out.println("id:"+id);
		System.out.println("reliability:"+reliability);
		System.out.println("sort:"+sort);
		try {
			flag = rulesService.updateReliabilityOrRuleType(id,reliability, userName,sort);
		} catch (IOException e) {
			return "../RBR/error";
		}
		if(flag == true){
			return "redirect:/rules/change.action";
		}else{
			return "../RBR/error";
		}
	}
	
	/**
	 * 删除规则
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete.action")
	public String delete(Model model) {
		model.addAttribute("allRulesList", rulesService.getByPassedState());
		return "/RBR/rules/delete";
	}

	/**
	 * 删除规则确认，载入页面时加载
	 * @param id：规则id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/deleteConfirm.action")
	public String deleteConfirm(String id, Model model){
		Rules rule = rulesService.getRuleById(Integer.parseInt(id));
		model.addAttribute("rule",rule);	//将当前节点传到页面
		List<Log> logList = logService.findByUsedRules(id);
		
		for(Log log : logList) {
			System.out.println("log:"+log);
			
			
		}
		if(rule.getState() == RulesService.USEDRULE){
			model.addAttribute("used",1);
		}else{
			model.addAttribute("used", 0);
		}
		model.addAttribute("logList", logList);
		return "/RBR/rules/ruleDeleteConfirm";
	}
	
	/**
	 * 删除规则确认对话框
	 * @param id：规则id
	 * @return
	 */
	@RequestMapping(value="/deleteConfirm.action", method = RequestMethod.POST)
	public String deleteConfirm(HttpSession session,String id){
		System.out.println("删除："+id);
		String username = (String) session.getAttribute("username");
		Rules rule = rulesService.getRuleById(Integer.parseInt(id));
		if(rule.getState() != RulesService.USEDRULE){
			try {
				rulesService.deleteRule(Integer.valueOf(id),username);
			} catch (NumberFormatException e) {
				return "../RBR/error";
			} catch (IOException e) {
				return "../RBR/error";
			}
		}else{
			try {
				rulesService.rulesSleep(Integer.parseInt(id),username);
			} catch (NumberFormatException e) {
				return "../RBR/error";
			} catch (IOException e) {
				return "../RBR/error";
			}
		}
		return "redirect:/rules/delete.action";
	}
	
	/**
	 * 增加规则的GET方法
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/add.action")
	public String add(HttpSession session,Model model) {
		metaDataService.getTreeList().clear();
		model.addAttribute("allMetaDatas",
				metaDataService.getMetaDatasTreeList(0));
		model.addAttribute("username", session.getAttribute("username"));
		return "/RBR/rules/add";
	}

	/**
	 * 增加规则的POST方法
	 * 
	 * @param s
	 * @return
	 */
	// ��������JSONObject������json���󣬵���һֱ����
	// @RequestMapping(value = "/add", method = RequestMethod.POST)
	// public String add(@RequestBody JSONObject jsonObj){
	// jsonObj.getString("json");
	// System.out.println("POST Fun");
	// return "/rules/add";
	// }
	@RequestMapping(value = "/addPOST.action", method = RequestMethod.POST)
	@ResponseBody
	public String add(String leftMap, String right, int reliablity,
			String username,String comment,String sort) {
		// leftMap
		Map<Integer, Integer> leftHashMap = new HashMap<Integer, Integer>();
		String[] tempStringArr = leftMap.split(",");
		for (int i = 0; i < tempStringArr.length; i++) {
			String[] innerStrings = tempStringArr[i].split(":");
			leftHashMap.put(Integer.valueOf(innerStrings[0]),
					Integer.valueOf(innerStrings[1]));
		}

		// rightSet
		Set<Integer> rightSet = new HashSet<Integer>();
		rightSet.add(Integer.valueOf(right));
		rulesService.addRulesToExam(leftHashMap, rightSet, reliablity, username,comment,Integer.valueOf(sort));
		return "OK";
	}
	
	
	
	
	

	/**
	 * get rules exam page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/exam.action")
	public String exam(Model model) {

		// get waiting exam rules
		// 0 wait
		// 1 pass
		// 2 no pass
		List<Rules> waitRulesList = rulesService.getRulesByState(0);
		int waitRulesListLength = waitRulesList.size();
		model.addAttribute("waitRulesList", waitRulesList);
		model.addAttribute("waitRulesListLength", waitRulesListLength);

		return "/RBR/rules/exam";
	}

	/**
	 * 执行审核
	 * 
	 * @param id
	 * @param model
	 * @return
	 * "hasConflict": 0是不冲突，1是结论冲突，2是条件冲突
	 */
	@RequestMapping(value = "/examing.action")
	public String examingRule(HttpSession session,String id, Model model) {
		System.out.println(id);
		int ruleId = Integer.valueOf(id);	
		Rules thisRule = rulesService.getRuleById(ruleId);
		model.addAttribute("rule", thisRule);// 当前正在审核的规则
//		String fastInferenceOrAllInference = droolsService.getAllDroolsFile();
		RulesContainList rulesContainList = rulesService.examineFirstStep(ruleId);
		Assistant inferenceassistant = new Assistant();
		Assistant examAssistant = new Assistant();
		inferenceassistant.getTree().getTreeNode().clear();
		inferenceassistant.getUsedRules().clear();
		inferenceassistant.getConclusionSet().clear();
		inferenceassistant.getMessageList().clear();
		examAssistant.getTree().getTreeNode().clear();
		examAssistant.getUsedRules().clear();
		examAssistant.getConclusionSet().clear();
		examAssistant.getMessageList().clear();
		inferenceassistant = droolsService.getInferenceResult(ruleId,"0");
		examAssistant = droolsService.examineSecondStep(ruleId,inferenceassistant);
//		Assistant conflictAssistant = droolsService.inference(thisRule);
//		System.out.println("-----------------------------------------------");
//		droolsService.showTree(inferenceassistant);
//		
//		System.out.println(droolsService.getTreeData(inferenceassistant));
		String treeData = droolsService.getTreeData(inferenceassistant);
//		System.out.println("-----------------------------------------------");
		
		if (rulesContainList != null) {

			// 条件包含规则
			List<Integer> containList = rulesContainList.getContainList();
			// 条件被包含规则
			List<Integer> containedList = rulesContainList.getContainedList();
			if (!containList.isEmpty()) {
				System.out.println("条件包含规则id--->" + containList);
				List<Rules> containListRules = new ArrayList<Rules>();
				for (int i = 0; i < containList.size(); i++) {
					containListRules.add(rulesService.getRuleById(containList
							.get(i)));
				}
				model.addAttribute("containListRules", containListRules);
			} else if (!containedList.isEmpty()) {
				System.out.println("条件被包含规则id--->" + containedList);
				List<Rules> containedListRules = new ArrayList<Rules>();
				for (int i = 0; i < containedList.size(); i++) {
					containedListRules.add(rulesService
							.getRuleById(containedList.get(i)));
				}
				model.addAttribute("containedListRules", containedListRules);
			}
		} else if (examAssistant  != null) {
			System.out.println("传递性冗余检查");
			// 如果通过第一步审核，进入第二步，传递性冗余
			List<Integer> usedRulesId = examAssistant.getUsedRules();
			if (examAssistant != null && !usedRulesId.isEmpty()) {
				List<Rules> deliverDuplicateRulesList = new ArrayList<Rules>();
				for(Integer ur : usedRulesId){
					deliverDuplicateRulesList.add(rulesService
							.getRuleById(ur));
				}
				model.addAttribute("deliverDuplicateRulesList",
						deliverDuplicateRulesList);
			}
			
		}else if(inferenceassistant != null){
			Set<Conclusion> conclusion = thisRule.getConclusionSet();
			 for(Conclusion cc : conclusion){
				 if(inferenceassistant.getConclusionSet().contains(cc.getMetaData().getInverseid())){
					 System.out.println();
					 System.out.println("结论冲突！");
					 List<Integer> usedRuleId = inferenceassistant.getUsedRules();
					 List<Rules> usedRulesList = new ArrayList<Rules>();
					 for(Integer ur:usedRuleId){
						 usedRulesList.add(rulesService
									.getRuleById(ur));
					 }
					 model.addAttribute("hasConflict", 1);
					 model.addAttribute("usedRulesList",
								usedRulesList);

				 }else if(droolsService.conclusionConflict(inferenceassistant.getConclusionSet(), metaDataService)){
					 System.out.println();
					 System.out.println("推理结论冲突！");
					 
					 List<Integer> usedRuleId = inferenceassistant.getUsedRules();
					 List<Rules> usedRulesList = new ArrayList<Rules>();
					 for(Integer ur : usedRuleId){
						 usedRulesList.add(rulesService
									.getRuleById(ur));
						
					 }
					 model.addAttribute("hasConflict", 1);
					 model.addAttribute("usedRulesList",usedRulesList);
				 }else{
					 System.out.println();
					 System.out.println("结论不冲突！");
					 List<Integer> usedRuleId = inferenceassistant.getUsedRules();
					 List<Rules> usedRulesList = new ArrayList<Rules>();
					 for(Integer ur : usedRuleId){
						 usedRulesList.add(rulesService
									.getRuleById(ur));
					 }
					 model.addAttribute("hasConflict", 0);
					 model.addAttribute("usedRulesList",
								usedRulesList);
				} 
			 }
		}
		
		Set<Integer> inverseSet = new HashSet<Integer>();
		 for(Condition c : thisRule.getConditionSet()){
			 inverseSet.add(c.getMetaData().getInverseid());
		 }
		 System.out.println("冲突检查！");
		 List<Rules> conditionlist = rulesService.getRulesByRight(thisRule.getConclusionSet(), 1);
			for(Rules r : conditionlist){
				for(Condition c : r.getConditionSet()){
					if(inverseSet.contains(c.getMetaData().getId())){
						System.out.println("条件冲突!");
						flag = 2;
						model.addAttribute("hasConflict", flag);
						model.addAttribute("conditionlist", conditionlist);
					}
				}
			}
//		 String inferenceTree = droolsService.getTreeData(thisRule);
		 String managerName = "ty";
//		 String managerName = (String) session.getAttribute("username");	
//		 String logText = "规则"+ruleId;
		 rulesService.saveRuleLog(managerName, "规则"+ruleId,"3","8","20");
//		 System.out.println("inferenceTree:"+inferenceTree);
		
		model.addAttribute("treeData", treeData);
		// 前件相同规则
		model.addAttribute("sameLeftRules",
				rulesService.getRulesByLeft(thisRule.getConditionSet(), 1));

		// 后件相同规则
		model.addAttribute("sameRightRules",
				rulesService.getRulesByRight(thisRule.getConclusionSet(), 1));
		return "/RBR/rules/examingRule";
	}
	
	/**
	 * 审核不通过
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/examReject.action",method = RequestMethod.POST)
	public String examReject(HttpSession session, String id) throws IOException{
		System.out.println("审核不通过"+id);
		int ruleId = Integer.valueOf(id);
//		String managerName = "abc";//审核员姓名！！！！！！待完善
		String managerName = (String) session.getAttribute("username");
//		Assistant assistant = new Assistant();
//		String myRule = droolsService.getAllDroolsFile();
//		Assistant assistant = droolsService.getInferenceResult(ruleId);
//		System.out.println("审核通过的assistant"+assistant.getUsedRules());
		boolean flag = rulesService.updateExamRejectState(ruleId,RulesService.NOTPASS, managerName);
//		rulesService.updateState(id, 2, managerName);
		if(flag == true){
			
			return "redirect:/rules/exam.action";
		}else{
			return "../RBR/error";
		}
	}
	
	/**
	 * 审核通过
	 * @param id
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/examPass.action",method = RequestMethod.POST)
	public String examPass(HttpSession session,String id) throws IOException{
		System.out.println("审核通过:"+id);
//		String managerName = "abc";//审核员姓名！！！！！！待完善
		String managerName = (String) session.getAttribute("username");
		int ruleId = Integer.valueOf(id);
//		String myRule = droolsService.getAllDroolsFile();
//		Assistant assistant = droolsService.getInferenceResult(ruleId,"0");
//		System.out.println("审核通过的assistant"+assistant.getUsedRules());
		boolean flag = false;
		Rules rule = rulesService.getRuleById(ruleId);
		if(rule.getSort() == 0){
			
			flag = rulesService.updateExamPass(ruleId, RulesService.PASSED, managerName,"AdvancedInferenceRulesFile");
		}else{
			flag = rulesService.updateExamPass(ruleId, RulesService.PASSED, managerName,"FastInferenceRulesFile");

		}
		if(flag == true){
			
			return "redirect:/rules/exam.action";
		}else{
			return "../RBR/error";
		}
	}
	
//	/**
//	 * 更新获取单条规则的数据信息
//	 */
//	@RequestMapping(value = "/rulesIDForChange.action",method = RequestMethod.GET)
//	public String rulesIDForChange(int id, Model model) throws IOException{
//		System.out.println("审核通过:"+id);
//		model.addAttribute("rules", rulesService.getRuleById(id));
//		return "/RBR/rules/rulesChangeCheck";
//	}
}
