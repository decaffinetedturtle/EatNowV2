package com.mad.eatnowv2.itemLists;

public class foodGetter {

    public foodGetter(){
        super();
    }

    public foodGetter(String foodName, String foodDesc, String expiryDate) {
        this.foodName = foodName;
        this.foodDesc = foodDesc;
        this.expiryDate = expiryDate;
    }

    String foodName, foodDesc, expiryDate;

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
