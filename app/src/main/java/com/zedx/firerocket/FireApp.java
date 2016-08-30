package com.zedx.firerocket;

import android.app.Application;

import com.zedx.firerocket.di.components.AppComponent;
import com.zedx.firerocket.di.components.DaggerAppComponent;
import com.zedx.firerocket.di.modules.AppModule;
import com.zedx.firerocket.di.modules.DataModule;

/**
 * Created by ajmac1005 on 28/08/16.
 */

public class FireApp extends Application {

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).dataModule(new DataModule()).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
