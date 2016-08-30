package com.zedx.firerocket.data.local;

import com.zedx.firerocket.ui.wardrobe.models.Clothes;
import com.zedx.firerocket.ui.wardrobe.models.FavouriteCombo;

import java.util.List;

/**
 * Created by ajmac1005 on 28/08/16.
 */

public interface ClothesRepo {

    List<Clothes> findAllShirts();

    List<Clothes> findAllPants();

    List<FavouriteCombo> findAllSortedFavourites();

    void insertFavourite(int shirtPos, int pantPos);

    void insertClothes(Integer type, String path);

    void updateFavouriteTime(FavouriteCombo favouriteCombo);

//    RealmResults<Clothes> findAllShirtsWithListener();


}
