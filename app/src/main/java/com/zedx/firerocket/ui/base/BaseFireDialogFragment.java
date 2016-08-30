package com.zedx.firerocket.ui.base;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.zedx.firerocket.R;

/**
 * Created by ajmac1005 on 28/08/16.
 */
public abstract class BaseFireDialogFragment extends DialogFragment {

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = getResources().getDimensionPixelSize(R.dimen.popup_width);
            dialog.getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = getView(inflater, container);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams wmlp = getDialog().getWindow().getAttributes();
        wmlp.gravity = Gravity.CENTER_HORIZONTAL;
        return view;
    }

    public View getView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(getFragmentViewId(), container, false);
    }

    public abstract int getFragmentViewId();

}
