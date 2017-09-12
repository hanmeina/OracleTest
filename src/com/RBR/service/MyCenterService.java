package com.RBR.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.RBR.dao.MyCenterDAO;

@Service
public class MyCenterService {
	@Resource
	private MyCenterDAO myCenterDAO;
	@Resource
	private LogService logService;
	public List getMyMetaData(String managerName){
		return myCenterDAO.getMyMetaData(managerName);
	}
	public List getMyPassedRules(String managerName){
		return myCenterDAO.getMyPassedRules(managerName);
	}
	public List getMyNotPassRules(String managerName){
		return myCenterDAO.getMyNoPassRules(managerName);
	}
	public List getMySleepRules(String managerName){
		return myCenterDAO.getMySleepRules(managerName);
	}
	public List getMyPendingRules(String managerName){
		return myCenterDAO.getMyPendingRules(managerName);
	}
	public List getAllMyRules(String managerName){
		return myCenterDAO.getAllMyRules(managerName);
	}
	public List getMyInferenceInfo(String username){
		return logService.findRBRInferenceInfo(username);
	}
}
