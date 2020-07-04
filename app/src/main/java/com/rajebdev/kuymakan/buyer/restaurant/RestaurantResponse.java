package com.rajebdev.kuymakan.buyer.restaurant;

import com.google.gson.annotations.SerializedName;

public class RestaurantResponse{

	@SerializedName("data")
	private RestaurantData restaurantData;

	@SerializedName("response")
	private int response;

	public void setRestaurantData(RestaurantData restaurantData){
		this.restaurantData = restaurantData;
	}

	public RestaurantData getRestaurantData(){
		return restaurantData;
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
			"RestaurantResponse{" + 
			"data = '" + restaurantData + '\'' +
			",response = '" + response + '\'' + 
			"}";
		}
}