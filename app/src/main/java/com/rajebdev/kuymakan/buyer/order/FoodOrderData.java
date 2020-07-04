package com.rajebdev.kuymakan.buyer.order;

import com.google.gson.annotations.SerializedName;

public class FoodOrderData {

	@SerializedName("amount")
	private int amount;

	@SerializedName("id")
	private int id;

	@SerializedName("food_id")
	private int foodId;

	@SerializedName("order_id")
	private int orderId;

	public void setAmount(int amount){
		this.amount = amount;
	}

	public int getAmount(){
		return amount;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setFoodId(int foodId){
		this.foodId = foodId;
	}

	public int getFoodId(){
		return foodId;
	}

	public void setOrderId(int orderId){
		this.orderId = orderId;
	}

	public int getOrderId(){
		return orderId;
	}

	@Override
 	public String toString(){
		return 
			"FoodordersItem{" + 
			"amount = '" + amount + '\'' + 
			",id = '" + id + '\'' + 
			",food_id = '" + foodId + '\'' + 
			",order_id = '" + orderId + '\'' + 
			"}";
		}
}