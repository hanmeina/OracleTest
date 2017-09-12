package com.RBR.util;

import java.util.List;

public class PagerModel {
	/** 
     * 总记录数 
     */  
    private Long total;  
    /** 
     * 当前页结果集 
     */  
    private List datas;  
    /**
     * 查询的条件
     * @return
     */
    private String queryString;
    /**
     * 是否查询
     * @return
     */
    public String isSearch;
    /**
     * 记录子系统
     * @return
     */
    public String subSystem;
    /**
     * 记录操作
     * @return
     */
    public String operate;
    /**
     * 记录操作对象
     * @return
     */
    public String operateSubject;
    
    public String getSubSystem() {
		return subSystem;
	}
	public void setSubSystem(String subSystem) {
		this.subSystem = subSystem;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public String getOperateSubject() {
		return operateSubject;
	}
	public void setOperateSubject(String operateSubject) {
		this.operateSubject = operateSubject;
	}
	public String getIsSearch() {
		return isSearch;
	}
	public void setIsSearch(String isSearch) {
		this.isSearch = isSearch;
	}
	public String getQueryString() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	public List getDatas() {  
        return datas;  
    }  
    public void setDatas(List datas) {  
        this.datas = datas;  
    }  
    public Long getTotal() {  
        return total;  
    }  
    public void setTotal(Long total) {  
        this.total = total;  
    }  
}
