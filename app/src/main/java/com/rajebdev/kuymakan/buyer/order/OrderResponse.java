package com.rajebdev.kuymakan.buyer.order;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class OrderResponse{

	@SerializedName("data")
	private List<OrderData> data;

	@SerializedName("response")
	private int response;

	public void setData(List<OrderData> data){
		this.data = data;
	}

	public List<OrderData> getData(){
		return data;
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
			"OrderResponse{" + 
			"data = '" + data + '\'' + 
			",response = '" + response + '\'' + 
			"}";
		}
}