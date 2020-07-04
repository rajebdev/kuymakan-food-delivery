package com.rajebdev.kuymakan.buyer.foodtype;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class FoodTypeRespond {

	@SerializedName("data")
	private List<FoodTypeData> data;

	@SerializedName("response")
	private int response;

	public void setData(List<FoodTypeData> data){
		this.data = data;
	}

	public List<FoodTypeData> getData(){
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
			"FoodTypeData{" + 
			"data = '" + data + '\'' + 
			",response = '" + response + '\'' + 
			"}";
		}
}