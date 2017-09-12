package com.RBR.service;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.RBR.dao.DimensionDAO;
import com.RBR.model.Dimension;


@Service
public class DimensionService {
	@Resource
	private DimensionDAO dimensionDao;

	
	/**
	 * ����id��ѯһ��Dimension����
	 * @param id
	 * @return
	 */
	public Dimension getDimensionById(String id) {
		return dimensionDao.getById(id);
	}
	/**
	 * ����name��ѯһ��Dimension����
	 */
	public List getDimensionByName(String name) {
		return dimensionDao.getByName(name);
	}
	
}
