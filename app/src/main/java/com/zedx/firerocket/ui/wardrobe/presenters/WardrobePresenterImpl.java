package com.zedx.firerocket.ui.wardrobe.presenters;

import com.zedx.firerocket.data.local.ClothesRepo;
import com.zedx.firerocket.di.customScopes.PerActivity;
import com.zedx.firerocket.ui.base.presenters.BaseFirePresenter;
import com.zedx.firerocket.ui.wardrobe.di.ClothesRepoModule;
import com.zedx.firerocket.ui.wardrobe.di.DaggerWardrobeComponent;
import com.zedx.firerocket.ui.wardrobe.di.WardrobeComponent;
import com.zedx.firerocket.ui.wardrobe.models.Clothes;
import com.zedx.firerocket.ui.wardrobe.models.FavouriteCombo;
import com.zedx.firerocket.ui.wardrobe.views.interactors.WardrobeViewListener;

import java.util.List;

import javax.inject.Inject;

import io.realm.exceptions.RealmPrimaryKeyConstraintException;

/**
 * Created by ajmac1005 on 28/08/16.
 */
@PerActivity
public class WardrobePresenterImpl extends BaseFirePresenter implements WardrobePresenter {

    private final WardrobeViewListener viewListener;

    @Inject
    ClothesRepo clothesRepo;

    private WardrobeComponent wardrobeComponent;

    public WardrobePresenterImpl(WardrobeViewListener viewListener) {
        this.viewListener = viewListener;
    }

    @Override
    public void initDaggerInjection() {
        wardrobeComponent = DaggerWardrobeComponent.builder().appComponent(viewListener.getAppComponent()).clothesRepoModule(new ClothesRepoModule(viewListener.getAppComponent().realm())).build();
        wardrobeComponent.inject(this);
    }


    public void saveClothes(String path, int category) {
        try {
            clothesRepo.insertClothes(category, path);
        } catch (RealmPrimaryKeyConstraintException e) {
            viewListener.onSaveClothesFailure(e);
            return;
        }
        viewListener.onSaveClothesSuccessful(category, path);
    }

    @Override
    public List<Clothes> findAllPants() {
        return clothesRepo.findAllPants();
    }

    @Override
    public List<Clothes> findAllShirts() {
        return clothesRepo.findAllShirts();
    }

    @Override
    public void saveFav(int shirtPosition, int pantPosition) {
        try {
            clothesRepo.insertFavourite(shirtPosition, pantPosition);
        } catch (Exception e) {
            viewListener.onFavFailure();
            return;
        }
        viewListener.onFavSuccesful();
    }

    @Override
    public void doShufflin(int maxShirts, int maxPants, boolean forceRandom) {

        List<FavouriteCombo> favouriteCombos = clothesRepo.findAllSortedFavourites();
        if (!forceRandom && !favouriteCombos.isEmpty() && !isShownEarlierToday(favouriteCombos.get(0).getTimestamp())) {
            viewListener.onShuffleSuccessful(favouriteCombos.get(0).getShirtPosition(), favouriteCombos.get(0).getPantPosition());
            updateFavouriteTimestampToNow(favouriteCombos.get(0));
        } else {
            viewListener.onShuffleSuccessful((int) (Math.random() * maxShirts), (int) (Math.random() * maxPants));
        }
    }


    private void updateFavouriteTimestampToNow(FavouriteCombo favouriteCombo) {
        clothesRepo.updateFavouriteTime(favouriteCombo);
    }

    private boolean isShownEarlierToday(long timestamp) {
        return (System.currentTimeMillis() / 1000 - timestamp) <= 86400;
    }

}