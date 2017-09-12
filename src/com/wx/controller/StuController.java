package com.wx.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wx.service.StuService;

@Controller
public class StuController {
	@Resource
	private StuService stuService;
	
	@RequestMapping("Stu/test.action")
	public String test(){
		System.out.println("my controller");
		return "index";
	}
	
	
}
