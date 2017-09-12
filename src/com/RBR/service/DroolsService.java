package com.RBR.service;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONObject;

import org.antlr.runtime.tree.Tree;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.Resource;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RBR.drools.Assistant;
import com.RBR.drools.TreeService;
import com.RBR.drools.Values;
import com.RBR.model.Conclusion;
import com.RBR.model.Condition;
import com.RBR.model.Message;
import com.RBR.model.Rules;
import com.RBR.model.TreeNode;

@Service
public class DroolsService {
	private StatefulKnowledgeSession ksession = null;
	@javax.annotation.Resource 
	private MetaDataService metaDataService;
	@javax.annotation.Resource 
	private RulesService rulesService;
	
//	private Assistant assistant;
//	public Assistant getAssistant(){
//		return assistant;
//	}
	private synchronized void ksessionReady(String flag) {
//	private synchronized void ksessionReady() {
		KnowledgeBase kbase = null;
		// load up the knowledge base
		long startTime = System.currentTimeMillis();
		try {
			kbase = readKnowledgeBase(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ksession = kbase.newStatefulKnowledgeSession();
		System.out.println("推理" + (System.currentTimeMillis() - startTime) + "ms");
	}

	public synchronized Assistant startDrools(Set<Message> set,String fastInferenceOrAllInference) {
//    public synchronized Assistant startDrools(Set<Message> set) {
    	Assistant assistant = new Assistant();
    	assistant.getConclusionSet().clear();
    	assistant.getMessageList().clear();
    	assistant.getUsedRules().clear();
    	assistant.getTree().getTreeNode().clear();
    	ksessionReady(fastInferenceOrAllInference);
        for (Message message : set) {
        	ksession.insert(message);			//插入对象
           	assistant.getReliabilityMap().put(message.isA(), message.getR());
		}
        ksession.insert(assistant);			//插入对象
        int count = ksession.fireAllRules();			//开始执行规则引擎
        //logger.close();
        //ksession.getWorkingMemoryEntryPoints().clear();
        
//        System.out.println("执行："+count+"条");
//    	showTree(assistant);
    	ksession.dispose();
//    	ksession.retract(ksession.getFactHandle(assistant));
		return assistant;
    }
    public List<TreeNode> getFormatList(Assistant assistant,String rootId){
    	List<TreeNode> list = new ArrayList<TreeNode>();
    	list.clear();
    	//得到规则后键
    	for (Integer integer : assistant.getConclusionSet()) {
			if(false == assistant.getTree().hasParent(String.valueOf(integer))) {
				assistant.getTree().add(integer, 0, integer,"circle");
			}
		}
//		for(TreeNode tn : assistant.getTree().getTreeNode()){
//			System.out.println(tn);
//		}
		list = assistant.getTree().formatTreeList(rootId,0);
		return list;
    }	
    
    
	public void showTree(Assistant assistant) {
//		for (Integer integer : assistant.getConclusionSet()) {
//			if(false == assistant.getTree().hasParent(integer)) {
//				assistant.getTree().add(0, integer);
//			}
//		}
//		List<Integer> list = assistant.getTree().formatList(0, 0);
		java.text.DecimalFormat df = new java.text.DecimalFormat("#00.00%");
		TreeNode root = new TreeNode("0", null, "root","circle");
		List<TreeNode> list = getFormatList(assistant,root.getId());
		for(TreeNode tn : list){
			tn.setName(metaDataService.getMetaDataById(Integer.valueOf(tn.getName())).getName()+"(" + df.format(assistant.getReliabilityMap().get(Integer.valueOf(tn.getName()))) + ")");
		}
		for(TreeNode rootTn : list){
			root.add(rootTn);
		}
		JSONObject obj = JSONObject.fromObject(root);
		System.out.println(obj.toString());

		
	}
   
    private synchronized KnowledgeBase readKnowledgeBase(String flag) throws Exception {
    	
    	KnowledgeBase kbase = null;
    	KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
    	String path = Thread.currentThread().getContextClassLoader()
           		.getResource("").getPath();
    	System.out.println("path:"+path);
        File rulesFile = new File(path + "com/RBR/AdvancedInferenceRulesFile.drl");
        File fastFile = new File(path+ "com/RBR/FastInferenceRulesFile.drl");
        System.out.println("rulesFile.exists():"+rulesFile.exists());
        System.out.println("fastFile.exists():"+fastFile.exists());
        if(!rulesFile.exists() ) {
           	 rewriteRulesFile("AdvancedInferenceRulesFile");
        }
        if(!fastFile.exists()){
        	rewriteRulesFile("FastInferenceRulesFile");
        }
      
    	if("1".equals(flag)){
    		//快速推理
    		System.out.println("flag "+flag);
    		kbuilder.add(ResourceFactory.newClassPathResource("com/RBR/FastInferenceRulesFile.drl"), ResourceType.DRL);
    	}else{
    		//高级推理
    		System.out.println("flag "+flag);
    		kbuilder.add(ResourceFactory.newClassPathResource("com/RBR/AdvancedInferenceRulesFile.drl"), ResourceType.DRL);
    	}
        
        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if (errors.size() > 0) {
            for (KnowledgeBuilderError error: errors) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }
        kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        return kbase;
    }
    /**
     * 快速推理
     * @param set，规则前件的id集合
     * @param fastInferenceOrAllInference 快速推理标志
     * @return 
     */
    public Assistant inference(Set<Integer> set ,String fastInferenceOrAllInference) {
//	public Assistant inference(Set<Integer> set) {
		Set<Message> messageSet = new HashSet<Message>();
		Assistant assistant = new Assistant();
		assistant.getTree().getTreeNode().clear();
		assistant.getUsedRules().clear();
		assistant.getConclusionSet().clear();
		assistant.getMessageList().clear();
		for (Integer id : set) {
			Message message = new Message(id, 
					Double.valueOf(metaDataService.getMetaDataById(id).getReliability())/100.0);
			messageSet.add(message);
		}
		long startTime = System.currentTimeMillis();
		assistant = startDrools(messageSet,fastInferenceOrAllInference);
//		System.out.println("推理 " + (System.currentTimeMillis() - startTime) + "ms");
//		showTree(assistant);
		return assistant;
	}
	
    
	public Assistant inference(Rules rules ,String fastInferenceOrAllInference){
//	public Assistant inference(Rules rules){
		Assistant assistant = new Assistant();
		assistant.getTree().getTreeNode().clear();
		assistant.getUsedRules().clear();
		assistant.getConclusionSet().clear();
		assistant.getMessageList().clear();
		Set<Integer> factSet = new HashSet<Integer>();	//新规则前件id作为推理事实集
		for(Condition condition : rules.getConditionSet()) {
			factSet.add(condition.getMetaData().getId());
		}
		assistant = inference(factSet,fastInferenceOrAllInference);
		return assistant;
	}
	//根据drools文件名称重写规则文件，可以是快速推理文件，高级推理文件
	public boolean rewriteRulesFile(String droolsFileName) throws IOException {
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("").getPath();
		File rulesFile = new File(path +"com/RBR/"+ droolsFileName+".drl");
		if (rulesFile.exists()) {
			rulesFile.delete();
		}
		rulesFile.createNewFile();
		rulesFile.setWritable(true);
		FileWriter out = new FileWriter(rulesFile);
		List<Rules> rulesList = new ArrayList<Rules>();
		if(droolsFileName.equals("AdvancedInferenceRulesFile")){
			rulesList = rulesService.getByPassedState();
		}else{
			rulesList = rulesService.getByFastInferenceSort();
		}
		out.write("package com.RBR."+droolsFileName+"\r\n\r\n" +
				"import com.RBR.service.DroolsService;\r\n" +
				"import com.RBR.model.Message;\r\n" +
				"import com.RBR.drools.Assistant;\r\n" +
				"import java.util.HashMap;\r\n" +
				"import java.util.Map;\r\n\r\n\r\n");
		out.write("rule \"0\"\r\n" +
				"    salience 1\r\n" +
				"    no-loop false\r\n" +
				"    when\r\n" +
				"        $message0 : Message(a == 0)\r\n" +
				"        $assistant : Assistant()\r\n" +
				"    then\r\n" +
				"        retract($message0);\r\n" +
				"        for(Message message: $assistant.getMessageList()) {\r\n" +
				"            insert(message);\r\n" +
				"        }\r\n" +
				"        $assistant.getMessageList().clear();\r\n" +
				"end\r\n\r\n");
		out.flush();
		int n = 0;
		for(Rules rules : rulesList) {
			out.write("rule \""  + rules.getId() + "\"\r\n" +
					"    salience 4\r\n" +
					"    no-loop true\r\n" +
					"    when\r\n");
			for(Condition condition : rules.getConditionSet()) {
				int conditionId = condition.getMetaData().getId();
				out.write("        $message" + conditionId 
						+ " : Message(a == " + conditionId + ")\r\n");
			}
			out.write("        $assistant : Assistant()\r\n");
			out.write("    then\r\n");
			out.write("        //System.out.println(" + rules.getId() + ");\r\n");
			out.write("        Map<Integer, Double> factAndWeight = new HashMap<Integer, Double>();" +
					"\r\n");
			for(Condition condition : rules.getConditionSet()) {
				out.write("        factAndWeight.put(" + condition.getMetaData().getId() 
						+ ", " + Double.valueOf(condition.getWeight())/100 + ");\r\n");
			}
			for(Conclusion conclusion : rules.getConclusionSet()) {
				out.write("        $assistant.inferenceOneStep(" + conclusion.getMetaData().getId() 
						+ ", factAndWeight, " + rules.getReliability() + ", " + rules.getId() + ");\r\n");
			}	
			out.write("        insert(new Message(0));\r\n");
			out.write("end\r\n");
			
			n+=1;
			if (n >= 10) {
				out.flush();
				n = 0;
			}
		}
			
		out.close();
		return true;
	}
	
	public boolean updateRulesFile(int id,String droolsFileName) throws IOException {
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("").getPath();
		File rulesFile = new File(path + droolsFileName);
		if (!rulesFile.exists()) {
			rewriteRulesFile(droolsFileName);
			return false;
		}
		rulesFile.setWritable(true);
		FileWriter out = new FileWriter(rulesFile, true);
		
//		RulesService rulesService = new RulesService();
		Rules rules = rulesService.getRuleById(id);
		
		out.write("rule \""  + rules.getId() + "\"\r\n" +
				"    salience 4\r\n" +
				"    no-loop true\r\n" +
				"    when\r\n");
		for(Condition condition : rules.getConditionSet()) {
			int conditionId = condition.getMetaData().getId();
			out.write("        $message" + conditionId 
					+ " : Message(a == " + conditionId + ")\r\n");
		}
		out.write("        $assistant : Assistant()\r\n");
		out.write("    then\r\n");
		out.write("        //System.out.println(" + rules.getId() + ");\r\n");
		out.write("        Map<Integer, Double> factAndWeight = new HashMap<Integer, Double>();" +
				"\r\n");
		for(Condition condition : rules.getConditionSet()) {
			out.write("        factAndWeight.put(" + condition.getMetaData().getId() 
					+ ", " + Double.valueOf(condition.getWeight())/100 + ");\r\n");
		}
		for(Conclusion conclusion : rules.getConclusionSet()) {
			out.write("        $assistant.inferenceOneStep(" + conclusion.getMetaData().getId() 
					+ ", factAndWeight, " + rules.getReliability() + ", " + rules.getId() + ");\r\n");
		}	
		out.write("        insert(new Message(0));\r\n");
		out.write("end\r\n");
		out.close();
		
		return true;
	}
	
	
	
	
	/**
	 * 规则审核第二步，用推理引擎判断是否有传递冗余
	 * @param 新加入规则id
	 * @return 有则返回以新加入规则前件为事实的推理链，没有返回null
	 */
//	public Assistant examineSecondStep(Integer id,String fastInferenceOrAllInference) {
	public Assistant examineSecondStep(Integer id,Assistant assistant) {
		
		Set<Integer> newConlusionSet = new HashSet<Integer>();	//新规则后件id集
//		RulesService rulesService = new RulesService();	
		Rules rules = rulesService.getRuleById(id);
		
//		Assistant assistant = getInferenceResult(id);
		for(Conclusion conclusion : rules.getConclusionSet()) {
			newConlusionSet.add(conclusion.getMetaData().getId());
		}
		
		if (assistant.getConclusionSet().containsAll(newConlusionSet)) {
			//showTree(assistant);
			return assistant;
		}
		
		return null;
	}
	
	/**
	 * 推理，执行drools 文件
	 * @return 返回Assistant对象
	 */
	
	public Assistant getInferenceResult(Integer id,String fastInferenceOrAllInference){
//	public Assistant getInferenceResult(Integer id){
//		assistant.getTree().getTreeNode().clear();
//		assistant.getUsedRules().clear();
//		assistant.getConclusionSet().clear();
//		assistant.getMessageList().clear();
		Assistant inferenceAssistant = new Assistant();
		inferenceAssistant.getTree().getTreeNode().clear();
		inferenceAssistant.getUsedRules().clear();
		inferenceAssistant.getConclusionSet().clear();
		inferenceAssistant.getMessageList().clear();
		Rules rules = rulesService.getRuleById(id);
		Set<Integer> factSet = new HashSet<Integer>();	//新规则前件id作为推理事实集
		for(Condition condition : rules.getConditionSet()) {
			factSet.add(condition.getMetaData().getId());
		}
		inferenceAssistant = inference(factSet,fastInferenceOrAllInference);
		return inferenceAssistant;
	}
	
	public String getTreeData(Rules rule,String fastInferenceOrAllInference){
//	public String getTreeData(Rules rule){
//		assistant.getTree().getTreeNode().clear();
//		assistant.getUsedRules().clear();
//		assistant.getConclusionSet().clear();
//		assistant.getMessageList().clear();
		Assistant treeDataAssistant = new Assistant();
		treeDataAssistant.getTree().getTreeNode().clear();
		treeDataAssistant.getUsedRules().clear();
		treeDataAssistant.getConclusionSet().clear();
		treeDataAssistant.getMessageList().clear();
		treeDataAssistant = inference(rule,fastInferenceOrAllInference);
		System.out.println("执行getTreeData(Rules rule,String myRule)："+treeDataAssistant.getConclusionSet());
		java.text.DecimalFormat df = new java.text.DecimalFormat("#00.00%");
		TreeNode root = new TreeNode("0", null, "root","circle");
		List<TreeNode> list = getFormatList(treeDataAssistant,root.getId());
		for(TreeNode tn : list){
			tn.setName(metaDataService.getMetaDataById(Integer.valueOf(tn.getName())).getName()+"(" + df.format(treeDataAssistant.getReliabilityMap().get(Integer.valueOf(tn.getName()))) + ")");
		}
		for(TreeNode rootTn : list){
			root.add(rootTn);
		}
		JSONObject obj = JSONObject.fromObject(root);
		return obj.toString();
		
	}
	public String getTreeData(Set set ,String fastInferenceOrAllInference){
//	public String getTreeData(Set set){
		java.text.DecimalFormat df = new java.text.DecimalFormat("#00.00%");
//		assistant.getTree().getTreeNode().clear();
//		assistant.getUsedRules().clear();
//		assistant.getConclusionSet().clear();
//		assistant.getMessageList().clear();
		Assistant tdAssistant = new Assistant();
		tdAssistant.getTree().getTreeNode().clear();
		tdAssistant.getUsedRules().clear();
		tdAssistant.getConclusionSet().clear();
		tdAssistant.getMessageList().clear();
		tdAssistant = inference(set ,fastInferenceOrAllInference);
//		assistant = tdAssistant;
		System.out.println("执行getTreeData(Set set ,String myRule)："+tdAssistant.getConclusionSet());
//		java.text.DecimalFormat df = new java.text.DecimalFormat("#00.00%");
		TreeNode root = new TreeNode("0", null, "root","circle");
		List<TreeNode> list = getFormatList(tdAssistant,root.getId());
		for(TreeNode tn : list){
			tn.setName(metaDataService.getMetaDataById(Integer.valueOf(tn.getName())).getName()+"(" + df.format(tdAssistant.getReliabilityMap().get(Integer.valueOf(tn.getName()))) + ")");
		}
		for(TreeNode rootTn : list){
			root.add(rootTn);
		}
		JSONObject obj = JSONObject.fromObject(root);
		return obj.toString();
	}
	//得到推理树
	public String getTreeData(Assistant tassistant){
		java.text.DecimalFormat df = new java.text.DecimalFormat("#00.00%");
		TreeNode root = new TreeNode("0", null, "root","circle");
		List<TreeNode> list = getFormatList(tassistant,root.getId());
		for(TreeNode tn : list){
			tn.setName(metaDataService.getMetaDataById(Integer.valueOf(tn.getName())).getName()+"(" + df.format(tassistant.getReliabilityMap().get(Integer.valueOf(tn.getName()))) + ")");
		}
		for(TreeNode rootTn : list){
			root.add(rootTn);
		}
		JSONObject obj = JSONObject.fromObject(root);
		return obj.toString();
	
	}
	/**
	 * 冲突检查，推理结论冲突检查
	 * @return 有则返回true 没有返回false
	 */
	public boolean conclusionConflict(Set<Integer> set,MetaDataService mds){
		boolean isConflict = false;
		for(Integer id : set){
			if(set.contains(mds.getMetaDataById(id).getInverseid())){
				isConflict = true;
			}
		}
		return isConflict;
	}
}
