package com.zedx.firerocket.ui.wardrobe.di;

import com.zedx.firerocket.data.local.ClothesRepo;
import com.zedx.firerocket.data.local.RealmClothesRepo;
import com.zedx.firerocket.di.customScopes.PerActivity;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by ajmac1005 on 28/08/16.
 */

@Module
@PerActivity
public class ClothesRepoModule {
    private final Realm realm;

    public ClothesRepoModule(Realm realm) {
        this.realm = realm;
    }

    @Provides
    @PerActivity
    ClothesRepo provideClothesRepo() {
        return new RealmClothesRepo(realm);
    }

}
