package com.RBR.model;

public class Message implements java.io.Serializable{
	
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Message(Integer a, double r) {
		super();
		this.a = a;
		this.r = r;
	}

	
	public Message(Integer number) {
		a = number;
	}
	private Integer a;

	public Integer isA() {
		return a;
	}
	
	private double r = 1.0;
	
	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}
}
