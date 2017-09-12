package com.RBR.controller;

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

import com.RBR.model.Log;
import com.RBR.service.LogService;
import com.RBR.util.PagerModel;
import com.wx.model.WxAuthority;
import com.wx.service.UserService;



/**
 * 日志相关操作
 * @author Gao Haoyang
 *
 */
@Controller
@RequestMapping("log")
public class LogController {
	@Resource
	private LogService logService;
	@Resource(name="userService")
	private UserService userService;
	@RequestMapping(value = "/logMessage")
	public String log(){
		return "/RBR/log/logIntro";
	}
//	@RequestMapping(value = "/showTimeLine", method = RequestMethod.GET)
//	public String logTimeLine(){
//		return "log/logTime2";
//	}
	
	/**
	 * 查询日志
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/query.action")
	public String logQuery(HttpSession session ,Model model){
		Set<String> setAuthorityUrl = new HashSet<String>();
		String userid = userService.findIdByName("a");
		System.out.println("userid:"+userid);
		//不允许修改用户名，直接将用户名存在session中
		Map<String, Map<WxAuthority,Set<WxAuthority>>> mapAuthoritys
			= userService.getAuthority(userid, setAuthorityUrl);
//		Map<String, Map<WxAuthority,Set<WxAuthority>>> mapAuthoritys =  
//				(Map<String, Map<WxAuthority, Set<WxAuthority>>>) session.getAttribute("mapAuthoritys");
		
//		Set<Integer> setAuthorityId = userService.getAuthorityIdSet(userid);
		
		
		
		System.out.println("logQuery(HttpSession session ,Model model)");
		PagerModel pm = logService.getLogList(mapAuthoritys);
		HashMap<Integer, String> formTag = logService.getQueryFormTag(mapAuthoritys);
		model.addAttribute("pm", pm);
		model.addAttribute("formTag",formTag);
		return "/RBR/log/log";
	}
	
	
	
	
	@RequestMapping(value = "/querySearch.action", method = RequestMethod.POST)
	public String logQuerypage(HttpSession session ,String subSystem , String operate ,String operateSubject ,Model model){
//		Map<String, Map<WxAuthority,Set<WxAuthority>>> mapAuthoritys =  (Map<String, Map<WxAuthority, Set<WxAuthority>>>) session.getAttribute("mapAuthoritys");
		System.out.println("subSystem:"+subSystem+",operate:"+operate+",operateSubject:"+operateSubject);
		Set<String> setAuthorityUrl = new HashSet<String>();
		String userid = userService.findIdByName("a");
		//不允许修改用户名，直接将用户名存在session中
		Map<String, Map<WxAuthority,Set<WxAuthority>>> mapAuthoritys
			= userService.getAuthority(userid, setAuthorityUrl);
		System.out.println("logQuerypage(HttpSession session ,Model model)");
		PagerModel pm = logService.findLog(subSystem, operate, operateSubject,mapAuthoritys);
		HashMap<Integer, String> formTag = logService.getQueryFormTag(mapAuthoritys);
		model.addAttribute("pm", pm);
		model.addAttribute("formTag",formTag);
		return "/RBR/log/log";
	}
	@RequestMapping(value = "/logSearch.action")
	public String logQuerySearch(HttpSession session ,String subSystem , String operate ,String operateSubject,Model model){
//		Map<String, Map<WxAuthority,Set<WxAuthority>>> mapAuthoritys =  (Map<String, Map<WxAuthority, Set<WxAuthority>>>) session.getAttribute("mapAuthoritys");
		Set<String> setAuthorityUrl = new HashSet<String>();
		String userid = userService.findIdByName("a");
		//不允许修改用户名，直接将用户名存在session中
		Map<String, Map<WxAuthority,Set<WxAuthority>>> mapAuthoritys
			= userService.getAuthority(userid, setAuthorityUrl);
		System.out.println("logQuerySearch(HttpSession session ,Model model)");
		PagerModel pm = logService.findLog(subSystem, operate, operateSubject,mapAuthoritys);
		HashMap<Integer, String> formTag = logService.getQueryFormTag(mapAuthoritys);
		model.addAttribute("pm", pm);
		model.addAttribute("formTag",formTag);
		return "/RBR/log/log";
	}
	
}
