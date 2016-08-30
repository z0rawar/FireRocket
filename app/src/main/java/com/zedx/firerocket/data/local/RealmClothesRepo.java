package com.zedx.firerocket.data.local;

import com.zedx.firerocket.ui.wardrobe.constants.WardrobeConstants;
import com.zedx.firerocket.ui.wardrobe.models.Clothes;
import com.zedx.firerocket.ui.wardrobe.models.FavouriteCombo;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.Sort;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;

/**
 * Created by ajmac1005 on 28/08/16.
 */

public class RealmClothesRepo implements ClothesRepo {


    private final Realm realm;

    @Inject
    public RealmClothesRepo(Realm realm) {
        this.realm = realm;
    }

    @Override
    public List<Clothes> findAllShirts() {
//        Realm realm = realmProvider.get();
        return realm.where(Clothes.class).equalTo(WardrobeConstants.CATEGORY, WardrobeConstants.SHIRT).findAll();
    }

    @Override
    public List<Clothes> findAllPants() {
//        Realm realm = realmProvider.get();
        return realm.where(Clothes.class).equalTo(WardrobeConstants.CATEGORY, WardrobeConstants.PANT).findAll();
    }

    @Override
    public List<FavouriteCombo> findAllSortedFavourites() {
//        Realm realm = realmProvider.get();
        return realm.where(FavouriteCombo.class).findAllSorted(WardrobeConstants.TIMESTAMP, Sort.ASCENDING);
    }

    @Override
    public void insertFavourite(int shirtPos, int pantPos) {
//        Realm realm = realmProvider.get();
        checkRealm();
        realm.beginTransaction();
        realm.copyToRealm(new FavouriteCombo(shirtPos, pantPos));
        realm.commitTransaction();
    }

    @Override
    public void insertClothes(Integer type, String path) throws RealmPrimaryKeyConstraintException {
//        Realm realm = realmProvider.get();
        checkRealm();
        realm.beginTransaction();
        realm.copyToRealm(new Clothes(path, type));
        realm.commitTransaction();
    }

    @Override
    public void updateFavouriteTime(FavouriteCombo favouriteCombo) {
//        Realm realm = realmProvider.get();
        checkRealm();
        realm.beginTransaction();
        favouriteCombo.setTimestamp(System.currentTimeMillis() / 1000);
        realm.commitTransaction();
    }

    private void checkRealm() {
        if(realm.isInTransaction()){
            realm.cancelTransaction();
        }
    }


}
