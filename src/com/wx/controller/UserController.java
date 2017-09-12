package com.wx.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wx.model.WxAuthority;
import com.wx.model.WxRole;
import com.wx.model.WxUser;
import com.wx.service.RoleService;
import com.wx.service.UserService;

@Controller
public class UserController {
	@Resource(name="userService")
	private UserService userService;
	@Resource
	private RoleService roleService;
	
	/*
	 * �û��ĵ�½
	 */
	@RequestMapping("User/login.action")
	public String login(HttpSession session, String username, String pwd){
		Set<String> setAuthorityUrl = new HashSet<String>();
		//System.out.println("login.action");
		/*Set urlSet = userService.getUrlList();
		session.setAttribute("urlSet",urlSet);*/
		String systabs = (String)session.getAttribute("systabs");
		System.out.println("systabs:"+systabs);
		String userid = userService.findIdByName(username);
		String roleName = userService.findRoleByName(username);
		System.out.println("roleName is :"+roleName);
		//�������޸��û�����ֱ�ӽ��û�������session��
		session.setAttribute("userName", username);
		session.setAttribute("roleName", roleName);
		Map<String, Map<WxAuthority,Set<WxAuthority>>> mapAuthoritys
			= userService.getAuthority(userid, setAuthorityUrl);
		Map<WxAuthority,Set<WxAuthority>> mapAuthority = mapAuthoritys.get(systabs);
		session.setAttribute("mapAuthority", mapAuthority);//�����Զ������˵�
		session.setAttribute("setAuthorityUrl",setAuthorityUrl); //���ڹ�����У��
		
		//����
		/*System.out.println("setAuthorityUrl size:"+setAuthorityUrl.size());
		System.out.println("mapAuthority size:"+mapAuthority.size());
		for(WxAuthority a:mapAuthority.keySet()){
			System.out.println(a.getMenuurl());
			for(WxAuthority ca:mapAuthority.get(a))
				System.out.println(a.getName()+" de child:"+ca.getMenuurl());
		}*/
		return "wx/indexMain";
	}
	
	/*
	 * �û����˳���δ��ɣ�
	 */
	@RequestMapping("User/exit.action")
	public String exit(HttpSession session, String username, String pwd){
		Set<String> setAuthorityUrl = new HashSet<String>();
		//System.out.println("login.action");
		/*Set urlSet = userService.getUrlList();
		session.setAttribute("urlSet",urlSet);*/
		String systabs = (String)session.getAttribute("systabs");
		String userid = userService.findIdByName(username);
		String roleName = userService.findRoleByName(username);
		System.out.println("roleName is :"+roleName);
		//�������޸��û�����ֱ�ӽ��û�������session��
		session.setAttribute("userName", username);
		session.setAttribute("roleName", roleName);
		Map<String, Map<WxAuthority,Set<WxAuthority>>> mapAuthoritys
			= userService.getAuthority(userid, setAuthorityUrl);
		Map<WxAuthority,Set<WxAuthority>> mapAuthority = mapAuthoritys.get(systabs);
		session.setAttribute("mapAuthority", mapAuthority);//�����Զ������˵�
		session.setAttribute("setAuthorityUrl",setAuthorityUrl); //���ڹ�����У��
		
		//����
		/*System.out.println("setAuthorityUrl size:"+setAuthorityUrl.size());
		System.out.println("mapAuthority size:"+mapAuthority.size());
		for(WxAuthority a:mapAuthority.keySet()){
			System.out.println(a.getMenuurl());
			for(WxAuthority ca:mapAuthority.get(a))
				System.out.println(a.getName()+" de child:"+ca.getMenuurl());
		}*/
		return "wx/indexMain";
	}
	
	@RequestMapping("User/subSystem.action")
	public String subSystem(HttpSession session,String systabs){
		Set<String> setAuthorityUrl = new HashSet<String>();
		String username = (String) session.getAttribute("username");
		System.out.println("User/subSystem.action:username:"+username);
		String userid = userService.findIdByName(username);
		String roleName = userService.findRoleByName(username);
		session.setAttribute("userName", username);
		session.setAttribute("roleName", roleName);
		Map<String, Map<WxAuthority,Set<WxAuthority>>> mapAuthoritys
			= userService.getAuthority(userid, setAuthorityUrl);
		Map<WxAuthority,Set<WxAuthority>> mapAuthority = mapAuthoritys.get(systabs);
		
		
		session.setAttribute("mapAuthority", mapAuthority);//�����Զ������˵�
		session.setAttribute("setAuthorityUrl",setAuthorityUrl); //���ڹ�����У��
		return "wx/indexMain";
	}
	/**
	 * ��ȡ���е��û�
	 *<p>Title: UserController </p>
	 *<p>Description: UserController.java</p>
	 * Company:www.xjtu.com
	 * @author RedLee
	 * @date 2016��2��29��
	 * @version 1.0.0
	 * @param request
	 * @return
	 */
	@RequestMapping("User/getAllUser.action")
	public String getAllUser(HttpServletRequest request){
		List userList = userService.getAllUser();
		request.setAttribute("userList", userList);
		System.out.println("getAllUser.action");
		return "/wx/authority/UserList";
	}
	
	//��������û�����ǰ�Ȼ�ȡ�����б���еĽ�ɫ��Ϣ
	@RequestMapping("User/IntoAddUser.action")
	public String getAllRoleForAddUser(HttpServletRequest request){
		List list = roleService.getAllRole();
		request.setAttribute("roleList", list);
		return "wx/authority/UserAdd";
	}
	
	@RequestMapping("User/addUser.action")
	public String addUser(WxUser wxUser, String roleid){
		WxRole role = roleService.findById(roleid);
		wxUser.setWxRole(role);
		userService.addUser(wxUser);
		System.out.println("addUser.action");
		return "wx/success";
	}
	
	@RequestMapping(value = "User/checkUserName.action", method = RequestMethod.POST)
	public @ResponseBody Integer checkUserName(String username){
		//System.out.println(userService.findByName(username));
		return userService.findByName(username);
	}
	
	@RequestMapping("User/IntoModifyUser.action")
	public String findById(HttpServletRequest request,String id){
		WxUser user = userService.findById(id);
		List list = roleService.getAllRole();
		request.setAttribute("roleList", list);
		request.setAttribute("user", user);
		return "wx/authority/UserModify";
	}
	
	@RequestMapping("User/modifyUser.action")
	public String modifyUser(WxUser wxUser, String roleid){
		WxRole role = roleService.findById(roleid);
		wxUser.setWxRole(role);
		userService.modify(wxUser);
		return "wx/success";
	}
	
	@RequestMapping("User/delUser.action")
	public String delUser(String id){
		WxUser user = userService.findById(id);
		userService.del(user);
		return "wx/success";
	}
}
