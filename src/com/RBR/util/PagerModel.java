package com.RBR.util;

import java.util.List;

public class PagerModel {
	/** 
     * �ܼ�¼�� 
     */  
    private Long total;  
    /** 
     * ��ǰҳ����� 
     */  
    private List datas;  
    /**
     * ��ѯ������
     * @return
     */
    private String queryString;
    /**
     * �Ƿ��ѯ
     * @return
     */
    public String isSearch;
    /**
     * ��¼��ϵͳ
     * @return
     */
    public String subSystem;
    /**
     * ��¼����
     * @return
     */
    public String operate;
    /**
     * ��¼��������
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
