package com.RBR.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.drools.lang.descr.RuleDescr;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.RBR.dao.LogDAO;
import com.RBR.dao.RulesDAO;
import com.RBR.drools.Assistant;
import com.RBR.model.Conclusion;
import com.RBR.model.Condition;
import com.RBR.model.Dimension;
import com.RBR.model.ReasoningTree;
import com.RBR.model.Rules;
import com.RBR.modelEncapsulation.RulesContainList;

@Service
public class RulesService {

	public static final int PENDING = 0;	//待审核状态
	public static final int PASSED = 1;	//通过审核
	public static final int NOTPASS = 2;	//未通过审核
	public static final int SLEEP = 3;	//休眠状态
	public static final int USEDRULE = 4 ; //规则使用过
	public static final int DELETE = 5 ; //删除规则
	@Resource
	private RulesDAO rulesDAO;
	@Resource
	private DimensionService dimensionService;
	@Resource
	private ReasoningTreeService reasoningTreeService;
	@Resource
	private LogService logService;
	@Resource
	private DroolsService droolsService;
	public List<Rules> getAllRules() {
		return rulesDAO.findAll();
	}
    
	
	
	/**
	 * 根据id 查一条规则
	 * @param id
	 * @return
	 */
	public Rules getRuleById(int id) {
		return rulesDAO.getById(id);
	}
	
	//生成全部规则文件所需要的规则，返回所有规则
	public List<Rules> getByPassedState(){
		Set<Integer> set = new HashSet<Integer>();
		set.add(RulesService.PASSED);
		set.add(RulesService.USEDRULE);
		return rulesDAO.getPassedRules(set);
	}
	
	//生成常用规则文件所需要的规则,缺省返回全部规则
	
	public List<Rules> getByFastInferenceSort(){
		List<Rules> list = rulesDAO.getByFastSort();
//		if(list.isEmpty()){
//			
//			return getByPassedState();//没有常用规则返回全部规则
//		}else{
		return list;//返回常用规则
//		}
		
		
//		Collections.sort(list);
////		Collections.sort(list, new Comparator<Rules>() {
////			            public int compare(Rules arg0, Rules arg1) {
////			                return arg0.getId().compareTo(arg1.getId());
////			            }
////			        });
////		Arrays.sort(list.toArray());
////		return (from c in list  order by c.id descending select c).ToList<Rules>();
//		return list;
	}
	
	public List<Rules> getRulesByState(int state) {
		return rulesDAO.getByState(state);
	}

	public List<Rules> getRulesByLeft(Set<Condition> set, Integer state) {
		List<Rules> rList = new ArrayList<Rules>();
		for (Rules r : rulesDAO.getByState(state)) {
			Set<Integer> rIdSet = new HashSet<Integer>();
			Set<Integer> sIdSet = new HashSet<Integer>();
			for(Condition condition : r.getConditionSet()) {
				rIdSet.add(condition.getMetaData().getId());
			}
			for(Condition condition : set) {
				sIdSet.add(condition.getMetaData().getId());
			}
			if (rIdSet.containsAll(sIdSet) && sIdSet.containsAll(rIdSet)) {
				rList.add(r);
			}
		}
		return rList;
	}

	public List<Rules> getRulesByRight(Set<Conclusion> set, Integer state) {
		List<Rules> rList = new ArrayList<Rules>();
		for (Rules r : rulesDAO.getByState(state)) {
			Set<Integer> rIdSet = new HashSet<Integer>();
			Set<Integer> sIdSet = new HashSet<Integer>();
			for(Conclusion conclusion : r.getConclusionSet()) {
				rIdSet.add(conclusion.getMetaData().getId());
			}
			for(Conclusion conclusion : set) {
				sIdSet.add(conclusion.getMetaData().getId());
			}
			if (rIdSet.containsAll(sIdSet) && sIdSet.containsAll(rIdSet)) {
				rList.add(r);
			}
		}
		return rList;
	}

	public void addRulesToExam(Map<Integer, Integer> leftMap,
			Set<Integer> right, int reliablity, String username,String comment,int sort) {
		
		Rules rules = new Rules();
		rules.setReliability(reliablity);
		rules.setUserName(username);
		rules.setSubmitTime(new Date());
		rules.setState(RulesService.PENDING);
		rules.setInfo(comment);
		rules.setSort(sort);
//		rules.setUseFrequency(0);
//		rules.setSort(0);
//		rules.setUsedRules(0);
//		int ruleId = rulesDAO.save(rules);
		int id = rulesDAO.save(rules, leftMap, right);
//		System.out.println("RulesService的rules："+rules);
//		String logText = "规则"+dataId;
//		String logText = getLogText(leftMap, right);
		saveRuleLog(username, "规则"+id, "3", "5", "20");
//		logService.saveLog("新增规则待审核", dataId, logText, "ty", 211);
	}
	
//	public void addRulesToExam(Map<Integer, Integer> leftMap,
//			Set<Integer> right, int reliablity, String username) {
//		Rules rules = new Rules();
//		rules.setReliability(reliablity);
//		rules.setUserName(username);
//		rules.setSubmitTime(new Date());
//		rules.setState(RulesService.PENDING);
////		rules.setUseFrequency(0);
//		rules.setSort(0);
////		rules.setInfo(comment);
////		rules.setUsedRules(0);
//		int dataId = rulesDAO.save(rules, leftMap, right);
////		System.out.println("RulesService的rules："+rules);
////		String logText = getLogText(leftMap, right);
//		String logText = "规则"+dataId;
////		logService.saveLog("新增规则待审核", dataId, logText, "ty", 211);
//	}
//	private String getLogText(Map<Integer, Integer> leftMap , Set<Integer> right){
//	StringBuilder sb = new StringBuilder("规则条件集合编号：");
//	for (Entry<Integer, Integer> map : leftMap.entrySet()) {
//		sb.append(map.getKey()+"，");
//	}
//	sb.append("规则结论集合编号：");
//	for (Integer integer : right) {
//		sb.append(integer);
//	}
//	
//	return sb.toString();
//}
	
	/**
	 * 审核第一步，数据库查询比较，审核新加入规则
	 * @param id
	 * @return 通过返回null，不通过返回与之冲突的原有规则id集合
	 * 相对于原有规则来说的，原有规则包含了新规则，原有规则被新规则包含。等价规则是当做原有规则包含了新规则处理的。
	 */
	public RulesContainList examineFirstStep(Integer id) {
		RulesContainList rulesContainList = new RulesContainList();//返回的包含和被包含的规则集合
		
		//获取新加入规则的前件id集合
		Set<Integer> conditionIdSet = new HashSet<Integer>();//新规则前件集
		for(Condition condition : getRuleById(id).getConditionSet()) {
			conditionIdSet.add(condition.getMetaData().getId());
		}
		
		//获取与新加入规则后件相同的规则的前件集合，将其分别与新加入规则前件做比较
		for(Rules rules : getRulesByRight(getRuleById(id).getConclusionSet(), RulesService.PASSED)) {
			Set<Integer> compIdSet = new HashSet<Integer>();//原有规则前件集
			for(Condition condition : rules.getConditionSet()) {
				compIdSet.add(condition.getMetaData().getId());
			}
			//如果新规则前件包含原有规则前件，则新规则被包含，或相同。将原有规则id加入包含List
			if(conditionIdSet.containsAll(compIdSet)) {
				rulesContainList.getContainList().add(rules.getId());
			}
			//排除相同情况后，如果原有规则前件包含新规则前件，则新规则将包含原有规则。将原有规则id加入被包含List
			else if(compIdSet.containsAll(conditionIdSet)) {
				rulesContainList.getContainedList().add(rules.getId());
			}
		}
		if (rulesContainList.getContainList().isEmpty() 
				&& rulesContainList.getContainedList().isEmpty()) {
			return null;
		}
		return rulesContainList;
	}
	public boolean updateExamPass(int id, int state, String managerName,String droolsFileName) throws IOException {
//		boolean flag = false;
		Rules rules = rulesDAO.getById(id);
		Integer oldState = rules.getState();
		rules.setState(state);
		rules.setManagerName(managerName);
		rules.setPassTime(new Date());
		boolean flag = rulesDAO.upDateState(rules);
		
		if (RulesService.PASSED != oldState && RulesService.PASSED == state) {
			if(droolsFileName.equals("AdvancedInferenceRulesFile")){
				droolsService.updateRulesFile(id,"AdvancedInferenceRulesFile");
			}else{
				droolsService.updateRulesFile(id,"FastInferenceRulesFile");
				droolsService.updateRulesFile(id,"AdvancedInferenceRulesFile");
				
			}
//			logService.saveLog(managerName, "规则"+id, reasoningTreeService.getReasoningTreeById(0), dimensionService.getDimensionById(3), dimensionService.getDimensionById(5), dimensionService.getDimensionById(20));
			saveRuleLog(managerName, "审核通过规则"+id, "3", "8", "20");
		}
		return flag;
	}
	public boolean updateExamRejectState(int id, int state, String managerName) throws IOException {
//		boolean flag = false;
		Rules rules = rulesDAO.getById(id);
//		Integer oldState = rules.getState();
		rules.setState(state);
		rules.setManagerName(managerName);
		rules.setPassTime(new Date());
		boolean flag = rulesDAO.upDateState(rules);
		
//		if (RulesService.PASSED != oldState && RulesService.PASSED == state) {
//			if(droolsFileName.equals("AdvancedInferenceRulesFile")){
//				droolsService.updateRulesFile(id,"AdvancedInferenceRulesFile");
//			}else{
//				droolsService.updateRulesFile(id,"FastInferenceRulesFile");
//				droolsService.updateRulesFile(id,"AdvancedInferenceRulesFile");
//				
//			}
//			logService.saveLog(managerName, "规则"+id, reasoningTreeService.getReasoningTreeById(0), dimensionService.getDimensionById(3), dimensionService.getDimensionById(5), dimensionService.getDimensionById(20));
		saveRuleLog(managerName, "审核未通过规则"+id, "3", "8", "20");
//		}
		return flag;
	}
	
	/**
	 * 修改规则的可行度和规则类型
	 * @param id
	 * @param reliability
	 * @param userName
	 * @param changeRuleType
	 * @return
	 * @throws IOException
	 */
	public boolean updateReliabilityOrRuleType(String id ,String reliability ,String userName,String changeRuleType) throws IOException{
		boolean flag = false;
		int dataId = Integer.parseInt(id);
		Rules rule = getRuleById(dataId);
		int oldReliability = rule.getReliability();
		StringBuilder sb = new StringBuilder("规则").append(id);
		if(!String.valueOf(oldReliability).equals(reliability)){
			sb.append(",可信度为").append(oldReliability).append("改成").append(reliability);
			System.out.println(sb.toString());
		}
		if(rule.getState() == RulesService.USEDRULE){
				int ruleSort = rule.getSort();
				if(1 == ruleSort){
	//				System.out.println(",基础规则改为高级规则");
					if(Integer.parseInt(changeRuleType) != ruleSort){
						sb.append(",基础规则改为高级规则");
//						droolsService.rewriteRulesFile("FastInferenceRulesFile");
	//					droolsService.updateRulesFile(dataId,"AdvancedInferenceRulesFile");
					}
				}else{
					if(Integer.parseInt(changeRuleType) != ruleSort){
	//					System.out.println(",高级规则改为基础规则");
						sb.append(",高级规则改为基础规则");
//						droolsService.updateRulesFile(dataId,"FastInferenceRulesFile");
					}
				}
				saveRuleLog(userName, sb.toString(), "3", "6", "20");
				flag = changeRuleSleep(dataId, userName, changeRuleType);
								
//				leftMapResultString += metaDataId + ":" + aInput[i].value + ",";
				StringBuilder leftMap = new StringBuilder();
				for(Condition c : rule.getConditionSet()){
					leftMap.append(c.getMetaData().getId()).append(":").append(c.getMetaData().getReliability()).append(",");
				}
				leftMap.deleteCharAt(leftMap.length()-1);
				System.out.println("leftMap:"+leftMap.toString());
				Map<Integer, Integer> leftHashMap = new HashMap<Integer, Integer>();
				String[] tempStringArr = leftMap.toString().split(",");
				for (int i = 0; i < tempStringArr.length; i++) {
					String[] innerStrings = tempStringArr[i].split(":");
					leftHashMap.put(Integer.valueOf(innerStrings[0]),
							Integer.valueOf(innerStrings[1]));
				}
				Set<Integer> right = new HashSet<Integer>();
				for(Conclusion cc:rule.getConclusionSet()){
					right.add(cc.getMetaData().getId());
				}	
				addRulesToExam(leftHashMap, right, Integer.parseInt(reliability), userName,rule.getInfo(),Integer.parseInt(changeRuleType));
		}else{
				int ruleSort = rule.getSort();
				rule.setReliability(Integer.parseInt(reliability));
				rule.setSort(Integer.parseInt(changeRuleType));
				rulesDAO.upDate(rule);
				if(1 == ruleSort){
//					System.out.println(",基础规则改为高级规则");
					if(Integer.parseInt(changeRuleType) != ruleSort){
						sb.append(",基础规则改为高级规则");
						droolsService.rewriteRulesFile("FastInferenceRulesFile");
//						droolsService.updateRulesFile(dataId,"AdvancedInferenceRulesFile");
					}
				}else{
					if(Integer.parseInt(changeRuleType) != ruleSort){
//						System.out.println(",高级规则改为基础规则");
						sb.append(",高级规则改为基础规则");
						droolsService.updateRulesFile(dataId,"FastInferenceRulesFile");
					}
				}
				
				saveRuleLog(userName, sb.toString(), "3", "6", "20");	
				
			}
		
		return flag;
	}
	
	
	public boolean updateUsedRules(int id, int state,Assistant assistant) throws IOException {
//		boolean flag = false;
//		Rules rules = rulesDAO.getById(id);
//		Integer oldState = rules.getState();
//		rules.setState(state);
//		rules.setManagerName(managerName);
//		rules.setPassTime(new Date());
		Rules rules = new Rules();
//		rulesDAO.upDateState(rules);
		for(Integer aid : assistant.getUsedRules()){
				rules = rulesDAO.getById(aid);
				rules.setState(state);
				rulesDAO.upDateState(rules);
		}
		
//		if (RulesService.PASSED != oldState && RulesService.PASSED == state) {
//			InferenceService service = new InferenceService();
//			service.updateRulesFile(id);
//		}
//		if (RulesService.PASSED != oldState && RulesService.PASSED == state) {
//			
//			droolsService.updateRulesFile(id);
//		}
		return true;
	}
	/**
	 * 规则休眠
	 * @param id
	 * @param managerName
	 * @throws IOException 
	 */
	
	public void rulesSleep(int id,String managerName) throws IOException{
		Rules rules = rulesDAO.getById(id);
		rules.setState(RulesService.SLEEP);
		rules.setManagerName(managerName);
		rulesDAO.upDate(rules);
		if(rules.getSort() == 0){
			//休眠全部规则，修改高级推理drools文件
			droolsService.rewriteRulesFile("AdvancedInferenceRulesFile");
		}else{
			//休眠常用规则，修改快速推理drools文件、高级推理drools文件
			droolsService.rewriteRulesFile("FastInferenceRulesFile");
			droolsService.rewriteRulesFile("AdvancedInferenceRulesFile");
		}
		
		
		saveRuleLog(managerName, "规则"+id, "3", "9", "20");
	}
	//修改规则的规则休眠
	
	public boolean changeRuleSleep(int id,String managerName,String changeRuleSort) throws IOException{
		Rules rules = rulesDAO.getById(id);
		rules.setState(RulesService.SLEEP);
		rules.setManagerName(managerName);
//		rules.setSort(rules.getSort());
		rulesDAO.upDate(rules);
//		if(1 == rules.getSort()){
//			if(Integer.parseInt(changeRuleSort) != rules.getSort()){
//				rules.setSort(Integer.parseInt(changeRuleSort));
//				rulesDAO.upDate(rules);
//				droolsService.rewriteRulesFile("FastInferenceRulesFile");
//				droolsService.rewriteRulesFile("AdvancedInferenceRulesFile");
//			}
//		}else{
//			if(Integer.parseInt(changeRuleSort) != rules.getSort()){
//				rules.setSort(Integer.parseInt(changeRuleSort));
//				rulesDAO.upDate(rules);
//				droolsService.rewriteRulesFile("FastInferenceRulesFile");
//				droolsService.rewriteRulesFile("AdvancedInferenceRulesFile");
//			}
//		}
	
		droolsService.rewriteRulesFile("FastInferenceRulesFile");
		droolsService.rewriteRulesFile("AdvancedInferenceRulesFile");
		
		
		
		return logService.saveLog(managerName, "休眠"+id, reasoningTreeService.getReasoningTreeById(0), dimensionService.getDimensionById("3"), dimensionService.getDimensionById("9"), dimensionService.getDimensionById("20"));
	}
	
	/**
	 * 删除规则
	 * @param id
	 * @param managerName
	 * @throws IOException 
	 */
	public void deleteRule(int id, String managerName) throws IOException {
		Rules rule = rulesDAO.getById(id);
		rule.setManagerName(managerName);
		rule.setState(RulesService.DELETE);
		rulesDAO.upDate(rule);
		if(rule.getSort() == 0){
			//删除全部规则，修改高级推理drools文件
			droolsService.rewriteRulesFile("AdvancedInferenceRulesFile");
		}else{
			//删除常用规则，修改快速推理drools文件、高级推理drools文件
			droolsService.rewriteRulesFile("FastInferenceRulesFile");
			droolsService.rewriteRulesFile("AdvancedInferenceRulesFile");
		}
		
		
		saveRuleLog(managerName, "规则"+id, "3", "7", "20");
	}
	
	public List<Rules> getReasoningTreeUsedRules(String usedRules){
		List<Rules> list = new ArrayList<Rules>();
		String[] temp = usedRules.split(",");
		for(int i = 0 ; i < temp.length ; i++){
			list.add(getRuleById(Integer.valueOf(temp[i].trim())));
		}
		return list;
	}
	
	public void saveRuleLog(String userName,String content,String subSystem,String operate,String operateSubject){
		logService.saveLog(userName, content, reasoningTreeService.getReasoningTreeById(0), dimensionService.getDimensionById(subSystem), dimensionService.getDimensionById(operate), dimensionService.getDimensionById(operateSubject));
	}
//	public List<Rules> findSomeRules(){
//		return rulesDAO.findSomeRules();
//	}
//	public void updateRules(int id, String managerName, int state) {
//		Rules rules = rulesDAO.getById(id);	
//		rules.setState(state);
//		rules.setManagerName(managerName);
//		rules.setPassTime(new Date());
//		rulesDAO.upDate(rules);
//		new InferenceService().rewriteRulesFile();
//	};
	
//	@Test
//	public void test() {
//		//System.out.println(getAllRules());
//		//System.out.println(getAllRules());
//		//System.out.println(getRuleById(1));
//		
//		/*Map<Integer, Integer> leftMap = new HashMap<Integer, Integer>();
//		leftMap.put(5, 40);
//		leftMap.put(6, 40);
//		leftMap.put(7, 20);
//		Set<Integer> right = new HashSet<Integer>();
//		right.add(10);
//		addRulesToExam(leftMap, right, 85, "liu");*/
//		
//		/*for (Rules rules : getRulesByState(0)) {
//			System.out.println(rules);
//		} */
//		
//		/*Set<Condition> set = getRuleById(2).getConditionSet();
//		for (Rules rules : getRulesByLeft(set)) {
//			System.out.println(rules);
//		} */
//		
//		/*Set<Conclusion> set = getRuleById(3).getConclusionSet();
//		for (Rules rules : getRulesByRight(set)) {
//			System.out.println(rules);
//		}*/
//		
//		/*RulesContainList rcl = examineFirstStep(3);
//		if (rcl == null) {
//			System.out.println("null");
//		} 
//		else {
//			System.out.println("���¹����ԭ�й���");
//			for(Integer id : rcl.getContainList()) {
//				System.out.println(id);
//			}
//			System.out.println("�¹�����ԭ�й���");
//			for(Integer id : rcl.getContainedList()) {
//				System.out.println(id);
//			}
//		}*/
//		
//		try {
//			updateState(12, PASSED, "gao");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	public static void main(String[] args) {
//		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
//		map.put(1, 100);
//		map.put(3, 100);
//		Set<Integer> set = new HashSet<Integer>();
//		set.add(17);
//		int reliablity = 94;
//		String userName = "����";
//		String comment = "ר�Ҿ���";
//		new RulesService().addRulesToExam(map, set, reliablity, userName, comment);
//		System.out.println("true���");
//	}
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		RulesService ms  = (RulesService) ctx.getBean("rulesService");
		System.out.println("chenggong");
		System.out.println(ms.getByPassedState());
	}
	
}
