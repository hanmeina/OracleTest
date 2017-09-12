package com.RBR.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页中的介绍
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
//		String treeData = "{\"name\":\"推理结论\",\"children\":[{\"name\":\"结晶器在液面处受损\",\"children\":[{\"name\":\"纵裂纹\"},{\"name\":\"区域\"},{\"name\":\"较深裂纹\"},{\"name\":\"总是准确地在同一位置\"}]},{\"name\":\"检修结晶器宽面出现裂纹的位置\",\"children\":[{\"name\":\"结晶器在液面处受损\"}]}]}";
//			model.addAttribute("treeData",treeData);
			
			return "/RBR/intro/Tree";
		}
}
