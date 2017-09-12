package com.RBR.util;

import java.util.List;

//封装页面显示逻辑
public class Pagination
{

	private int totle;// 总共的数据量

	private int pageSize;// 每页显示多少条

	private int totlePage;// 共有多少页

	private int index;// 当前是第几页

	private List data; // 数据

	private String path;// 连接路径
	
	private boolean iSearch=false;//判断是否搜索
	
	private String searchName;//搜素的操作人
	
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
//			displayInfo.append("没有分页的信息!");
//		}
//		else
//		{
//			displayInfo.append("<div>");
//			displayInfo.append("共" + totle
//					+ "条记录&nbsp;&nbsp;&nbsp;&nbsp;每页<span style='color:#FF0000'>" + pageSize
//					+ "</span>条&nbsp;&nbsp;&nbsp;&nbsp;");
//			displayInfo.append("第<span style='color:#FF0000'>" + index
//					+ "</span>页/共" + this.getTotlePage() + "页&nbsp;&nbsp;&nbsp;&nbsp;");
//			// 判断如果当前是第一页 则“首页”和“第一页”失去链接
//			if (index == 1)
//			{
//				displayInfo.append("首页&nbsp;&nbsp;&nbsp;&nbsp;");
//				displayInfo.append("上一页&nbsp;&nbsp;&nbsp;&nbsp;");
//			} 
//			else
//			{
//				displayInfo.append("<a style='color: red' href='" + path
//						+ "index=1'>首页&nbsp;&nbsp;&nbsp;&nbsp;</a>");
//				displayInfo.append("<a style='color: red' href='" + path + "index=" + (index - 1)
//						+ "'>上一页&nbsp;&nbsp;&nbsp;&nbsp;</a>&nbsp;");
//			}
//			if 
//			(index >= this.getTotlePage())
//			{
//				displayInfo.append("下一页&nbsp;&nbsp;&nbsp;&nbsp;");
//				displayInfo.append("末页&nbsp;&nbsp;&nbsp;&nbsp;");
//			}
//			else
//			{
//				displayInfo.append("<a style='color: red' href='" + path + "index=" + (index + 1)
//						+ "'>下一页&nbsp;&nbsp;&nbsp;&nbsp;</a>");
//				displayInfo.append("<a style='color: red' href='" + path + "index="
//						+ this.getTotlePage() + "'>末页</a>&nbsp;&nbsp;&nbsp;&nbsp;");
//			}
//			displayInfo.append("</div>");
//		}
//		return displayInfo.toString();
//	}
}

