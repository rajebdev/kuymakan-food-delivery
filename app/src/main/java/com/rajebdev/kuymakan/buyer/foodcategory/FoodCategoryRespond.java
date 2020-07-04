package com.rajebdev.kuymakan.buyer.foodcategory;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class FoodCategoryRespond{

	@SerializedName("data")
	private List<FoodCategoryData> data;

	@SerializedName("response")
	private int response;

	public void setData(List<FoodCategoryData> data){
		this.data = data;
	}

	public List<FoodCategoryData> getData(){
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
			"FoodCategoryRespond{" + 
			"data = '" + data + '\'' + 
			",response = '" + response + '\'' + 
			"}";
		}
}