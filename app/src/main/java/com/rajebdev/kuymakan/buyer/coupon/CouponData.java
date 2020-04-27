package com.rajebdev.kuymakan.buyer.coupon;

public class CouponData {
    private int id;
    private String image;
    private String name;
    private String expired;

    public CouponData(int id, String image, String name, String expired) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.expired = expired;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
