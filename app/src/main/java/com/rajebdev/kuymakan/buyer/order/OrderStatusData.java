package com.rajebdev.kuymakan.buyer.order;

import com.google.gson.annotations.SerializedName;

public class OrderStatusData {

	@SerializedName("id")
	private int id;

	@SerializedName("label")
	private String label;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setLabel(String label){
		this.label = label;
	}

	public String getLabel(){
		return label;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"id = '" + id + '\'' + 
			",label = '" + label + '\'' + 
			"}";
		}
}