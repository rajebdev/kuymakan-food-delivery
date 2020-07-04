package com.rajebdev.kuymakan.buyer.food;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class FoodResponse{

	@SerializedName("data")
	private List<FoodData> data;

	@SerializedName("response")
	private int response;

	public void setData(List<FoodData> data){
		this.data = data;
	}

	public List<FoodData> getData(){
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
			"FoodResponse{" + 
			"data = '" + data + '\'' + 
			",response = '" + response + '\'' + 
			"}";
		}
}