package com.rajebdev.kuymakan.buyer.restaurant;

import com.google.gson.annotations.SerializedName;

public class OrderCheckoutResponse{

	@SerializedName("response")
	private int response;

	@SerializedName("status")
	private int status;

	public void setResponse(int response){
		this.response = response;
	}

	public int getResponse(){
		return response;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"OrderCheckoutResponse{" + 
			"response = '" + response + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}