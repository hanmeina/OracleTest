package com.RBR.util;

import java.util.List;

//��װҳ����ʾ�߼�
public class Pagination
{

	private int totle;// �ܹ���������

	private int pageSize;// ÿҳ��ʾ������

	private int totlePage;// ���ж���ҳ

	private int index;// ��ǰ�ǵڼ�ҳ

	private List data; // ����

	private String path;// ����·��
	
	private boolean iSearch=false;//�ж��Ƿ�����
	
	private String searchName;//���صĲ�����
	
	private String timedate;
	
	private String timedateLate;
	
	private String showInfo;
	
	public String getTimedateLate() {
		return timedateLate;
	}

	public void setTimedateLate(String timedateLate) {
		this.timedateLate = timedateLate;
	}

	
	
	public String getShowInfo() {
		return showInfo;
	}

	public void setShowInfo(String showInfo) {
		this.showInfo = showInfo;
	}

	private String actionid;
	
	public String getTimedate() {
		return timedate;
	}

	public void setTimedate(String timedate) {
		this.timedate = timedate;
	}

	public String getActionid() {
		return actionid;
	}

	public void setActionid(String actionid) {
		this.actionid = actionid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	private String content;
	
	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public boolean isiSearch() {
		return iSearch;
	}

	public void setiSearch(boolean iSearch) {
		this.iSearch = iSearch;
	}

	public void setTotle(int totle)
	{
		this.totle = totle;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public void setIndex(int index)
	{
		this.index = index;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public int getTotle()
	{
		return totle;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public int getTotlePage()
	{
		return (this.totle + this.pageSize - 1) / this.pageSize;
	}

	public int getIndex()
	{
		return index;
	}

	public List getData()
	{
		return data;
	}

	public void setData(List data)
	{
		this.data = data;
	}

//	public String getPageDisplay()
//	{
//		StringBuffer displayInfo = new StringBuffer();
//		if (index == 0 || pageSize == 0)
//		{
//			displayInfo.append("û�з�ҳ����Ϣ!");
//		}
//		else
//		{
//			displayInfo.append("<div>");
//			displayInfo.append("��" + totle
//					+ "����¼&nbsp;&nbsp;&nbsp;&nbsp;ÿҳ<span style='color:#FF0000'>" + pageSize
//					+ "</span>��&nbsp;&nbsp;&nbsp;&nbsp;");
//			displayInfo.append("��<span style='color:#FF0000'>" + index
//					+ "</span>ҳ/��" + this.getTotlePage() + "ҳ&nbsp;&nbsp;&nbsp;&nbsp;");
//			// �ж������ǰ�ǵ�һҳ ����ҳ���͡���һҳ��ʧȥ����
//			if (index == 1)
//			{
//				displayInfo.append("��ҳ&nbsp;&nbsp;&nbsp;&nbsp;");
//				displayInfo.append("��һҳ&nbsp;&nbsp;&nbsp;&nbsp;");
//			} 
//			else
//			{
//				displayInfo.append("<a style='color: red' href='" + path
//						+ "index=1'>��ҳ&nbsp;&nbsp;&nbsp;&nbsp;</a>");
//				displayInfo.append("<a style='color: red' href='" + path + "index=" + (index - 1)
//						+ "'>��һҳ&nbsp;&nbsp;&nbsp;&nbsp;</a>&nbsp;");
//			}
//			if 
//			(index >= this.getTotlePage())
//			{
//				displayInfo.append("��һҳ&nbsp;&nbsp;&nbsp;&nbsp;");
//				displayInfo.append("ĩҳ&nbsp;&nbsp;&nbsp;&nbsp;");
//			}
//			else
//			{
//				displayInfo.append("<a style='color: red' href='" + path + "index=" + (index + 1)
//						+ "'>��һҳ&nbsp;&nbsp;&nbsp;&nbsp;</a>");
//				displayInfo.append("<a style='color: red' href='" + path + "index="
//						+ this.getTotlePage() + "'>ĩҳ</a>&nbsp;&nbsp;&nbsp;&nbsp;");
//			}
//			displayInfo.append("</div>");
//		}
//		return displayInfo.toString();
//	}
}

