package com.zedx.firerocket.ui.wardrobe.views.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zedx.firerocket.R;
import com.zedx.firerocket.ui.base.views.fragments.BaseFireFragment;
import com.zedx.firerocket.ui.wardrobe.constants.WardrobeConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ajmac1005 on 28/08/16.
 */
public class WardrobeFragment extends BaseFireFragment {

    @BindView(R.id.iv_clothes)
    ImageView ivClothes;

    public static WardrobeFragment newInstance(String path) {

        Bundle args = new Bundle();
        args.putString(WardrobeConstants.PATH, path);
        WardrobeFragment fragment = new WardrobeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wardrobe, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String path = getArguments().getString(WardrobeConstants.PATH, "http://i.imgur.com/mnp2NNj.jpg");
        Glide.with(this).load(path).fitCenter().into(ivClothes);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public String getPath() {
        return getArguments().getString(WardrobeConstants.PATH);
    }
}
