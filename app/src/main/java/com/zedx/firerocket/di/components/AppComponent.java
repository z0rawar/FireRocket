package com.zedx.firerocket.di.components;

import android.content.Context;

import com.zedx.firerocket.di.modules.AppModule;
import com.zedx.firerocket.di.modules.DataModule;
import com.zedx.firerocket.ui.base.views.activities.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by ajmac1005 on 28/08/16.
 */

@Singleton
@Component(modules = {AppModule.class, DataModule.class})
public interface AppComponent {

    Context context();

    RealmConfiguration realmConfiguration();

    Realm realm();

    void inject(BaseActivity baseActivity);

}
