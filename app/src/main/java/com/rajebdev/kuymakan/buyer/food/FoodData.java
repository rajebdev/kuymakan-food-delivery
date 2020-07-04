package com.rajebdev.kuymakan.buyer.food;

import com.google.gson.annotations.SerializedName;

public class FoodData {

	@SerializedName("names")
	private String names;

	@SerializedName("category_id")
	private int categoryId;

	@SerializedName("restaurant_id")
	private int restaurantId;

	@SerializedName("details")
	private String details;

	@SerializedName("id")
	private int id;

	@SerializedName("prices")
	private int prices;

	@SerializedName("stock")
	private int stock;

	@SerializedName("foodtype_id")
	private int foodtypeId;

	public void setNames(String names){
		this.names = names;
	}

	public String getNames(){
		return names;
	}

	public void setCategoryId(int categoryId){
		this.categoryId = categoryId;
	}

	public int getCategoryId(){
		return categoryId;
	}

	public void setRestaurantId(int restaurantId){
		this.restaurantId = restaurantId;
	}

	public int getRestaurantId(){
		return restaurantId;
	}

	public void setDetails(String details){
		this.details = details;
	}

	public String getDetails(){
		return details;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setPrices(int prices){
		this.prices = prices;
	}

	public int getPrices(){
		return prices;
	}

	public void setStock(int stock){
		this.stock = stock;
	}

	public int getStock(){
		return stock;
	}

	public void setFoodtypeId(int foodtypeId){
		this.foodtypeId = foodtypeId;
	}

	public int getFoodtypeId(){
		return foodtypeId;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"names = '" + names + '\'' + 
			",category_id = '" + categoryId + '\'' + 
			",restaurant_id = '" + restaurantId + '\'' + 
			",details = '" + details + '\'' + 
			",id = '" + id + '\'' + 
			",prices = '" + prices + '\'' + 
			",stock = '" + stock + '\'' + 
			",foodtype_id = '" + foodtypeId + '\'' + 
			"}";
		}
}