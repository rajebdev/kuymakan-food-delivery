package com.rajebdev.kuymakan.buyer.restaurant;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.rajebdev.kuymakan.buyer.food.FoodData;

public class RestaurantData {

	@SerializedName("names")
	private String names;

	@SerializedName("foods")
	private List<FoodData> foods;

	@SerializedName("user_id")
	private int userId;

	@SerializedName("closetime")
	private String closetime;

	@SerializedName("closed")
	private boolean closed;

	@SerializedName("location")
	private String location;

	@SerializedName("id")
	private int id;

	@SerializedName("opentime")
	private String opentime;

	public void setNames(String names){
		this.names = names;
	}

	public String getNames(){
		return names;
	}

	public void setFoods(List<FoodData> foods){
		this.foods = foods;
	}

	public List<FoodData> getFoods(){
		return foods;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setClosetime(String closetime){
		this.closetime = closetime;
	}

	public String getClosetime(){
		return closetime;
	}

	public void setClosed(boolean closed){
		this.closed = closed;
	}

	public boolean isClosed(){
		return closed;
	}

	public void setLocation(String location){
		this.location = location;
	}

	public String getLocation(){
		return location;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setOpentime(String opentime){
		this.opentime = opentime;
	}

	public String getOpentime(){
		return opentime;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"names = '" + names + '\'' + 
			",foods = '" + foods + '\'' + 
			",user_id = '" + userId + '\'' + 
			",closetime = '" + closetime + '\'' + 
			",closed = '" + closed + '\'' + 
			",location = '" + location + '\'' + 
			",id = '" + id + '\'' + 
			",opentime = '" + opentime + '\'' + 
			"}";
		}
}