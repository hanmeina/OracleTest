package com.RBR.controller;

import java.util.List;

import com.RBR.model.MetaData;

public class Test {
	public static void main(String[] args) {
		String inputText = "����ѹǿ";
		String trendDescribeWord = "����"; 
		
		String inverseInputText = inputText.replace(trendDescribeWord, "��С");
		System.out.println("inverseInputText:"+inverseInputText);
	}

}
