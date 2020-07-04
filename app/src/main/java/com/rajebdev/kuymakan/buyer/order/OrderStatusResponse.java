package com.rajebdev.kuymakan.buyer.order;

import com.google.gson.annotations.SerializedName;

public class OrderStatusResponse{

	@SerializedName("data")
	private OrderStatusData orderStatusData;

	@SerializedName("response")
	private int response;

	public void setOrderStatusData(OrderStatusData orderStatusData){
		this.orderStatusData = orderStatusData;
	}

	public OrderStatusData getOrderStatusData(){
		return orderStatusData;
	}

	public void setResponse(int response){
		this.response = response;
	}

	public int getResponse(){
		return response;
	}

	@Override
 	public String toString(){
		return 
			"OrderStatusResponse{" + 
			"data = '" + orderStatusData + '\'' +
			",response = '" + response + '\'' + 
			"}";
		}
}