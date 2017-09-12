package com.RBR.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ��Ϣ�Ľ�����������������Ϣ���յ�����Ϣ
 * @author Gao Haoyang
 *
 */
@Controller
@RequestMapping("/message")
public class MessageController {
	
	@RequestMapping(value = "/tab", method = RequestMethod.GET)
	public String tab(){
		return "/RBR/message/tab";
	}
	
	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public String send(){
		return "/RBR/message/send";
	}
	
	@RequestMapping(value = "/receive", method = RequestMethod.GET)
	public String receive(){
		return "/RBR/message/receive";
	}

}
