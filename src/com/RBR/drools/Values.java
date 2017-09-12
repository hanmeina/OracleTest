package com.RBR.drools;

import java.util.HashMap;

import java.util.Map;

public class Values {
	
	public static Map<String, String> map;
	
	public static String result;		//将最后结果存入这个字符串
	
	//将字母字符串转化为语句
	public static String lettersToWords(String letterString) {
		result = "";
		initStringValues();							//初始化map
		String ss[] = letterString.split(",");		//用逗号将结果字母字符串分割成字符串数组
		
		for (int i = 0; i < ss.length; i++) {		//遍历字符串数组
			result=result+"    "+(i+1)+". "+map.get(ss[i])+'\n';		//通过键得到值，用逗号隔开，并存入字符串
//			System.out.println((i+1)+"."+map.get(ss[i]));	//按序号每行打印一次
		}
		
		return result;
	}
	
	public static String num2Words(Integer num) {
		return map.get("a" + num);
	}
	
	//初始化map
	public static void initStringValues () {
		map = new HashMap<String, String>();
		map.put("a1", "纵裂纹");
		map.put("a2", "局部");
		map.put("a3", "浅短裂纹（浅-短）");
		map.put("a4", "与钢种无关");
		map.put("a5", "渣线（圈）");
		map.put("a6", "去除渣线");
		map.put("a7", "结晶器液面不稳定");
		map.put("a8", "检查结晶器液面控制系统");
		
		map.put("a9", "区域");
		map.put("a10", "较深裂纹");
		map.put("a11", "总是准确地在同一位置");
		map.put("a12", "结晶器在液面处受损");
		map.put("a13", "检修结晶器宽面出现裂纹的位置");
		map.put("a14", "总是出现在宽侧中间");
		map.put("a15", "厚板坯");
		map.put("a16", "结晶器冷却不均匀");
		map.put("a17", "调整SEN与结晶器壁之间的距离");
		map.put("a18", "板坯中某些元素含量不在规定范围");
		map.put("a19", "调整元素含量");
		map.put("a20", "连续裂纹");
		map.put("a21", "调整结晶器冷却均匀度");
		map.put("a22", "宽面");
		map.put("a23", "浅裂纹（浅-长）");
		map.put("a24", "无规则分布");
		map.put("a25", "结晶器中形成的坯壳不均匀");
		map.put("a26", "调整拉速等（找出坯壳不均匀原因）");
		map.put("a27", "边缘旁（20-120mm）");
		map.put("a28", "最大深度5mm");
		map.put("a29", "发生在每个钢种");
		map.put("a30", "调整结晶器锥度");
		map.put("a31", "在振动痕迹外有短线,纵向延伸,和窄侧鼓肚无关");
		map.put("a32", "调整辊缝、辊子对弧");
		map.put("a33", "边缘上");
		map.put("a34", "深度（2-3mm）");
		map.put("a35", "长度（0.5-10m）");
		map.put("a36", "常发生在低碳钢上");
		map.put("a37", "调整结晶器铜板接缝");
	}
		
	
}

