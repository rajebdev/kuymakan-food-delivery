package com.rajebdev.kuymakan.buyer.foodcategory;

import com.google.gson.annotations.SerializedName;

public class FoodCategoryData {

	@SerializedName("images")
	private String images;

	@SerializedName("id")
	private int id;

	@SerializedName("label")
	private String label;

	public void setImages(String images){
		this.images = images;
	}

	public String getImages(){
		return images;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setLabel(String label){
		this.label = label;
	}

	public String getLabel(){
		return label;
	}

	@Override
 	public String toString(){
		return 
			"FoodCategoryData{" +
			"images = '" + images + '\'' + 
			",id = '" + id + '\'' + 
			",label = '" + label + '\'' + 
			"}";
		}
}