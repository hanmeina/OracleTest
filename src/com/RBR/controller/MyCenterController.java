package com.RBR.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.RBR.service.MyCenterService;


@Controller
@RequestMapping(value = "/myCenter")
public class MyCenterController {
	@Resource
	private MyCenterService myCenterService;
	
	@RequestMapping(value = "/tab")
	public String myCenterintroduce() {
	
		return "/RBR/myCenter/tab";
	}
	@RequestMapping(value = "/metaData.action")
	public String getMyMetaData(HttpSession session ,Model model) {
//		String username = (String) session.getAttribute("username");
		String username = "admin";
		model.addAttribute("myMetaData", myCenterService.getMyMetaData(username));
		return "/RBR/myCenter/myMetaData";
	}
	

	@RequestMapping(value = "/allMyRules.action")
	public String getAllMyPassedRules(HttpSession session ,Model model) {
//		String username = (String) session.getAttribute("username");
		String username = "userName";
		model.addAttribute("allMyRule", myCenterService.getAllMyRules(username));
		return "/RBR/myCenter/allMyRules";
	}
	
	@RequestMapping(value = "/passedRules.action")
	public String getMyPassedRules(HttpSession session ,Model model) {
//		String username = (String) session.getAttribute("username");
		String username = "userName";
		model.addAttribute("myPassedRule", myCenterService.getMyPassedRules(username));
		return "/RBR/myCenter/myPassedRules";
	}
	@RequestMapping(value = "/notpassRules.action")
	public String getMyNotPassRules(HttpSession session ,Model model) {
//		String username = (String) session.getAttribute("username");
		String username = "userName";
		model.addAttribute("myNotpassRule", myCenterService.getMyNotPassRules(username));
		return "/RBR/myCenter/myNotpassRules";
	}
	@RequestMapping(value = "/sleepRules.action")
	public String getMySleepRules(HttpSession session ,Model model) {
//		String username = (String) session.getAttribute("username");
		String username = "userName";
		model.addAttribute("mySleepRule", myCenterService.getMySleepRules(username));
		return "/RBR/myCenter/mySleepRules";
	}
	@RequestMapping(value = "/pendingRules.action")
	public String getMyPendingRules(HttpSession session ,Model model) {
//		String username = (String) session.getAttribute("username");
		String username = "userName";
		model.addAttribute("myPendingRule", myCenterService.getMyPendingRules(username));
		return "/RBR/myCenter/myPendingRules";
	}
	@RequestMapping(value = "/myInference.action")
	public String getMyInference(HttpSession session ,Model model) {
//		String username = (String) session.getAttribute("username");
		String username = "userName";
		model.addAttribute("myPendingRule", myCenterService.getMyInferenceInfo(username));
		return "/RBR/myCenter/myInference";
	}
	
	
}
