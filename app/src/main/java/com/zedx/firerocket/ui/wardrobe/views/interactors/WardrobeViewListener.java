package com.zedx.firerocket.ui.wardrobe.views.interactors;

import com.zedx.firerocket.di.components.AppComponent;
import com.zedx.firerocket.ui.base.views.interactors.BaseViewListener;

/**
 * Created by ajmac1005 on 28/08/16.
 */
public interface WardrobeViewListener extends BaseViewListener {
    void onSaveClothesSuccessful(int category, String path);

    void onFavFailure();

    void onFavSuccesful();

    void onSaveClothesFailure(Exception e);

    void onShuffleSuccessful(Integer shirtPosition, Integer pantPosition);

    AppComponent getAppComponent();
}
