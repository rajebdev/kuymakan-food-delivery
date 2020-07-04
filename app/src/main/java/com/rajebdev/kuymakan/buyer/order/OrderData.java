package com.rajebdev.kuymakan.buyer.order;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class OrderData {

	@SerializedName("code")
	private String code;

	@SerializedName("foodorders")
	private List<FoodOrderData> foodorders;

	@SerializedName("user_id")
	private int userId;

	@SerializedName("created")
	private String created;

	@SerializedName("restaurant_id")
	private int restaurantId;

	@SerializedName("orderstatus_id")
	private int orderstatusId;

	@SerializedName("id")
	private int id;

	@SerializedName("location_send")
	private String locationSend;

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setFoodorders(List<FoodOrderData> foodorders){
		this.foodorders = foodorders;
	}

	public List<FoodOrderData> getFoodorders(){
		return foodorders;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setCreated(String created){
		this.created = created;
	}

	public String getCreated(){
		return created;
	}

	public void setRestaurantId(int restaurantId){
		this.restaurantId = restaurantId;
	}

	public int getRestaurantId(){
		return restaurantId;
	}

	public void setOrderstatusId(int orderstatusId){
		this.orderstatusId = orderstatusId;
	}

	public int getOrderstatusId(){
		return orderstatusId;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
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
			"DataItem{" + 
			"code = '" + code + '\'' + 
			",foodorders = '" + foodorders + '\'' + 
			",user_id = '" + userId + '\'' + 
			",created = '" + created + '\'' + 
			",restaurant_id = '" + restaurantId + '\'' + 
			",orderstatus_id = '" + orderstatusId + '\'' + 
			",id = '" + id + '\'' + 
			",location_send = '" + locationSend + '\'' + 
			"}";
		}
}