package com.rajebdev.kuymakan.buyer.food;

import com.google.gson.annotations.SerializedName;

public class FoodViewResponse{

	@SerializedName("data")
	private FoodData data;

	@SerializedName("response")
	private int response;

	public void setData(FoodData data){
		this.data = data;
	}

	public FoodData getData(){
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
			"FoodViewResponse{" + 
			"data = '" + data + '\'' + 
			",response = '" + response + '\'' + 
			"}";
		}
}