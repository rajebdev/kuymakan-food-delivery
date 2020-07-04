package com.rajebdev.kuymakan;

import com.rajebdev.kuymakan.auth.LoginBody;
import com.rajebdev.kuymakan.auth.LoginResponse;
import com.rajebdev.kuymakan.buyer.food.FoodResponse;
import com.rajebdev.kuymakan.buyer.food.FoodViewResponse;
import com.rajebdev.kuymakan.buyer.foodcategory.FoodCategoryRespond;
import com.rajebdev.kuymakan.buyer.foodtype.FoodTypeRespond;
import com.rajebdev.kuymakan.buyer.order.OrderResponse;
import com.rajebdev.kuymakan.buyer.order.OrderStatusResponse;
import com.rajebdev.kuymakan.buyer.restaurant.OrderBody;
import com.rajebdev.kuymakan.buyer.restaurant.OrderCheckoutResponse;
import com.rajebdev.kuymakan.buyer.restaurant.RestaurantResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("foodtypes/")
    Call<FoodTypeRespond> getListFoodType();

    @GET("foodtypes/")
    Call<FoodCategoryRespond> getListFoodCategory();

    @GET("foods/")
    Call<FoodResponse> getListFood();

    @GET("foods/view/{foodId}")
    Call<FoodViewResponse> getFood(@Path("foodId") int foodId);

    @GET("restaurants/view/{restId}")
    Call<RestaurantResponse> getRestaurant(@Path("restId") int restId);

    @GET("orders/list/{token}")
    Call<OrderResponse> getlistOrder(@Path("token") String token);

    @GET("orderstatus/view/{orderStatusId}")
    Call<OrderStatusResponse> getOrderStatus(@Path("orderStatusId") int orderStatusId);

    @POST("users/login/")
    Call<LoginResponse> postLogin(@Body LoginBody loginBody);

    @POST("orders/checkout/{token}")
    Call<OrderCheckoutResponse> checkoutOrder(@Body OrderBody orderBody, @Path("token") String token);

}
