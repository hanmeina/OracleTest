package com.RBR.model;

/**
 * 规则前件
 * @author hanmeina
 *
 */
public class Condition implements java.io.Serializable{
	
	
	private Integer id;  //主键
	private MetaData metaData;  //对应的元数据 多对一
	private Integer weight;  //权重
	private Rules rules;  //对应的规则，对应是那条规则的前件 多对一
	public Condition(Integer id, MetaData metaData, Integer weight, Rules rules) {
		super();
		this.id = id;
		this.metaData = metaData;
		this.weight = weight;
		this.rules = rules;
	}

	
	
	public Condition() {
		super();
		// TODO Auto-generated constructor stub
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
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Rules getRules() {
		return rules;
	}
	public void setRules(Rules rules) {
		this.rules = rules;
	}
	
	@Override
	public String toString() {
		return "(condition_id:" + id + " metaData:" + metaData + " weight:" + weight + " rules:" + rules.getId() + ")";
	}
	
	
}
