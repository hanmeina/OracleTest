package com.wx.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wx.dao.WxAuthorityDAO;
import com.wx.dao.WxRoleAuthorityDAO;
import com.wx.dao.WxRoleDAO;
import com.wx.model.WxAuthority;
import com.wx.model.WxRole;
import com.wx.model.WxRoleAuthority;

@Service
public class AuthorityService {
	@Resource
	private WxAuthorityDAO wxAuthorityDAO;
	@Resource
	private WxRoleDAO wxRoleDAO;
	@Resource
	private WxRoleAuthorityDAO wxRoleAuthorityDAO;
	
	public String createAuthorityTree(String roleid){
		//获得roleid具有的权限
		Set<Integer> roleA = getAuthorityByRole(roleid);
		//获得全部权限
		List<WxAuthority> fathers = wxAuthorityDAO.getAllParentAuthority();
		//System.out.println(father.size());
		StringBuffer js = new StringBuffer();
		js.append("var arr = [];");
		for(WxAuthority father:fathers){
			//获取父节点的子节点
			Set<WxAuthority> childs = father.getWxAuthorities();
			Set<WxAuthority> sortChlids = new TreeSet<WxAuthority>(childs);
			int checkedChildSize = 0;//当前父节点下应该选中的子节点个数
			js.append("var subarr = [];");
			for(WxAuthority chlid:sortChlids){
				int checkstate = 0;//roleid对应的角色是否有当前权限（子节点选中状态）
				if(roleA.contains(chlid.getAuthorityid())){
					checkstate = 1;
					checkedChildSize++; 
				}
				//节点属性设置(子节点)
				js.append("subarr.push({" +
						  "'id' : '"+chlid.getAuthorityid()+"'," +
						  "'text' : '"+chlid.getName()+"'," +
						  "'value' : '"+chlid.getAuthorityid()+"'," +
						  "'showcheck' : true," +
						  " complete : true," +
						  "'isexpand' : false," +
						  "'checkstate' : "+checkstate+"," +
						  "'hasChildren' : false });");
			}
			int fatherState = 0; //父节点的选中状态
			int allchildSize = childs.size();
			//需要考虑当前父节点没有子节点的情况
			if((checkedChildSize>0 && checkedChildSize==allchildSize) 
					|| (roleA.contains(father.getAuthorityid())))
				fatherState = 1; //全选中
			else if(checkedChildSize>0 && checkedChildSize<allchildSize)
				fatherState = 2; //半选中
			//添加父节点(父节点的value前加个f标记)
			js.append("arr.push( {"+
					  "'id' : '"+father.getAuthorityid()+"'," +
					  "'text' : '"+father.getName()+"'," +
					  "'value' : 'f"+father.getAuthorityid()+"'," +
					  "'showcheck' : true," +
					  " complete : true," +
					  "'isexpand' : false," +
					  "'checkstate' : "+fatherState+"," +
					  "'hasChildren' : true," +
					  "'ChildNodes' : subarr});");
		}
		return js.toString();
	}
	
	//返回存有roleid对应的所有角色的权限id集合
	public Set<Integer> getAuthorityByRole(String roleid){
		
		List<Integer> list = wxRoleAuthorityDAO.findAuthorityByRole(roleid);
		//System.out.println(list.get(0));
		Set<Integer> set = new HashSet<Integer>(list);
		return set;
	}
	
	//获得某个父权限节点对应的子节点数
	public Long getChildSize(String pid){
		Long count = wxAuthorityDAO.getChildSize(pid);
		System.out.println(count);
		return count;
	}
	//删除某个角色对应的所有权限
	public void delAuthorityByRole(String roleid){
		wxRoleAuthorityDAO.delAuthorityByRole(roleid);
	}

	//为某个角色添加权限
	public void addAuthorityForRole(String roleid,String authorityid){
		WxRole role = wxRoleDAO.findById(roleid);
		WxAuthority authority = wxAuthorityDAO.findById(Integer.parseInt(authorityid));
		WxRoleAuthority ra = new WxRoleAuthority();
		ra.setWxAuthority(authority);
		ra.setWxRole(role);
		wxRoleAuthorityDAO.save(ra);
	}
	
	//修改roleid对应角色的权限(先删除原有权限再添加新权限，故此方法应添加事务处理)
	@Transactional(rollbackFor={Exception.class})
	public void modifyAuthority(String roleid, String authoritys){
		//1.先删除roleAuthority表中所有role对应的权限
		delAuthorityByRole(roleid);
		//if(true)throw new Exception();
		//2.再从将新选择的权限加入roleAuthority表
		String[] as= authoritys.split(","); 
		for(String a:as){
			if(a.startsWith("f")){//父节点
				a = a.substring(1); //截掉f
				Long childCount = getChildSize(a);
				if(childCount>0) //不属于“首页”等没有子节点的权限
					continue;
				
			}
			addAuthorityForRole(roleid,a); //其余节点都添加
		}
	} 
}
