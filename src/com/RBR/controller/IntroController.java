package com.RBR.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ��ҳ�еĽ���
 * @author Gao Haoyang
 *
 */
@Controller
@RequestMapping(value = "/intro")
public class IntroController {
		@RequestMapping(value = "/RBRintroduce.action")
		public String introduce() {
			return "/RBR/intro/intro";
		}
		@RequestMapping(value = "/RBRechart.action")
		public String introduceChart(Model model) {
//			System.out.println("treeData");
//		String treeData = "{\"name\":\"�������\",\"children\":[{\"name\":\"�ᾧ����Һ�洦����\",\"children\":[{\"name\":\"������\"},{\"name\":\"����\"},{\"name\":\"��������\"},{\"name\":\"����׼ȷ����ͬһλ��\"}]},{\"name\":\"���޽ᾧ������������Ƶ�λ��\",\"children\":[{\"name\":\"�ᾧ����Һ�洦����\"}]}]}";
//			model.addAttribute("treeData",treeData);
			
			return "/RBR/intro/Tree";
		}
}
