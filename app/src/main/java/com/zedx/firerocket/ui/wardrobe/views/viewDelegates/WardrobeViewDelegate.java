package com.zedx.firerocket.ui.wardrobe.views.viewDelegates;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.zedx.firerocket.R;
import com.zedx.firerocket.ui.base.views.activities.BaseActivity;
import com.zedx.firerocket.ui.base.views.viewDelegates.BaseFireViewDelegate;
import com.zedx.firerocket.ui.wardrobe.constants.WardrobeConstants;
import com.zedx.firerocket.ui.wardrobe.models.Clothes;
import com.zedx.firerocket.ui.wardrobe.views.activities.WardrobeActivity;
import com.zedx.firerocket.ui.wardrobe.views.adapters.ClothesPagerAdapter;
import com.zedx.firerocket.ui.wardrobe.views.fragments.WardrobeFragment;
import com.zedx.firerocket.ui.wardrobe.views.interactors.WardrobeDelegateInteractor;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ajmac1005 on 28/08/16.
 */
public class WardrobeViewDelegate extends BaseFireViewDelegate {

    @BindView(R.id.pager_shirts)
    ViewPager shirtsPager;
    @BindView(R.id.pager_pants)
    ViewPager pantsPager;

    private List<Fragment> shirtsFragmentList, pantsFragmentList;
    private ClothesPagerAdapter shirtsAdapter, pantsAdapter;


    public WardrobeViewDelegate(WardrobeDelegateInteractor interactor, WardrobeActivity activity) {
        super(interactor, activity);
        ButterKnife.bind(this, activity);
    }


    public void onViewInit(List<Clothes> shirts, List<Clothes> pants) {


//        initFragments(shirtsFragmentList,shirts);

        shirtsFragmentList = new ArrayList<>();
        for (Clothes shirt : shirts) {
            shirtsFragmentList.add(WardrobeFragment.newInstance(shirt.getPath()));
        }

        pantsFragmentList = new ArrayList<>();
        for (Clothes pant : pants) {
            pantsFragmentList.add(WardrobeFragment.newInstance(pant.getPath()));
        }

        shirtsAdapter = new ClothesPagerAdapter(((BaseActivity) context).getSupportFragmentManager(), shirtsFragmentList);
        shirtsPager.setAdapter(shirtsAdapter);

        pantsAdapter = new ClothesPagerAdapter(((BaseActivity) context).getSupportFragmentManager(), pantsFragmentList);
        pantsPager.setAdapter(pantsAdapter);

    }

    private void initFragments(List<Fragment> fragmentList, List<Clothes> dataset) {
        fragmentList = new ArrayList<>();
        for (Clothes clothes : dataset) {
            fragmentList.add(WardrobeFragment.newInstance(clothes.getPath()));
        }
    }

    @OnClick(R.id.btn_add_shirt)
    void onAddShirtClick() {
        getDelegateInteractor(WardrobeDelegateInteractor.class).onAddClothes(WardrobeConstants.SHIRT);
    }

    @OnClick(R.id.btn_add_pant)
    void onAddPantClick() {
        getDelegateInteractor(WardrobeDelegateInteractor.class).onAddClothes(WardrobeConstants.PANT);
    }

    @OnClick(R.id.btn_add_favourite)
    void onAddFavClick() {
        getDelegateInteractor(WardrobeDelegateInteractor.class).onAddFavClick(shirtsPager.getCurrentItem(), pantsPager.getCurrentItem());
    }

    @OnClick(R.id.btn_shuffle)
    void onShuffleClick() {
        getDelegateInteractor(WardrobeDelegateInteractor.class).onShuffleClick(shirtsFragmentList.size(), pantsFragmentList.size());
    }


    public void updateViewPager(int category, String path) {
        switch (category) {
            case WardrobeConstants.SHIRT:
                updateShirtViewPager(path);
                break;
            case WardrobeConstants.PANT:
                updatePantViewPager(path);
                break;
        }

    }

    private void updatePantViewPager(String path) {
        pantsFragmentList.add(WardrobeFragment.newInstance(path));
        pantsAdapter.notifyDataSetChanged();
    }

    private void updateShirtViewPager(String path) {
        shirtsFragmentList.add(WardrobeFragment.newInstance(path));
        shirtsAdapter.notifyDataSetChanged();
    }

    public void setFragmentToViewPager(Integer shirtPosition, Integer pantPosition) {
        shirtsPager.setCurrentItem(shirtPosition, true);
        pantsPager.setCurrentItem(pantPosition, true);
    }

    public int getShirtsSize() {
        return shirtsFragmentList.size();
    }

    public int getPantsSize() {
        return pantsFragmentList.size();
    }
}
