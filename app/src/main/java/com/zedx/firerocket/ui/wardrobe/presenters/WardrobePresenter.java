package com.zedx.firerocket.ui.wardrobe.presenters;

import com.zedx.firerocket.ui.wardrobe.models.Clothes;

import java.util.List;

/**
 * Created by ajmac1005 on 28/08/16.
 */
public interface WardrobePresenter {
    void saveClothes(String path, int shirt);

    List<Clothes> findAllShirts();

    List<Clothes> findAllPants();

    void saveFav(int shirtPosition, int pantPosition);

    void doShufflin(int maxShirts, int maxPants, boolean forceRandom);


    void initDaggerInjection();


}
