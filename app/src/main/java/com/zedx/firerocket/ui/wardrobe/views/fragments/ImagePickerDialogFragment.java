package com.zedx.firerocket.ui.wardrobe.views.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.zedx.firerocket.R;
import com.zedx.firerocket.ui.base.BaseFireDialogFragment;
import com.zedx.firerocket.ui.wardrobe.views.interactors.ImagePickerDialogFragmentInteractor;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImagePickerDialogFragment extends BaseFireDialogFragment {

    private ImagePickerDialogFragmentInteractor fragmentInteractor;


    @OnClick(R.id.rl_camera)
    public void onCameraClick() {
        fragmentInteractor.startCameraIntent();
        dismiss();
    }

    @OnClick(R.id.rl_gallery)
    public void onGalleryClick() {
        fragmentInteractor.startGalleryIntent();
        dismiss();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        fragmentInteractor = (ImagePickerDialogFragmentInteractor) getActivity();
    }

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow()
                .getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        WindowManager.LayoutParams wmlp = getDialog().getWindow().getAttributes();
        wmlp.gravity = Gravity.FILL_HORIZONTAL | Gravity.BOTTOM;
        ButterKnife.bind(this,view);
        return view;
    }



    @Override
    public int getFragmentViewId() {
        return R.layout.fragment_dialog_image_picker;
    }


}
