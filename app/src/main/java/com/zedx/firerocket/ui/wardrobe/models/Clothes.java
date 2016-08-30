package com.zedx.firerocket.ui.wardrobe.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ajmac1005 on 28/08/16.
 */

public class Clothes extends RealmObject{

    @PrimaryKey
    String path;
    Integer category;

    public Clothes() {
    }

    public Clothes(String path, Integer category) {
        this.path = path;
        this.category = category;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }
}
