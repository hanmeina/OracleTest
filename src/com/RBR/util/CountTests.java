package com.RBR.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CountTests {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("5,1,5,9,3,4,7");
		list.add("9,6,5,47,8,2");
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		Set<Map<Integer, Integer>> set = new HashSet<Map<Integer, Integer>>();
		for(String s : list){
			String[] sarray = s.split(",");
			for(int i = 0 ; i<sarray.length ; i++){
				if(!map.keySet().contains(Integer.valueOf(sarray[i]))){
					map.put(Integer.valueOf(sarray[i]), 1);
				}else{
					map.put(Integer.valueOf(sarray[i]), map.get(Integer.valueOf(sarray[i]))+1);
				}
			}
		}
		for(Map.Entry<Integer, Integer> entry : map.entrySet()){
			System.out.println("key = "+entry.getKey()+" --> value = "+entry.getValue());
		}
		
	}
}
