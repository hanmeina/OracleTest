package com.RBR.controller;

import java.util.List;

import com.RBR.model.MetaData;

public class Test {
	public static void main(String[] args) {
		String inputText = "增大压强";
		String trendDescribeWord = "增大"; 
		
		String inverseInputText = inputText.replace(trendDescribeWord, "减小");
		System.out.println("inverseInputText:"+inverseInputText);
	}

}
