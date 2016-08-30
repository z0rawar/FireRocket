package com.zedx.firerocket.ui.wardrobe.views.interactors;

import com.zedx.firerocket.ui.base.views.interactors.BaseDelegateInteractor;

/**
 * Created by ajmac1005 on 28/08/16.
 */
public interface WardrobeDelegateInteractor extends BaseDelegateInteractor {

    void onShuffleClick(int maxShirts, int maxPants);

    void onAddFavClick(int shirtPosition, int pantPosition);

    void onAddClothes(int category);
}
