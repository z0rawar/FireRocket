package com.zedx.firerocket.ui.wardrobe.di;

import com.zedx.firerocket.di.components.AppComponent;
import com.zedx.firerocket.di.customScopes.PerActivity;
import com.zedx.firerocket.ui.wardrobe.presenters.WardrobePresenterImpl;

import dagger.Component;

/**
 * Created by ajmac1005 on 28/08/16.
 */
@PerActivity
@Component(dependencies = AppComponent.class,modules = ClothesRepoModule.class)
public interface WardrobeComponent {

    void inject(WardrobePresenterImpl wardrobePresenter);

}
