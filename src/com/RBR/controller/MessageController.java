package com.RBR.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 消息的交互，包括发出的消息，收到的消息
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
