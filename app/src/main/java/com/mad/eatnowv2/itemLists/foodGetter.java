package com.mad.eatnowv2.itemLists;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class foodGetter implements Serializable {

    @Exclude private String foodId;

    String foodName, foodDesc, expiryDate;

    public foodGetter(){
        super();
    }

    public foodGetter(String foodName, String foodDesc, String expiryDate) {
        this.foodName = foodName;
        this.foodDesc = foodDesc;
        this.expiryDate = expiryDate;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodDesc() {
        return foodDesc;
    }

    public String getExpiryDate() {
        return expiryDate;
    }
}
