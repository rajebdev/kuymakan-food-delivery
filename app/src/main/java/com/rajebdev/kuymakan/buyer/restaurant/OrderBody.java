package com.rajebdev.kuymakan.buyer.restaurant;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.rajebdev.kuymakan.buyer.food.FoodData;

public class OrderBody{

	@SerializedName("foodorders")
	private List<FoodData> foodorders;

	@SerializedName("restaurant_id")
	private int restaurantId;

	@SerializedName("location_send")
	private String locationSend;

	public void setFoodorders(List<FoodData> foodorders){
		this.foodorders = foodorders;
	}

	public List<FoodData> getFoodorders(){
		return foodorders;
	}

	public void setRestaurantId(int restaurantId){
		this.restaurantId = restaurantId;
	}

	public int getRestaurantId(){
		return restaurantId;
	}

	public void setLocationSend(String locationSend){
		this.locationSend = locationSend;
	}

	public String getLocationSend(){
		return locationSend;
	}

	@Override
 	public String toString(){
		return 
			"OrderBody{" + 
			"foodorders = '" + foodorders + '\'' +
			",restaurant_id = '" + restaurantId + '\'' + 
			",location_send = '" + locationSend + '\'' + 
			"}";
		}
}