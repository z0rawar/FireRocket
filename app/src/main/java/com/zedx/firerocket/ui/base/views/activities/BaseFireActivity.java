package com.zedx.firerocket.ui.base.views.activities;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.zedx.firerocket.FireApp;
import com.zedx.firerocket.di.components.AppComponent;

/**
 * Created by ajmac1005 on 28/08/16.
 */
public abstract class BaseFireActivity extends BaseActivity implements ActivityCompat.OnRequestPermissionsResultCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void showToast(String txt){
        Toast.makeText(this,txt,Toast.LENGTH_SHORT).show();
    }


    public AppComponent getAppComponent(){
        return ((FireApp)getApplicationContext()).getAppComponent();
    }


    protected boolean hasRequiredPermission(String permission) {
        return ContextCompat.checkSelfPermission(this,
                permission) == PackageManager.PERMISSION_GRANTED;

    }

    public boolean hasRequiredPermissions(String[] permissions) {
        int i = 0;
        while (i < permissions.length && hasRequiredPermission(permissions[i])) {
            i++;
        }
        return i == permissions.length;
    }

    public void requestForPermissions(String[] permissions,int requestCode) {
        ActivityCompat.requestPermissions(this, permissions,
                requestCode);

    }

    protected boolean isPermissionGrantedByUser(int[] grantResults) {
        return grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED;
    }

}
