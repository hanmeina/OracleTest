package com.RBR.util;

import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.resource.spi.work.Work;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import oracle.net.aso.r;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.StatefulSession;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.compiler.DroolsParserException;
import org.drools.compiler.PackageBuilder;
import org.drools.io.Resource;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;
import org.drools.runtime.rule.StatefulRuleSession;
import org.drools.runtime.rule.WorkingMemory;
import org.drools.runtime.rule.WorkingMemoryEntryPoint;
import org.mvel2.sh.command.basic.ShowVars;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.RBR.drools.Assistant;
import com.RBR.drools.Values;
import com.RBR.model.Conclusion;
import com.RBR.model.Condition;
import com.RBR.model.Message;
import com.RBR.model.Rules;
import com.RBR.model.TreeNode;
import com.RBR.service.DroolsService;
import com.RBR.service.MetaDataService;
import com.RBR.service.RulesService;

public class DroolsTest {
	Assistant assistant = new Assistant();
	StatefulKnowledgeSession ksession = null;
	MetaDataService metaDataService = new MetaDataService();
	DroolsService droolsService = new DroolsService();
	RulesService rulesService = new RulesService();
	private synchronized void ksessionReady() {
		KnowledgeBase kbase = null;
		// load up the knowledge base
		long startTime = System.currentTimeMillis();
		try {
			kbase = readKnowledgeBase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ksession = kbase.newStatefulKnowledgeSession();
		System.out.println("����" + (System.currentTimeMillis() - startTime) + "ms");
		//KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "test");
		
		
//		long startTime = System.currentTimeMillis();
//		ksession = droolsUtil.getStatefulKnowledgeSession();
//		System.out.println("���� " + (System.currentTimeMillis() - startTime) + "ms");
	}

    public synchronized Assistant startDrools(Set<Message> set) {
    	ksessionReady();
    	//StatefulKnowledgeSession ksession = DroolsUtils.getThreadLocal().get();
        for (Message message : set) {
        	ksession.insert(message);			//�������
           	assistant.getReliabilityMap().put(message.isA(), message.getR());
		}
        ksession.insert(assistant);			//�������
        ksession.fireAllRules();			//��ʼִ�й�������
        //logger.close();
        //ksession.getWorkingMemoryEntryPoints().clear();
        //ksession.dispose();
        //ksession.halt();
        //kbase.removeKnowledgePackage("com.RBR.rulesFile");
        //kbuilder = null;
    	showTree();
    	ksession.dispose();
		return assistant;
    }

	public void showTree() {
		
		java.text.DecimalFormat df = new java.text.DecimalFormat("#00.00%");
		TreeNode root = new TreeNode("0", null, "root","");
//		TreeNode node = null;
//		TreeNode rootChildrenNode = null;
//		List<Integer> subList = null;
//		List<TreeNode> list = assistant.getTreeNode().getTreeNode();
//		System.out.println(list);
//		list.add(root);
//		List<TreeNode> list = assistant.getTree().getTreeNode();
		System.out.println(assistant.getConclusionSet());
		
		for (Integer integer : assistant.getConclusionSet()) {
			if(false == assistant.getTree().hasParent(String.valueOf(integer))) {
//				list.add(new TreeNode(String.valueOf(integer), String.valueOf(0), String.valueOf(integer)));
				assistant.getTree().add(integer, 0, integer,"");
			}
		}
		for(TreeNode tn : assistant.getTree().getTreeNode()){
			System.out.println(tn);
		}
		List<TreeNode> treelist = assistant.getTree().formatTreeList(root.getId(),0);
		System.out.println(treelist);
//		TreeBuilder treeBuilder = new TreeBuilder(list);
//		System.out.println(treeBuilder.buildJSONTree());
//		for(TreeNode t : list){
//			assistant.getTreeNode().formatTreeNode(t);
//		}
//		for(TreeNode tn : assistant.getTreeNode().getTreeNode()){
//			assistant.getTreeNode().formatChildrenTreeNode(tn);
//		}
//		for(TreeNode te:assistant.getTreeNode().getTreeNode()){
//			root.add(te);
//		}
//		 JSONObject jsonArray = JSONObject.fromObject(root);
//		 System.out.println(jsonArray.toString());
//		List<Integer> list = assistant.getTreeNode().formatTreeNode(0, 0);
//		for(Integer i : list){
//			System.out.println(i);
//		}
//		for(TreeNode tn : list){
//			if(assistant.getTreeNode().hasChild(tn.getId())){
//				for(TreeNode t:assistant.getTreeNode().getTreeNodeChild(tn.getId())){
//					tn.add(t);
//				}
//				
//			}
//		}
		for(TreeNode rootTn : treelist){
			root.add(rootTn);
		}
//		List<Integer> rdwo = assistant.getTree().
//		List<Integer> list = assistant.getTreeNode().formatList(0, 0);
//		
//		for (int i = 0; i < list.size(); i+=2) {
//			for (int j = 0; j < list.get(i+1) - 1; j++) {
//				System.out.print("	");
//			}
//			System.out.print("��������");
//			System.out.print(list.get(i));
//			System.out.print("(" + df.format(assistant.getReliabilityMap().get(list.get(i))) + ")");
//			System.out.println();
//		}
		
//		StringBuilder sb = new StringBuilder();
//		for(int i=0;i<list.size();i++){
//			System.out.println(list.get(i));
//		}
//		
//		
//		List<Integer> levelList = new ArrayList<Integer>();
//		List<Integer> treeList = new ArrayList<Integer>();
//		List<Integer> indexList = new ArrayList<Integer>();
//		for(int i = 0 ; i < list.size()/2 ; i++){
//			treeList.add(list.get(2*i));
//			levelList.add(list.get(2*i+1));
//		}
//		for(int i = 0 ; i < levelList.size() ; i++){
//			if(levelList.get(i) == 1){
//				indexList.add(i);
//			}
//			
//		}
		
		
//		if(indexList.isEmpty()){
//			
//		}else if(indexList.size()==1){
//			for(int i=0 ; i<list.size()/2 ; i++){
//				node = new TreeNode(i+1, list.get(2*i+1)-1,String.valueOf(++i));
//				tnode.add(node);
//				
//			}
//			
//		}else{
//			indexList.remove(0);
//			
//			for(int p = 1 ; p <= indexList.size() ; p++){
//				rootChildrenNode = new TreeNode(id, 0, "");
//				subList = getSubList(treeList, indexList.get(p-1), indexList.get(p));
//				for(int i=0 ; i<list.size()/2 ; i++){
//					node = new TreeNode(i+1, list.get(2*i+1)-1,String.valueOf(++i));
//					rootChildrenNode.add(node);
//					
//				}
//				tnode.add(rootChildrenNode);
//			}
//		}
		
		JSONObject obj = JSONObject.fromObject(root);
		System.out.println(obj.toString());
//		if(indexList.size() == 1){
//			sb.append("{\"name\":\"root\",\"children\":[");
//			if(levelList.get(0) == 1){
//				sb.append("{\"name\":\"").append(treeList.get(0)).append("(").append(df.format(assistant.getReliabilityMap().get(treeList.get(0)))).append(")").append("\"},");
//			}
//			for(int k = 0 ; k < levelList.size()-1 ; k++){
//				if(levelList.get(k) < levelList.get(k+1)){
//					sb.delete(sb.length()-2, sb.length()-1);
//					sb.append("\"children\":[{\"name\":\"").append(treeList.get(k+1)).append("(").append(df.format(assistant.getReliabilityMap().get(treeList.get(k+1)))).append(")").append("\"},");
//				}
//				if(levelList.get(k) == levelList.get(k+1) ){
//					sb.append("{\"name\":\"").append(treeList.get(k+1)).append("(").append(df.format(assistant.getReliabilityMap().get(treeList.get(k+1)))).append(")").append("\"},");
//				}
//			}
//			
////			sb.append("{\"name\":\""+treeList.get(levelList.size() - 1)+"\"},");
//			sb.deleteCharAt(sb.length()-1);
//			
//			for(int l = 0 ; l < getMaxNumber(levelList) - 1; l++){
//					
//				sb.append("]}");
//			}
//			
//			
//		
//		
//		sb.append("]}");
//	}
//				
//			
//			
//	if(indexList.size() == 2){
//		sb.append("{\"name\":\"root\",\"children\":[");
//		for(int m = 0 ; m < indexList.get(1) ; m++){
//			if(levelList.get(m) == 1){
//				sb.append("{\"name\":\"").append(treeList.get(m)).append("(").append(df.format(assistant.getReliabilityMap().get(treeList.get(m)))).append(")").append("\"},");
//			}
//			if(m+1 < indexList.get(1)){
//				
//				if(levelList.get(m) < levelList.get(m+1)){
//					sb.delete(sb.length()-2, sb.length()-1);
//					
//					sb.append("\"children\":[{\"name\":\"").append(treeList.get(m+1)).append("(").append(df.format(assistant.getReliabilityMap().get(treeList.get(m+1)))).append(")").append("\"},");
//				}
//				if(levelList.get(m) == levelList.get(m+1) ){
//					sb.append("{\"name\":\"").append(treeList.get(m+1)).append("(").append(df.format(assistant.getReliabilityMap().get(treeList.get(m+1)))).append(")").append("\"},");
//				}
//			}
//			if(m+1 == indexList.get(1)){
////				sb.append("{\"name\":\""+treeList.get(m)+"\"},");
//				sb.deleteCharAt(sb.length()-1);
//				for(int l = 0 ; l < getMaxNumber(levelList.subList(0, indexList.get(1)-1)) - 1; l++){
//					
//					sb.append("]}");
//				}
//			}
//			
//		}
//		sb.append(",");
//		for(int m = indexList.get(1) ; m < treeList.size() ; m++){
//			if(levelList.get(m) == 1){
//				sb.append("{\"name\":\"").append(treeList.get(m)).append("(").append(df.format(assistant.getReliabilityMap().get(treeList.get(m)))).append(")").append("\"},");
//			}
//			if(m+1 < treeList.size()){
//				
//				if(levelList.get(m) < levelList.get(m+1)){
//					sb.delete(sb.length()-2, sb.length()-1);
//					sb.append("\"children\":[{\"name\":\"").append(treeList.get(m+1)).append("(").append(df.format(assistant.getReliabilityMap().get(treeList.get(m+1)))).append(")").append("\"},");
//				}
//				if(levelList.get(m) == levelList.get(m+1) ){
//					sb.append("{\"name\":\"").append(treeList.get(m+1)).append("(").append(df.format(assistant.getReliabilityMap().get(treeList.get(m+1)))).append(")").append("\"},");
//				}
//			}
//			if(m+1 == treeList.size()){
////				sb.append("{\"name\":\""+treeList.get(m)+"\"},");
//				sb.deleteCharAt(sb.length()-1);
//				for(int l = 0 ; l < getMaxNumber(levelList.subList(indexList.get(1), levelList.size()-1)) - 1 ; l++){
//					
//					sb.append("]}");
//				}
//			}
//		}
//		sb.append("]}");
//	}
//		if(indexList.size() > 2){
//			sb.append("{\"name\":\"root\",\"children\":[");
//			
//			for(int m = 0 ; m < indexList.get(1) ; m++){
//				if(levelList.get(m) == 1){
//					sb.append("{\"name\":\"").append(treeList.get(m)).append("(").append(df.format(assistant.getReliabilityMap().get(treeList.get(m)))).append(")").append("\"},");
//				}
//				if(m+1 < indexList.get(1)){
//					
//					if(levelList.get(m) < levelList.get(m+1)){
//						sb.delete(sb.length()-2, sb.length()-1);
//						sb.append("\"children\":[{\"name\":\"").append(treeList.get(m+1)).append("(").append(df.format(assistant.getReliabilityMap().get(treeList.get(m+1)))).append(")").append("\"},");
//					}
//					if(levelList.get(m) == levelList.get(m+1) ){
//						sb.append("{\"name\":\"").append(treeList.get(m+1)).append("(").append(df.format(assistant.getReliabilityMap().get(treeList.get(m+1)))).append(")").append("\"},");
//					}
//				}
//				if(m+1 == indexList.get(1)){
//	//				sb.append("{\"name\":\""+treeList.get(m)+"\"},");
//					sb.deleteCharAt(sb.length()-1);
//					for(int l = 0 ; l < getMaxNumber(getSubList(levelList, 0, indexList.get(1)-1)) - 1 ; l++){
////						for(int l = 0 ; l < getMaxNumber(levelList.get(indexList.get(1)-2), levelList.get(indexList.get(1)-1)) - 1 ; l++){
//						
//						sb.append("]}");
//					}
//				}
//				
//			}
//			sb.append(",");
//			for(int j = 1 ; j < indexList.size(); j++){
//				if(j+1 < indexList.size()){
//					for(int m = indexList.get(j) ; m < indexList.get(j+1) ; m++){
//						if(levelList.get(m) == 1){
//							sb.append("{\"name\":\"").append(treeList.get(m)).append("(").append(df.format(assistant.getReliabilityMap().get(treeList.get(m)))).append(")").append("\"},");
//						}
//						if(m+1 < indexList.get(j+1)){
//							
//							if(levelList.get(m) < levelList.get(m+1)){
//								sb.delete(sb.length()-2, sb.length()-1);
//								sb.append("\"children\":[{\"name\":\"").append(treeList.get(m+1)).append("(").append(df.format(assistant.getReliabilityMap().get(treeList.get(m+1)))).append(")").append("\"},");
//							}
//							if(levelList.get(m) == levelList.get(m+1) ){
//								sb.append("{\"name\":\"").append(treeList.get(m+1)).append("(").append(df.format(assistant.getReliabilityMap().get(treeList.get(m+1)))).append(")").append("\"},");
//							}
//						}
//						if(m+1 == indexList.get(j+1)){
//	//						sb.append("{\"name\":\""+treeList.get(m)+"\"},");
//							sb.deleteCharAt(sb.length()-1);
//							for(int l = 0 ; l < getMaxNumber(getSubList(levelList, indexList.get(j), indexList.get(j+1)-1)) - 1; l++){
////								for(int l = 0 ; l < getMaxNumber(levelList.get(indexList.get(j+1)-2),levelList.get(indexList.get(j+1)-1)) - 1; l++){
//								sb.append("]}");
//							}
//						}
//					}
//					sb.append(",");
//				}
//			}
//			for(int m = indexList.get(indexList.size()-1) ; m < treeList.size() ; m++){
//				if(levelList.get(m) == 1){
//					sb.append("{\"name\":\"").append(treeList.get(m)).append("(").append(df.format(assistant.getReliabilityMap().get(treeList.get(m)))).append(")").append("\"},");
//				}
//				if(m+1 < treeList.size()){
//					
//					if(levelList.get(m) < levelList.get(m+1)){
//						sb.delete(sb.length()-2, sb.length()-1);
//						sb.append("\"children\":[{\"name\":\"").append(treeList.get(m+1)).append("(").append(df.format(assistant.getReliabilityMap().get(treeList.get(m+1)))).append(")").append("\"},");
//					}
//					if(levelList.get(m) == levelList.get(m+1) ){
//						sb.append("{\"name\":\"").append(treeList.get(m+1)).append("(").append(df.format(assistant.getReliabilityMap().get(treeList.get(m+1)))).append(")").append("\"},");
//					}
//				}
//				if(m+1 == treeList.size()){
//	//				sb.append("{\"name\":\""+treeList.get(m)+"\"},");
//					sb.deleteCharAt(sb.length()-1);
//					for(int l = 0 ; l < getMaxNumber(getSubList(levelList, indexList.get(indexList.size()-1), levelList.size())) - 1 ; l++){
////						for(int l = 0 ; l < getMaxNumber(levelList.get(levelList.size()-2), levelList.get(levelList.size()-1)) - 1 ; l++){
//						
//						sb.append("]}");
//					}
//				}
//			}
//			sb.append("]}");
//		}	
//	
//		
//		System.out.println(sb.toString());
		
	}

    private synchronized KnowledgeBase readKnowledgeBase() throws Exception {
    	
    	KnowledgeBase kbase = null;
    	KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
//        String path = Thread.currentThread().getContextClassLoader()
//        		.getResource("").getPath();
//        File rulesFile = new File(path + "RulesFile.drl");
//        if(!rulesFile.exists()) {
//        	InferenceService service = new InferenceService();
//        	service.rewriteRulesFile();
//        }
//        kbuilder.add(ResourceFactory.newClassPathResource("com/RBR/RulesFile.drl"), ResourceType.DRL);
        String myRule = getDroolsFile();
        Resource myResource = ResourceFactory.newByteArrayResource(myRule.getBytes("UTF-8"));
        kbuilder.add(myResource, ResourceType.DRL);
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
	 * �ҳ�������������
	 * 
	 */
//	public int getMaxNumber(List<Integer> list){
//		System.out.println("getMaxNumber");
//		System.out.println(list);
//		int max = 0;
//		for(Integer integer:list){
//			if(integer > max){
//				max = integer;
//			}
//		}
//		return max;
//		
//	}
	/**
	 * �ҳ�������������
	 * 
	 */
	public int getMaxNumber(List<Integer> list){
		int max = Integer.MIN_VALUE;
		for(Integer integer:list){
			if(integer > max){
				max = integer;
			}
		}
		return max;
		
	}
	public List<Integer> getSubList(List<Integer> list,int fromIndex,int toIndex){
		return list.subList(fromIndex, toIndex);
	}
	public int getMaxNumber(int a , int b){
		return a>b?a:b;
	}
//	public List<Integer> getSubList(List<Integer> list,int start,int end){
//		List<Integer> subList = new ArrayList<Integer>();
//		subList.clear();
//		subList = list.subList(start, end);
//		return subList;
//		
//	}
    public String getDroolsFile(){
		System.out.println("������getDroolsFile()����");
//		RulesService rulesService = new RulesService();
		String s = "package com.RBR "+

"import com.RBR.service.DroolsService; "+
"import com.RBR.model.Message; "+
"import com.RBR.drools.Values; "+
"import com.RBR.drools.Assistant; "+
"import java.util.HashMap;"+
"import java.util.Map;"+


"rule \"0\" "+
    "salience 1 "+
    "no-loop false "+
    "when "+
        "$message0 : Message(a == 0) "+
        "$assistant : Assistant() "+
    "then "+
        "retract($message0); "+
        "for(Message message: $assistant.getMessageList()) { "+
        "    insert(message); "+
        "} "+
        "$assistant.getMessageList().clear(); "+
"end "+

"rule \"8\" "+
    "salience 4 "+
    "no-loop true "+
    "when "+
        "$message23 : Message(a == 23)	"+ //������
        "$message61 : Message(a == 61)	"+ //����׼ȷ����ͬһλ��
        "$message30 : Message(a == 30)	"+ //��������
        "$message25 : Message(a == 25)	"+ //����
        "$assistant : Assistant()	"+
    "then	"+
        //System.out.println(8);
        "Map<Integer, Double> factAndWeight = new HashMap<Integer, Double>();	"+
        "factAndWeight.put(23, 1.0);	"+
        "factAndWeight.put(61, 1.0);	"+
        "factAndWeight.put(30, 1.0);	"+
        "factAndWeight.put(25, 1.0);	"+
        "$assistant.inferenceOneStep(35, factAndWeight, 100, 8);	"+ //�ᾧ����Һ�洦����
        "insert(new Message(0));	"+
"end	"+
"rule \"9\"	"+
    "salience 4	"+
    "no-loop true	"+
    "when	"+
        "$message35 : Message(a == 35)	"+//�ᾧ����Һ�洦����
        "$assistant : Assistant()	"+
    "then	"+
        //System.out.println(9);
       " Map<Integer, Double> factAndWeight = new HashMap<Integer, Double>();	"+
        "factAndWeight.put(35, 1.0);	"+
       " $assistant.inferenceOneStep(36, factAndWeight, 100, 9);	"+//���ᾧ������������Ƶ�λ��
        "insert(new Message(0));	"+
"end	"+
"rule \"10\"	"+
    "salience 4	"+
    "no-loop true	"+
    "when	"+
        "$message30 : Message(a == 30)	"+
       " $message62 : Message(a == 62)	"+
       " $message25 : Message(a == 25)	"+
       " $message63 : Message(a == 63)	"+
       " $assistant : Assistant()	"+
   "then	"+
        //System.out.println(10);
       " Map<Integer, Double> factAndWeight = new HashMap<Integer, Double>();	"+
       " factAndWeight.put(30, 1.0);	"+
        "factAndWeight.put(62, 1.0);	"+
      "  factAndWeight.put(25, 1.0);	"+
       " factAndWeight.put(63, 1.0);	"+
      "  $assistant.inferenceOneStep(37, factAndWeight, 100, 10);	"+
      "  insert(new Message(0));	"+
"end	"+
"rule \"11\"	"+
    "salience 4	"+
    "no-loop true	"+
   " when	"+
     "   $message37 : Message(a == 37)	"+
    "    $assistant : Assistant()	"+
   " then	"+
        //System.out.println(11);
     "   Map<Integer, Double> factAndWeight = new HashMap<Integer, Double>();	"+
     "   factAndWeight.put(37, 1.0);	"+
    "    $assistant.inferenceOneStep(73, factAndWeight, 100, 11);	"+
     "   insert(new Message(0));	"+
"end	"+
"rule \"12\"	"+
    "salience 4	"+
   " no-loop true	"+
    "when"+
     "   $message33 : Message(a == 33)	"+
     "   $message29 : Message(a == 29)	"+
     "   $message30 : Message(a == 30)	"+
     "   $message28 : Message(a == 28)	"+
     "   $assistant : Assistant()	"+
   " then	"+
        //System.out.println(12);
      "  Map<Integer, Double> factAndWeight = new HashMap<Integer, Double>();	"+
      "  factAndWeight.put(33, 1.0);	"+
     "   factAndWeight.put(29, 1.0);	"+
     "   factAndWeight.put(30, 1.0);	"+
     "   factAndWeight.put(28, 1.0);	"+
     "   $assistant.inferenceOneStep(71, factAndWeight, 100, 12);	"+
     "   insert(new Message(0));	"+
"end	"+
"rule \"13\"	"+
    "salience 4	"+
   " no-loop true	"+
   " when	"+
   "     $message64 : Message(a == 64)	"+
   "     $assistant : Assistant()	"+
  "  then	"+
        //System.out.println(13);
    "    Map<Integer, Double> factAndWeight = new HashMap<Integer, Double>();	"+
    "    factAndWeight.put(64, 1.0);	"+
    "    $assistant.inferenceOneStep(66, factAndWeight, 100, 13);	"+
     "   insert(new Message(0));	"+
"end	"+
"rule \"14\"	"+
  "  salience 4	"+
   " no-loop true	"+
  "  when"+
    "    $message66 : Message(a == 66)	"+
   "     $assistant : Assistant()	"+
  "  then	"+
        //System.out.println(14);
   "     Map<Integer, Double> factAndWeight = new HashMap<Integer, Double>();	"+
    "    factAndWeight.put(66, 1.0);	"+
   "     $assistant.inferenceOneStep(67, factAndWeight, 100, 14);	"+
   "     insert(new Message(0));	"+
"end	"+
"rule \"15\"	"+
   " salience 4	"+
   " no-loop true	"+
   " when	"+
     "   $message70 : Message(a == 70)	"+
     "   $message69 : Message(a == 69)	"+
     "   $assistant : Assistant()	"+
   " then	"+
        //System.out.println(15);
     "   Map<Integer, Double> factAndWeight = new HashMap<Integer, Double>();	"+
     "   factAndWeight.put(70, 1.0);	"+
     "   factAndWeight.put(69, 1.0);	"+
     "   $assistant.inferenceOneStep(57, factAndWeight, 100, 15);	"+
      "  insert(new Message(0));	"+
"end	";
			
			
		
		return s;
	}
	public static void main(String[] args) {
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//		DroolsService is  = (DroolsService) ctx.getBean("DroolsService");
		Message m1= new Message(35);
		Message m2= new Message(69);
		Message m3= new Message(70);
		Set<Message> set2 = new HashSet<Message>();
//		set2.add(m1);
		set2.add(m2);
		set2.add(m3);
		set2.add(new Message(23));
		set2.add(new Message(61));
		set2.add(new Message(30));
		set2.add(new Message(25));
		set2.add(new Message(62));
		set2.add(new Message(63));
		set2.add(new Message(28));
		set2.add(new Message(29));
		set2.add(new Message(64));
		set2.add(new Message(33));
//		Set<Integer> set1 = new HashSet<Integer>();
//		set1.add(35);
//		set1.add(69);
//		set1.add(70);
//		Set<Integer> set1 = new HashSet<Integer>();
//		set1.add(43);
//		set1.add(45);
//		set1.add(44);
//		set1.add(24);
//		set1.add(57);
//		Set<Message> set2 = new HashSet<Message>();
//		set2.add(new Message(43));
//		set2.add(new Message(45));
//		set2.add(new Message(44));
//		set2.add(new Message(24));
//		set2.add(new Message(57));
		DroolsTest dt = new DroolsTest();
		dt.startDrools(set2);
		
	}
}
