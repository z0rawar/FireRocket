package com.zedx.firerocket.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ajmac1005 on 28/08/16.
 */

@Module
@Singleton
public class AppModule {

    private final Context context;

    public AppModule(Context context) {
        this.context=context;
    }

    @Provides
    @Singleton
//    @ApplicationContext
    Context provideApplicationContext(){
        return context;
    }


//    @Provides
//    @Singleton
//    RealmConfiguration provideRealmConfiguration(Context context){
//        return new RealmConfiguration.Builder(context).deleteRealmIfMigrationNeeded().build();
//    }
//
//    @Provides
//    @Singleton
//    Realm provideRealm(RealmConfiguration configuration){
//        return Realm.getInstance(configuration);
//    }
}
