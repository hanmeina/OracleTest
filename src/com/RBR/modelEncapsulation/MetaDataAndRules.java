package com.RBR.modelEncapsulation;

import java.util.ArrayList;
import java.util.List;

import com.RBR.model.MetaData;



public class MetaDataAndRules {
	List<MetaData> metaDatasList = new ArrayList<MetaData>();
	List<Integer> rulesIdList = new ArrayList<Integer>();
	public List<MetaData> getMetaDatasList() {
		return metaDatasList;
	}
	public List<Integer> getRulesIdList() {
		return rulesIdList;
	}
}
