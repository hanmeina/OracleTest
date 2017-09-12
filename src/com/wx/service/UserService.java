package com.wx.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;




import com.wx.model.WxAuthority;
import com.wx.model.WxRole;
import com.wx.model.WxRoleAuthority;
import com.wx.model.WxUrl;
import com.wx.model.WxUser;
import com.wx.dao.WxUserDAO;


@Service
public class UserService {
	@Resource
	private WxUserDAO wxUserDAO;
	
	//获取菜单Map和权限url集合
	public Map<String, Map<WxAuthority,Set<WxAuthority>>> getAuthority(String userid, Set<String> setAuthorityUrl){
		//1.获得指定id的用户
		WxUser user = wxUserDAO.findById(userid);
		//2.获得用户对应的角色,并通过角色进而获得权限
		//Set<WxUserRole> setUserRole = user.getWxUserRoles();
		//Set<Authority> setAuthority = new TreeSet<Authority>();//有序集合（保证每次菜单的顺序固定）
		Map<WxAuthority,Set<WxAuthority>> mapAuthorityCon = new HashMap<WxAuthority,Set<WxAuthority>>();//保存菜单的目录结构
		Map<WxAuthority,Set<WxAuthority>> mapAuthorityCas = new HashMap<WxAuthority,Set<WxAuthority>>();//保存菜单的目录结构
		Map<WxAuthority,Set<WxAuthority>> mapAuthorityRul = new HashMap<WxAuthority,Set<WxAuthority>>();//保存菜单的目录结构
		Map<WxAuthority,Set<WxAuthority>> mapAuthorityMod = new HashMap<WxAuthority,Set<WxAuthority>>();//保存菜单的目录结构
		Map<String ,Map<WxAuthority,Set<WxAuthority>>> mapAuthority = new HashMap<String ,Map<WxAuthority,Set<WxAuthority>>>();
		//Set<Integer> setAuthorityId = new HashSet<Integer>();
		//for(WxUserRole ur:setUserRole){
			WxRole role = user.getWxRole();

			Set<WxRoleAuthority> listRoleAuthoritie = role.getWxRoleAuthorities();
			
			for(WxRoleAuthority ra:listRoleAuthoritie){
				WxAuthority a = ra.getWxAuthority();
				//用来区分子系统
				String sysTabs = a.getSystabs();
				//添加url（包括authority表中的菜单url和url表中的访问url）
				//String类型的Set在添加时直接比较内容是否重复，重复则不添加，故不需要重写hashcode和equals方法
				setAuthorityUrl.add(a.getMenuurl());

				Set<WxUrl> urls = a.getWxUrls();
				for(WxUrl u:urls){
					setAuthorityUrl.add(u.getUrl());
				}
				
				//对于hibernate缓存中保存的对象，主键相同的都为1个对象，故不需要重写hashcode和equals方法
				//在数据表中用户所具有的权限都是通过角色与子节点建立对应关系反应出来的（role_authority表中保存的都是子权限节点）
				//父权限节点只是为了在动态构建界面时显示用的
				//往map中存（应通过子节点找父节点）
				//if(a.getAuthority()!=null){//说明是子节点
					//Set<Authority> chlidSet = new TreeSet<Authority>(a.getAuthorities());//获得所有子节点
					//mapAuthority.put(a, chlidSet);
					WxAuthority father = a.getWxAuthority();//获取父节点
					//总控判断
					if(sysTabs == "1" || sysTabs.equals("1")){
					if(father!=null){//当前是子节点
						if(!mapAuthorityCon.containsKey(father)){ //原map中没有当前父节点
							Set<WxAuthority> chlidSet = new TreeSet<WxAuthority>(); //TreeSet会自动在添加元素时对Set排序，前提是Authority实现了Comparable接口
							chlidSet.add(a);
							mapAuthorityCon.put(father, chlidSet);
							setAuthorityUrl.add(father.getMenuurl());
							
						}
						else{//原map中已有当前父节点
							Set<WxAuthority> chlidSet = mapAuthorityCon.get(father);//new TreeSet<Authority>();
							/*System.out.println(father.getName());
							System.out.println(chlidSet.iterator().next().getName());
							System.out.println(a.getName());*/
							//chlidSet.addAll(mapAuthority.get(father));
							chlidSet.add(a);
							mapAuthorityCon.put(father, chlidSet);
							
						}
					}
					else{  //当前是父节点
						Set<WxAuthority> chlidSet = new TreeSet<WxAuthority>();
						mapAuthorityCon.put(a, chlidSet);
						
					}
			}	
					//判断案例
					if(sysTabs == "2" || sysTabs.equals("2")){
						if(father!=null){//当前是子节点
							if(!mapAuthorityCas.containsKey(father)){ //原map中没有当前父节点
								Set<WxAuthority> chlidSet = new TreeSet<WxAuthority>(); //TreeSet会自动在添加元素时对Set排序，前提是Authority实现了Comparable接口
								chlidSet.add(a);
								mapAuthorityCas.put(father, chlidSet);
								setAuthorityUrl.add(father.getMenuurl());
							}
							else{//原map中已有当前父节点
								Set<WxAuthority> chlidSet = mapAuthorityCas.get(father);//new TreeSet<Authority>();
								/*System.out.println(father.getName());
								System.out.println(chlidSet.iterator().next().getName());
								System.out.println(a.getName());*/
								//chlidSet.addAll(mapAuthority.get(father));
								chlidSet.add(a);
								mapAuthorityCas.put(father, chlidSet);
								
							}
						}
						else{  //当前是父节点
							Set<WxAuthority> chlidSet = new TreeSet<WxAuthority>();
							mapAuthorityCas.put(a, chlidSet);
						}
				}	
					//判断规则
					if(sysTabs == "3" || sysTabs.equals("3")){
						if(father!=null){//当前是子节点
							if(!mapAuthorityRul.containsKey(father)){ //原map中没有当前父节点
								Set<WxAuthority> chlidSet = new TreeSet<WxAuthority>(); //TreeSet会自动在添加元素时对Set排序，前提是Authority实现了Comparable接口
								chlidSet.add(a);
								mapAuthorityRul.put(father, chlidSet);
								setAuthorityUrl.add(father.getMenuurl());
							}
							else{//原map中已有当前父节点
								Set<WxAuthority> chlidSet = mapAuthorityRul.get(father);//new TreeSet<Authority>();
								/*System.out.println(father.getName());
								System.out.println(chlidSet.iterator().next().getName());
								System.out.println(a.getName());*/
								//chlidSet.addAll(mapAuthority.get(father));
								chlidSet.add(a);
								mapAuthorityRul.put(father, chlidSet);
							}
						}
						else{  //当前是父节点
							Set<WxAuthority> chlidSet = new TreeSet<WxAuthority>();
							mapAuthorityRul.put(a, chlidSet);
						}
				}
					//判断模型
					if(sysTabs == "4" || sysTabs.equals("4")){
						if(father!=null){//当前是子节点
							if(!mapAuthorityMod.containsKey(father)){ //原map中没有当前父节点
								Set<WxAuthority> chlidSet = new TreeSet<WxAuthority>(); //TreeSet会自动在添加元素时对Set排序，前提是Authority实现了Comparable接口
								chlidSet.add(a);
								mapAuthorityMod.put(father, chlidSet);
								setAuthorityUrl.add(father.getMenuurl());
								
							}
							else{//原map中已有当前父节点
								Set<WxAuthority> chlidSet = mapAuthorityMod.get(father);//new TreeSet<Authority>();
								/*System.out.println(father.getName());
								System.out.println(chlidSet.iterator().next().getName());
								System.out.println(a.getName());*/
								//chlidSet.addAll(mapAuthority.get(father));
								chlidSet.add(a);
								mapAuthorityMod.put(father, chlidSet);
								
							}
						}
						else{  //当前是父节点
							Set<WxAuthority> chlidSet = new TreeSet<WxAuthority>();
							mapAuthorityMod.put(a, chlidSet);
							
						}
				}
					
				//}
			}
		//}
		//排序后的map(hashMap在插入节点构建map时比treeMap更高效，插入完成后转为treeMap，treeMap默认根据key排序，前提仍是Authority实现排序接口)
		//mapAuthorityCon.put("1", mapAuthority);
		 Map<WxAuthority,Set<WxAuthority>> mapAuthoritySortCon = new TreeMap<WxAuthority,Set<WxAuthority>>(mapAuthorityCon);
		 Map<WxAuthority,Set<WxAuthority>> mapAuthoritySortCas = new TreeMap<WxAuthority,Set<WxAuthority>>(mapAuthorityCas);
		 Map<WxAuthority,Set<WxAuthority>> mapAuthoritySortRul = new TreeMap<WxAuthority,Set<WxAuthority>>(mapAuthorityRul);
		 Map<WxAuthority,Set<WxAuthority>> mapAuthoritySortMod = new TreeMap<WxAuthority,Set<WxAuthority>>(mapAuthorityMod);
		
		 mapAuthority.put("1", mapAuthoritySortCon);
		 mapAuthority.put("2", mapAuthoritySortCas);
		 mapAuthority.put("3", mapAuthoritySortRul);
		 mapAuthority.put("4", mapAuthoritySortMod);
		return mapAuthority;
	}
	
//	public Set<Integer> getAuthorityIdSet(String userid){
//		Set<Integer> setAuthorityId = new HashSet<Integer>(0);
//		WxUser user = wxUserDAO.findById(userid);
//		WxRole role = user.getWxRole();
//		Set<WxRoleAuthority> listRoleAuthoritie = role.getWxRoleAuthorities();
//		for(WxRoleAuthority ra:listRoleAuthoritie){
//			WxAuthority a = ra.getWxAuthority();
//			setAuthorityId.add(a.getAuthorityid());
//			Set<WxAuthority> listAuthoritie = a.getWxAuthorities();
//			for(WxAuthority wa : listAuthoritie){
//				setAuthorityId.add(wa.getAuthorityid());
//			}
//			WxAuthority father = a.getWxAuthority();
//			setAuthorityId.add(father.getAuthorityid());
//		}
//		System.out.println(setAuthorityId.toString());
//		return setAuthorityId;
//		
//	}
	
//	public Set<WxAuthority> getAuthorityIdSet(String userid){
//		Set<WxAuthority> setAuthority = new HashSet<WxAuthority>(0);
//		WxUser user = wxUserDAO.findById(userid);
//		WxRole role = user.getWxRole();
//		Set<WxRoleAuthority> listRoleAuthoritie = role.getWxRoleAuthorities();
//		for(WxRoleAuthority ra:listRoleAuthoritie){
//			WxAuthority a = ra.getWxAuthority();
//			setAuthority.add(ra.getWxAuthority());
//			Set<WxAuthority> listAuthoritie = a.getWxAuthorities();
////			for(WxAuthority wa : listAuthoritie){
////				setAuthorityId.add(wa.getAuthorityid());
////			}
//			setAuthority.addAll(listAuthoritie);
//			WxAuthority father = a.getWxAuthority();
//			setAuthority.add(father);
//		}
////		for(){
////			
////		}
//		System.out.println(setAuthority.toString());
//		return setAuthority;
//		
//	}
	
	
	public List getAllUser(){
		return wxUserDAO.findAll();
	}
	public void addUser(WxUser u){
		wxUserDAO.save(u);
	}
	
	//判断用户名是否存在
	public Integer findByName(String name){
		List list = wxUserDAO.findByUsername(name);
		return list.size();
	}
	
	public WxUser findById(String id){
		return wxUserDAO.findById(id);
	}
	public String findIdByName(String name){
		return wxUserDAO.findIdByName(name);
	}
	//根据用户名获取角色
	public String findRoleByName(String name){
		return wxUserDAO.findRoleByName(name);
	}
	public void modify(WxUser user){
		wxUserDAO.modify(user);
	}
	
	public void del(WxUser user){
		wxUserDAO.delete(user);
	}
}
