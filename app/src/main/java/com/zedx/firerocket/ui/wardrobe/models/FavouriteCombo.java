package com.zedx.firerocket.ui.wardrobe.models;

import io.realm.RealmObject;

/**
 * Created by ajmac1005 on 28/08/16.
 */
public class FavouriteCombo extends RealmObject {

    Integer shirtPosition;
    Integer pantPosition;
    long timestamp;


    public FavouriteCombo() {
    }

    public FavouriteCombo(Integer shirtPosition, Integer pantPosition) {
        this.shirtPosition = shirtPosition;
        this.pantPosition = pantPosition;
    }

    public Integer getShirtPosition() {
        return shirtPosition;
    }

    public void setShirtPosition(Integer shirtPosition) {
        this.shirtPosition = shirtPosition;
    }

    public Integer getPantPosition() {
        return pantPosition;
    }

    public void setPantPosition(Integer pantPosition) {
        this.pantPosition = pantPosition;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
