package com.RBR.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.RBR.drools.Assistant;
import com.RBR.model.ReasoningTree;
import com.RBR.model.Rules;
import com.RBR.service.DimensionService;
import com.RBR.service.DroolsService;
import com.RBR.service.LogService;
import com.RBR.service.MetaDataService;
import com.RBR.service.ReasoningTreeService;
import com.RBR.service.RulesService;

/**
 * 与推理相关的操作
 * @author Gao Haoyang
 *
 */
@Controller
@RequestMapping("reasoning")
public class ReasoningController {
	@Resource
	private ReasoningTreeService reasoningTreeService;
	@Resource
	private DroolsService droolsService;
	@Resource
	private MetaDataService metaDataService;
	@Resource
	private RulesService rulesService;
	@Resource
	private DimensionService dimensionService;
	@Resource
	private LogService logService;
	
	@RequestMapping(value = "/tab.action")
	public String tab(){
		return "/RBR/reasoning/tab";
	}
	
	@RequestMapping(value = "/selectQualityCondition.action")
	public String selectQualityCondition(Model model){
		metaDataService.getTreeList().clear();
		model.addAttribute("metaDataList",
				metaDataService.getMetaDatasTreeList(0));
//				metaDataService.generateTreeArrayListofMetaDatas(0));
		return "/RBR/reasoning/check";
	}
	//快速推理
	@RequestMapping(value = "/selectFastQualityCondition.action")
	public String selectQCondition(Model model){
		metaDataService.getTreeList().clear();
		model.addAttribute("metaDataList",
				metaDataService.getMetaDatasTreeList(0));
//				metaDataService.generateTreeArrayListofMetaDatas(0));
		return "/RBR/reasoning/fastcheck";
	}
	
	//推理结果
	@RequestMapping(value = "/result.action", method = RequestMethod.POST)

	public String result(HttpSession session,String idstr,String fastOrAllRule, Model model){
//		String idstr = request.getParameter("idstr");
		System.out.println("resoningControllerhahhah："+idstr);
		System.out.println("fastOrAllRule:"+fastOrAllRule);
//		String idd = "76,78";
//		String username = (String) session.getAttribute("username");
		String username = "ty";
		Assistant assistant = new Assistant();
		assistant.getTree().getTreeNode().clear();
		assistant.getUsedRules().clear();
		assistant.getConclusionSet().clear();
		assistant.getMessageList().clear();
		assistant = reasoningTreeService.formatMetaData(idstr,fastOrAllRule);
//		String myRule = droolsService.getAllDroolsFile();
		String treeData = "";
		System.out.println("使用的规则："+assistant.getUsedRules());
		boolean flag = false;
		List<Rules> list = new ArrayList<Rules>();
		System.out.println("!assistant.getConclusionSet().isEmpty():"+!assistant.getConclusionSet().isEmpty());
		if(!assistant.getConclusionSet().isEmpty()){
//			droolsService.showTree(assistant);
			treeData = droolsService.getTreeData(assistant);
			
			flag = reasoningTreeService.save(username, idstr, treeData, assistant.getUsedRules().toString(),fastOrAllRule);
			for(Integer it : assistant.getUsedRules()){
				try {
					rulesService.updateUsedRules(it,RulesService.USEDRULE, assistant);
				} catch (IOException e) {
					return "../RBR/error";
				}
				list.add(rulesService.getRuleById(it));
			}
		}
		else{
			System.out.println("进来了");
			if("1".equals(fastOrAllRule)){
				System.out.println(new StringBuilder("快速推理条件：").append(idstr).toString());
				logService.saveLog(username, new StringBuilder("快速推理条件：").append(idstr).toString(), reasoningTreeService.getReasoningTreeById(0), dimensionService.getDimensionById("3"), dimensionService.getDimensionById("10"), dimensionService.getDimensionById("20"));
			}else{
				logService.saveLog(username, new StringBuilder("高级推理条件：").append(idstr).toString(), reasoningTreeService.getReasoningTreeById(0), dimensionService.getDimensionById("3"), dimensionService.getDimensionById("10"), dimensionService.getDimensionById("20"));
			}
			flag = true;
		}
		System.out.println("treeData:"+treeData);
		
		model.addAttribute("", fastOrAllRule);
		model.addAttribute("usedRulesList", list);//使用到的规则
		model.addAttribute("treeData", treeData);//推理链数据
		if(true == flag){
			return "/RBR/reasoning/result";
		}else{
			return "../RBR/error";
			
		}
		
		
	}
	@RequestMapping(value = "/getInferenceTree.action", method = RequestMethod.GET)
	public String result(HttpSession session,String id,Model model){
		ReasoningTree rt = reasoningTreeService.getReasoningTreeById(Integer.valueOf(id));
		List<Rules> list = rulesService.getReasoningTreeUsedRules(rt.getUsedRules());
		model.addAttribute("usedRulesList", list);//使用到的规则
		model.addAttribute("treeData", rt.getTreeData());//推理链数据
		return "/RBR/reasoning/result";
	}
	
}
