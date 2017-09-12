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
	 * 根据id查询一个Dimension对象
	 * @param id
	 * @return
	 */
	public Dimension getDimensionById(String id) {
		return dimensionDao.getById(id);
	}
	/**
	 * 根据name查询一个Dimension对象
	 */
	public List getDimensionByName(String name) {
		return dimensionDao.getByName(name);
	}
	
}
