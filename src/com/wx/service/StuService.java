package com.wx.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wx.dao.StuDAO;

@Service
public class StuService {
	@Resource
	private StuDAO stuDAO;
	
	public List getAll(){
		return stuDAO.findAll();
	}
}
