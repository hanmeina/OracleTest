package com.RBR.model;
/**
 * 规则后件
 * @author Administrator
 *
 */
public class Conclusion implements java.io.Serializable{
	
	private Integer id;  //主键
	private MetaData metaData;  //对应的元数据 多对一
	private Rules rules;  //对应的规则，对应是那条规则的前后件 多对一
	
	public Conclusion() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Conclusion(Integer id, MetaData metaData, Rules rules) {
		super();
		this.id = id;
		this.metaData = metaData;
		this.rules = rules;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public MetaData getMetaData() {
		return metaData;
	}
	public void setMetaData(MetaData metaData) {
		this.metaData = metaData;
	}
	public Rules getRules() {
		return rules;
	}
	public void setRules(Rules rules) {
		this.rules = rules;
	}
	@Override
	public String toString() {
		return "(conclusion_id:" + id + " metaData:" + metaData + ")";
	}
	
	
}
