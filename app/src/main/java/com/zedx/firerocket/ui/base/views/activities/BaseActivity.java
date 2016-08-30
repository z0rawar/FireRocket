package com.zedx.firerocket.ui.base.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zedx.firerocket.FireApp;

import javax.inject.Inject;
import javax.inject.Provider;

import io.realm.Realm;

/**
 * Created by ajmac1005 on 28/08/16.
 */
public abstract class BaseActivity extends AppCompatActivity{

    @Inject
    public Provider<Realm> realmProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
//        ButterKnife.bind(this);
        ((FireApp)getApplicationContext()).getAppComponent().inject(this);
    }

    protected abstract int getLayoutId();



}
