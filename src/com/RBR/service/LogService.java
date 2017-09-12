package com.RBR.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.drools.lang.DRLParser.package_id_return;
import org.springframework.stereotype.Service;

import com.RBR.dao.DimensionDAO;
import com.RBR.dao.LogDAO;
import com.RBR.model.Dimension;
import com.RBR.model.Log;
import com.RBR.model.ReasoningTree;
import com.RBR.util.PagerModel;
import com.RBR.util.Pagination;
import com.RBR.util.SystemContext;
import com.wx.model.WxAuthority;


@Service
public class LogService {
	
	
	@Resource
	private LogDAO logDAO;
	@Resource
	private DimensionDAO dimensionDAO;
	
	public boolean saveLog(String userName, String content,
			ReasoningTree reasoningTree, Dimension subSystem,
			Dimension operate, Dimension operateSubject){
		Log log = new Log();
		log.setUserName(userName);
		log.setOperateTime(new Date());
		log.setContent(content);
		log.setReasoningTree(reasoningTree);
		log.setSubSystem(subSystem);
		log.setOperate(operate);
		log.setOperateSubject(operateSubject);
		log.setAuthorityId(40);
		return logDAO.save(log);
		
	}
	public List<Log> findByUsedRules(String id){
		List<Log> list = logDAO.getByUsedRules();
		List<Log> hasThisIdLoglist = new ArrayList<Log>();
		for(Log log : list){
			if(log.getReasoningTree().getUsedRules().contains(id)){
				hasThisIdLoglist.add(log);
			}
		}
		return list;
	}
	
	
//	public PagerModel findBySubSystemDimensionProperty(Object[] subSystem,Set<WxAuthority> wxAuthorities){
//		List<Log> list = logDAO.getBySubSystemDimensionProperty(subSystem,wxAuthorities,SystemContext.getOffset(),SystemContext.getPageSize());
//		PagerModel pm = new PagerModel();
//		pm.setTotal(list.size());
//		pm.setDatas(list);
//		return pm;
//	}
//	public PagerModel findBySubSystemAndOperateDimensionProperty(Object[] subSystemAndOperate,Set<WxAuthority> wxAuthorities){
//		List<Log> list = logDAO.getBySubSystemAndOperateDimensionProperty(subSystemAndOperate, SystemContext.getOffset(),SystemContext.getPageSize(),wxAuthorities);
//		PagerModel pm = new PagerModel();
//		pm.setTotal(list.size());
//		pm.setDatas(list);
//		return pm;
//	}
//	public PagerModel findBySubSystemAndOperateSubjectDimensionProperty(Object[] subSystemAndOperateSubject,Set<WxAuthority> wxAuthorities){
//		List<Log> list = logDAO.getBySubSystemAndOperateSubjectDimensonPrioperty(subSystemAndOperateSubject,SystemContext.getOffset(),SystemContext.getPageSize(),wxAuthorities);
//		PagerModel pm = new PagerModel();
//		pm.setTotal(list.size());
//		pm.setDatas(list);
//		return pm;
//	}
//	public PagerModel findBySubSystemAndOperateAndOperateSubjectDimensionProperty(Object[] subSystemAndOperateAndOperateSubject,Set<WxAuthority> wxAuthorities){
//		List<Log> list = logDAO.getBySubSystemAndOperateAndOperateSubjectDimensionProperty(subSystemAndOperateAndOperateSubject,SystemContext.getOffset(),SystemContext.getPageSize(),wxAuthorities);
//		PagerModel pm = new PagerModel();
//		pm.setTotal(list.size());
//		pm.setDatas(list);
//		return pm;
//	}
//	public PagerModel findByOperateDimensionProperty(Object[] operate,Set<WxAuthority> wxAuthorities){
//		List<Log> list = logDAO.getByOperateDimensionProperty(operate,SystemContext.getOffset(),SystemContext.getPageSize(),wxAuthorities);
//		PagerModel pm = new PagerModel();
//		pm.setTotal(list.size());
//		pm.setDatas(list);
//		return pm;
//	}
//	
//	public PagerModel findByOperateSubjectDimensionProperty(Object[] operateSubject,Set<WxAuthority> wxAuthorities){
//		List<Log> list = logDAO.getByOperateSubjectDimensionProperty(operateSubject,SystemContext.getOffset(),SystemContext.getPageSize(),wxAuthorities);
//		PagerModel pm = new PagerModel();
//		pm.setTotal(list.size());
//		pm.setDatas(list);
//		return pm;
//	}
//	public PagerModel findByOperateAndOperateSubjectDimensionProperty(Object[] operateAndOperateSubject,Set<WxAuthority> wxAuthorities){
//		List<Log> list = logDAO.getByOperateAndOperateSubjectDimensionProperty(operateAndOperateSubject,SystemContext.getOffset(),SystemContext.getPageSize(),wxAuthorities);
//		PagerModel pm = new PagerModel();
//		pm.setTotal(list.size());
//		pm.setDatas(list);
//		return pm;
//	}
	
//	public PagerModel findAll(){
//		List<Log> list = logDAO.findAll(SystemContext.getOffset(),SystemContext.getPageSize());
//		PagerModel pm = new PagerModel();
////		System.out.println("findALL List.size()"+list.size());
//		pm.setTotal(logDAO.findLogSize());
//		pm.setDatas(list);
//		return pm;
//	}
	
	public PagerModel findLog(String subSystem ,String operate ,String operateSubject,Map<String, Map<WxAuthority,Set<WxAuthority>>> mapAuthoritys){
		List<Log> list = new ArrayList<Log>();
		PagerModel pm = new PagerModel();
		Set<Integer> setAuthorityId = new HashSet<Integer>(0);
		Map<WxAuthority,Set<WxAuthority>> map1 = mapAuthoritys.get("1");
		Map<WxAuthority,Set<WxAuthority>> map2 = mapAuthoritys.get("2");
		Map<WxAuthority,Set<WxAuthority>> map3 = mapAuthoritys.get("3");
		Map<WxAuthority,Set<WxAuthority>> map4 = mapAuthoritys.get("4");
		for(Map.Entry<WxAuthority, Set<WxAuthority>> entry : map1.entrySet()){
			for(WxAuthority wa : entry.getValue()){
				setAuthorityId.add(wa.getAuthorityid());
			}
		}
		for(Map.Entry<WxAuthority, Set<WxAuthority>> entry : map2.entrySet()){
			for(WxAuthority wa : entry.getValue()){
				setAuthorityId.add(wa.getAuthorityid());
			}
		}
		for(Map.Entry<WxAuthority, Set<WxAuthority>> entry : map3.entrySet()){
			for(WxAuthority wa : entry.getValue()){
				setAuthorityId.add(wa.getAuthorityid());
			}
		}
		for(Map.Entry<WxAuthority, Set<WxAuthority>> entry : map4.entrySet()){
			for(WxAuthority wa : entry.getValue()){
				setAuthorityId.add(wa.getAuthorityid());
			}
		}
		
		
		
		if(!isZero(subSystem) && isZero(operate) && isZero(operateSubject)){
			System.out.println("进入：!isZero(subSystem) && isZero(operate) && isZero(operateSubject)");
			Object[] object = new Object[]{dimensionDAO.getById(subSystem)};
			list = logDAO.getBySubSystemDimensionProperty(object,setAuthorityId,SystemContext.getOffset(),SystemContext.getPageSize());
			System.out.println("list:"+list);
			pm.setTotal(logDAO.getByTotalSubSystemDimensionProperty(object,setAuthorityId));
			pm.setDatas(list);
			pm.setIsSearch("1");
			pm.setSubSystem(subSystem);
			pm.setOperate(operate);
			pm.setOperateSubject(operateSubject);
//			pm.setQueryString("您查询的是 子系统："+dimensionDAO.getById(subSystem).getName());
			pm.setQueryString("您查询的是 子系统："+((Dimension) object[0]).getName());
		}
		else
		if(isZero(subSystem) && !isZero(operate) && isZero(operateSubject)){
			System.out.println("进入：isZero(subSystem) && !isZero(operate) && isZero(operateSubject)");
			Object[] object = new Object[]{dimensionDAO.getById(operate)};
			list = logDAO.getByOperateDimensionProperty(object,SystemContext.getOffset(),SystemContext.getPageSize(),setAuthorityId);
			pm.setTotal(logDAO.getByToalOperateDimensionProperty(object,setAuthorityId));
			pm.setDatas(list);
			pm.setIsSearch("1");
			pm.setSubSystem(subSystem);
			pm.setOperate(operate);
			pm.setOperateSubject(operateSubject);
			pm.setQueryString("您查询的是子系统："+((Dimension) object[0]).getName());
		}else
		if(isZero(subSystem) && isZero(operate) && !isZero(operateSubject)){
			System.out.println("进入：isZero(subSystem) && isZero(operate) && !isZero(operateSubject)");
			Object[] object = new Object[]{dimensionDAO.getById(operateSubject)};
			list = logDAO.getByOperateSubjectDimensionProperty(object,SystemContext.getOffset(),SystemContext.getPageSize(),setAuthorityId);
			pm.setTotal(logDAO.getByTotalOperateSubjectDimensionProperty(object,setAuthorityId));
			pm.setDatas(list);
			pm.setIsSearch("1");
			pm.setSubSystem(subSystem);
			pm.setOperate(operate);
			pm.setOperateSubject(operateSubject);
			pm.setQueryString("您查询的是子系统："+((Dimension) object[0]).getName());
		}
		if(!isZero(subSystem) && !isZero(operate) && isZero(operateSubject)){
			System.out.println("进入：!isZero(subSystem) && !isZero(operate) && isZero(operateSubject)");
			Object[] object = new Object[]{dimensionDAO.getById(subSystem),dimensionDAO.getById(operate)};
			list = logDAO.getBySubSystemAndOperateDimensionProperty(object,SystemContext.getOffset(),SystemContext.getPageSize(),setAuthorityId);
			pm.setTotal(logDAO.getByTotalSubSystemAndOperateDimensionProperty(object,setAuthorityId));
			pm.setDatas(list);
			pm.setIsSearch("1");
			pm.setSubSystem(subSystem);
			pm.setOperate(operate);
			pm.setOperateSubject(operateSubject);
			pm.setQueryString("您查询的是 子系统："+dimensionDAO.getById(subSystem).getName()+"、操作："+dimensionDAO.getById(operate).getName());
		}else 
		if(!isZero(subSystem) && isZero(operate) && !isZero(operateSubject)){
			System.out.println("进入：!isZero(subSystem) && isZero(operate) && !isZero(operateSubject)");
			Object[] object = new Object[]{dimensionDAO.getById(subSystem),dimensionDAO.getById(operateSubject)};
			list = logDAO.getBySubSystemAndOperateSubjectDimensonPrioperty(object,SystemContext.getOffset(),SystemContext.getPageSize(),setAuthorityId);	
			pm.setTotal(logDAO.getByTotalSubSystemAndOperateSubjectDimensonPrioperty(object,setAuthorityId));
			pm.setDatas(list);
			pm.setIsSearch("1");
			pm.setSubSystem(subSystem);
			pm.setOperate(operate);
			pm.setOperateSubject(operateSubject);
			pm.setQueryString("您查询的是 子系统："+dimensionDAO.getById(subSystem).getName()+"、操作："+dimensionDAO.getById(operateSubject).getName());
		}else 
		if(isZero(subSystem) && !isZero(operate) && !isZero(operateSubject)){
			System.out.println("进入：isZero(subSystem) && !isZero(operate) && !isZero(operateSubject)");
			Object[] object = new Object[]{dimensionDAO.getById(operate),dimensionDAO.getById(operateSubject)};
			list = logDAO.getByOperateAndOperateSubjectDimensionProperty(object,SystemContext.getOffset(),SystemContext.getPageSize(),setAuthorityId);
			pm.setTotal(logDAO.getByTotalOperateAndOperateSubjectDimensionProperty(object,setAuthorityId));
			pm.setDatas(list);
			pm.setIsSearch("1");
			pm.setSubSystem(subSystem);
			pm.setOperate(operate);
			pm.setOperateSubject(operateSubject);
			pm.setQueryString("您查询的是 操作："+dimensionDAO.getById(operate).getName()+"、操作对象："+dimensionDAO.getById(operateSubject).getName());
		}
		if(!isZero(subSystem) && !isZero(operate) && !isZero(operateSubject)){
			System.out.println("进入：!isZero(subSystem) && !isZero(operate) && !isZero(operateSubject)");
			Object[] object = new Object[]{dimensionDAO.getById(subSystem),dimensionDAO.getById(operate),dimensionDAO.getById(operateSubject)};
			list = logDAO.getBySubSystemAndOperateAndOperateSubjectDimensionProperty(object,SystemContext.getOffset(),SystemContext.getPageSize(),setAuthorityId);
			pm.setTotal(logDAO.getByTotalSubSystemAndOperateAndOperateSubjectDimensionProperty(object,setAuthorityId));
			pm.setDatas(list);
			pm.setIsSearch("1");
			pm.setSubSystem(subSystem);
			pm.setOperate(operate);
			pm.setOperateSubject(operateSubject);
			pm.setQueryString("您查询的是 子系统："+dimensionDAO.getById(subSystem).getName()+"、操作："+dimensionDAO.getById(operate).getName()+"、操作对象"+dimensionDAO.getById(operateSubject).getName());
		}
		return pm;
	}
	
//	public List<ArrayList<String>> getLogTag(Set urlSet){
//		List<ArrayList<String>> l = new ArrayList<ArrayList<String>>();
//		ArrayList<String> list = new ArrayList<String>();
//		// 在权限集合中未找到，提示不具有访问权限
//		String u2 = "User/subSystem.action?systabs=2";
//		String u3 = "User/subSystem.action?systabs=3";
//		String u4 = "User/subSystem.action?systabs=4";
//		if(hasSubsystemAuthority(mapAuthoritys, u2)){
//			list.add("1:CBR");
//		}
//		if(hasSubsystemAuthority(mapAuthoritys, u3)){
//			list.add("2:RBR");
//		}
//		if(hasSubsystemAuthority(mapAuthoritys, u4)){
//			list.add("3:MBR");
//		}
//		l.add(list);
//		return l;
//	}
	
//	public PagerModel getLog(Map<String, Map<WxAuthority,Set<WxAuthority>>> mapAuthoritys){
//		String u1 = "1";
//		String u2 = "2";
//		String u3 = "3";
//		String u4 = "4";
//		List<Log> list = new ArrayList<Log>();
//		PagerModel pm = new PagerModel();
//		if(hasSubsystemAuthority(mapAuthoritys, u1) && !hasSubsystemAuthority(mapAuthoritys, u2) && !hasSubsystemAuthority(mapAuthoritys, u3) && !hasSubsystemAuthority(mapAuthoritys, u4)){
//			Object[] object = new Object[]{dimensionDAO.getById("1")};
//			list = logDAO.getBySubSystemDimensionProperty(object,SystemContext.getOffset(),SystemContext.getPageSize());
//			pm.setTotal(logDAO.getByTotalSubSystemDimensionProperty(object));
//			pm.setDatas(list);
//			pm.setIsSearch("0");
//		}else
//		if(!hasSubsystemAuthority(mapAuthoritys, u1) && hasSubsystemAuthority(mapAuthoritys, u2) && !hasSubsystemAuthority(mapAuthoritys, u3) && !hasSubsystemAuthority(mapAuthoritys, u4)){
//			Object[] object = new Object[]{dimensionDAO.getById("2")};
//			list = logDAO.getBySubSystemDimensionProperty(object,SystemContext.getOffset(),SystemContext.getPageSize());
//			pm.setTotal(logDAO.getByTotalSubSystemDimensionProperty(object));
//			pm.setDatas(list);
//			pm.setIsSearch("0");
//		}else
//		if(!hasSubsystemAuthority(mapAuthoritys, u1) && !hasSubsystemAuthority(mapAuthoritys, u2) && hasSubsystemAuthority(mapAuthoritys, u3) && !hasSubsystemAuthority(mapAuthoritys, u4)){
//			Object[] object = new Object[]{dimensionDAO.getById("3")};
//			list = logDAO.getBySubSystemDimensionProperty(object,SystemContext.getOffset(),SystemContext.getPageSize());
//			pm.setTotal(logDAO.getByTotalSubSystemDimensionProperty(object));
//			pm.setDatas(list);
//			pm.setIsSearch("0");
//		}else
//		if(!hasSubsystemAuthority(mapAuthoritys, u1) && !hasSubsystemAuthority(mapAuthoritys, u2) && !hasSubsystemAuthority(mapAuthoritys, u3) && hasSubsystemAuthority(mapAuthoritys, u4)){
//			Object[] object = new Object[]{dimensionDAO.getById("4")};
//			list = logDAO.getBySubSystemDimensionProperty(object,SystemContext.getOffset(),SystemContext.getPageSize());
//			pm.setTotal(logDAO.getByTotalSubSystemDimensionProperty(object));
//			pm.setDatas(list);
//			pm.setIsSearch("0");
//		}
//		if(hasSubsystemAuthority(mapAuthoritys, u1) && hasSubsystemAuthority(mapAuthoritys, u2) && !hasSubsystemAuthority(mapAuthoritys, u3) && !hasSubsystemAuthority(mapAuthoritys, u4)){
//			Object[] object = new Object[]{dimensionDAO.getById("1"),dimensionDAO.getById("2")};
//			list = logDAO.getByTwoSubsystemDimensionProperty(object,SystemContext.getOffset(),SystemContext.getPageSize());
//			pm.setTotal(logDAO.getByTotalTwoSubsystemDimensionProperty(object));
//			pm.setDatas(list);
//			pm.setIsSearch("0");
//		}else if(hasSubsystemAuthority(mapAuthoritys, u1) && !hasSubsystemAuthority(mapAuthoritys, u2) && hasSubsystemAuthority(mapAuthoritys, u3) && !hasSubsystemAuthority(mapAuthoritys, u4)){
//			Object[] object = new Object[]{dimensionDAO.getById("1"),dimensionDAO.getById("3")};
//			list = logDAO.getByTwoSubsystemDimensionProperty(object,SystemContext.getOffset(),SystemContext.getPageSize());
//			pm.setTotal(logDAO.getByTotalTwoSubsystemDimensionProperty(object));
//			pm.setDatas(list);
//			pm.setIsSearch("0");
//		}else if(hasSubsystemAuthority(mapAuthoritys, u1) && !hasSubsystemAuthority(mapAuthoritys, u2) && !hasSubsystemAuthority(mapAuthoritys, u3) && hasSubsystemAuthority(mapAuthoritys, u4)){
//			Object[] object = new Object[]{dimensionDAO.getById("1"),dimensionDAO.getById("4")};
//			list = logDAO.getByTwoSubsystemDimensionProperty(object,SystemContext.getOffset(),SystemContext.getPageSize());
//			pm.setTotal(logDAO.getByTotalTwoSubsystemDimensionProperty(object));
//			pm.setDatas(list);
//			pm.setIsSearch("0");
//		}else
//		if(!hasSubsystemAuthority(mapAuthoritys, u1) && hasSubsystemAuthority(mapAuthoritys, u2) && hasSubsystemAuthority(mapAuthoritys, u3) && !hasSubsystemAuthority(mapAuthoritys, u4)){
//			Object[] object = new Object[]{dimensionDAO.getById("2"),dimensionDAO.getById("3")};
//			list = logDAO.getByTwoSubsystemDimensionProperty(object,SystemContext.getOffset(),SystemContext.getPageSize());
//			pm.setTotal(logDAO.getByTotalTwoSubsystemDimensionProperty(object));
//			pm.setDatas(list);
//			pm.setIsSearch("0");
//		}else 
//		if(!hasSubsystemAuthority(mapAuthoritys, u1) && hasSubsystemAuthority(mapAuthoritys, u2) && !hasSubsystemAuthority(mapAuthoritys, u3) && hasSubsystemAuthority(mapAuthoritys, u4)){
//			Object[] object = new Object[]{dimensionDAO.getById("2"),dimensionDAO.getById("4")};
//			list = logDAO.getByTwoSubsystemDimensionProperty(object,SystemContext.getOffset(),SystemContext.getPageSize());	
//			pm.setTotal(logDAO.getByTotalTwoSubsystemDimensionProperty(object));
//			pm.setDatas(list);
//			pm.setIsSearch("0");
//		}else 
//		if(!hasSubsystemAuthority(mapAuthoritys, u1) && !hasSubsystemAuthority(mapAuthoritys, u2) && hasSubsystemAuthority(mapAuthoritys, u3) && hasSubsystemAuthority(mapAuthoritys, u4)){
//			Object[] object = new Object[]{dimensionDAO.getById("3"),dimensionDAO.getById("4")};
//			list = logDAO.getByTwoSubsystemDimensionProperty(object,SystemContext.getOffset(),SystemContext.getPageSize());
//			pm.setTotal(logDAO.getByTotalTwoSubsystemDimensionProperty(object));
//			pm.setDatas(list);
//			pm.setIsSearch("0");
//		}
//		if(hasSubsystemAuthority(mapAuthoritys, u1) && hasSubsystemAuthority(mapAuthoritys, u2) && hasSubsystemAuthority(mapAuthoritys, u3) && !hasSubsystemAuthority(mapAuthoritys, u4)){
//			Object[] object = new Object[]{dimensionDAO.getById("1"),dimensionDAO.getById("2"),dimensionDAO.getById("3")};
//			list = logDAO.getByThreeSubsystemDimensionProperty(object,SystemContext.getOffset(),SystemContext.getPageSize());
//			pm.setTotal(logDAO.getByTotalThreeSubsystemDimensionProperty(object));
//			pm.setDatas(list);
//			pm.setIsSearch("0");
//		}else if(hasSubsystemAuthority(mapAuthoritys, u1) && hasSubsystemAuthority(mapAuthoritys, u2) && !hasSubsystemAuthority(mapAuthoritys, u3) && hasSubsystemAuthority(mapAuthoritys, u4)){
//			Object[] object = new Object[]{dimensionDAO.getById("1"),dimensionDAO.getById("2"),dimensionDAO.getById("4")};
//			list = logDAO.getByThreeSubsystemDimensionProperty(object,SystemContext.getOffset(),SystemContext.getPageSize());
//			pm.setTotal(logDAO.getByTotalThreeSubsystemDimensionProperty(object));
//			pm.setDatas(list);
//			pm.setIsSearch("0");
//		}else
//		if(!hasSubsystemAuthority(mapAuthoritys, u1) && hasSubsystemAuthority(mapAuthoritys, u2) && hasSubsystemAuthority(mapAuthoritys, u3) && hasSubsystemAuthority(mapAuthoritys, u4)){
//			Object[] object = new Object[]{dimensionDAO.getById("2"),dimensionDAO.getById("3"),dimensionDAO.getById("4")};
//			list = logDAO.getByThreeSubsystemDimensionProperty(object,SystemContext.getOffset(),SystemContext.getPageSize());
//			pm.setTotal(logDAO.getByTotalThreeSubsystemDimensionProperty(object));
//			pm.setDatas(list);
//			pm.setIsSearch("0");
//		}
//		if(hasSubsystemAuthority(mapAuthoritys, u1) && hasSubsystemAuthority(mapAuthoritys, u2) && hasSubsystemAuthority(mapAuthoritys, u3) && hasSubsystemAuthority(mapAuthoritys, u4)){
//			Object[] object = new Object[]{dimensionDAO.getById("1"),dimensionDAO.getById("2"),dimensionDAO.getById("3"),dimensionDAO.getById("4")};
//			list = logDAO.getByFourSubsystemDimensionProperty(object,SystemContext.getOffset(),SystemContext.getPageSize());
//			pm.setTotal(logDAO.getByTotalFourSubsystemDimensionProperty(object));
//			pm.setDatas(list);
//			pm.setIsSearch("0");
//		}
//		return pm;
//	}	
	
	public PagerModel getLogList(Map<String, Map<WxAuthority,Set<WxAuthority>>> mapAuthoritys){
		Set<Integer> setAuthorityId = new HashSet<Integer>(0);
		PagerModel pm = new PagerModel();
		
		Map<WxAuthority,Set<WxAuthority>> map1 = mapAuthoritys.get("1");
		Map<WxAuthority,Set<WxAuthority>> map2 = mapAuthoritys.get("2");
		Map<WxAuthority,Set<WxAuthority>> map3 = mapAuthoritys.get("3");
		Map<WxAuthority,Set<WxAuthority>> map4 = mapAuthoritys.get("4");
		for(Map.Entry<WxAuthority, Set<WxAuthority>> entry : map1.entrySet()){
			for(WxAuthority wa : entry.getValue()){
				setAuthorityId.add(wa.getAuthorityid());
			}
		}
		for(Map.Entry<WxAuthority, Set<WxAuthority>> entry : map2.entrySet()){
			for(WxAuthority wa : entry.getValue()){
				setAuthorityId.add(wa.getAuthorityid());
			}
		}
		for(Map.Entry<WxAuthority, Set<WxAuthority>> entry : map3.entrySet()){
			for(WxAuthority wa : entry.getValue()){
				setAuthorityId.add(wa.getAuthorityid());
			}
		}
		for(Map.Entry<WxAuthority, Set<WxAuthority>> entry : map4.entrySet()){
			for(WxAuthority wa : entry.getValue()){
				setAuthorityId.add(wa.getAuthorityid());
			}
		}
//		for(Integer w:setAuthorityId){
//			System.out.println(w);
//		}
		List<Log> list = logDAO.getByWxAuthorityProperty(setAuthorityId, SystemContext.getOffset(),SystemContext.getPageSize());
		pm.setTotal(logDAO.getByWxAuthorityProperty(setAuthorityId));
		pm.setDatas(list);
		pm.setIsSearch("0");
		return pm;
	}
	
	public List findRBRInferenceInfo(String username){
		String hql = "from Log as model where model.subSystem = ? and model.operate = ? and model.operateSubject = ? and model.userName = ? order by model.id asc";
		Object[] object = new Object[]{dimensionDAO.getById("3"),dimensionDAO.getById("10"),dimensionDAO.getById("20"),username};
		return logDAO.findRBRInferenceInfo(hql, object);
	}
	public HashMap<Integer, String> getQueryFormTag(Map<String, Map<WxAuthority,Set<WxAuthority>>> mapAuthoritys){
		String u1 = "1";
		String u2 = "2";
		String u3 = "3";
		String u4 = "4";
		HashMap<Integer,String> map = new HashMap<Integer,String>();
		if(hasSubsystemAuthority(mapAuthoritys, u1)){
			map.put(1, "总控");
			map.put(5, "增加");
			map.put(6, "修改");
			map.put(7, "删除");
			map.put(14, "添加");
			map.put(15, "取消");
			map.put(22, "角色");
			map.put(23, "授权");
		}
		
		if(hasSubsystemAuthority(mapAuthoritys, u2)){
			map.put(2, "CBR");
			map.put(5, "增加");
			map.put(6, "修改");
			map.put(7, "删除");
			map.put(8, "审核");
			map.put(12, "常规推理");
			map.put(13, "高级推理");
			map.put(16, "通用案例模板");
			map.put(17, "生产线案例模板");
			map.put(18, "案例");
		}
		if(hasSubsystemAuthority(mapAuthoritys, u3)){
			map.put(3, "RBR");
			map.put(5, "增加");
			map.put(6, "修改");
			map.put(7, "删除");
			map.put(8, "审核");
			map.put(9, "休眠");
			map.put(10, "推理");
			map.put(19, "元数据");
			map.put(20, "规则");
		}
		if(hasSubsystemAuthority(mapAuthoritys, u4)){
			map.put(4, "MBR");
			map.put(5, "增加");
			map.put(5, "修改");
			map.put(6, "删除");
			map.put(7, "推理");
			map.put(11, "训练");
			map.put(21, "模型");
		}
		
		return map;
	}
	
	public boolean isZero(String number){
		try {
			int num = Integer.parseInt(number);
			if(num == 0){
				return true;
			}else{
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}
	}
	// 在urlSet中查找是否有与url对应的链接，若有则表示有权限
		public boolean hasSubsystemAuthority(Map<String, Map<WxAuthority,Set<WxAuthority>>> mapAuthoritys, String subsystem) {
			boolean isFind = false;
			if(!mapAuthoritys.get(subsystem).isEmpty()){
				isFind = true;
			}
			return isFind;
		}
}
