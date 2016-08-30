package com.zedx.firerocket.ui.base.views.viewDelegates;

import android.content.Context;

import com.zedx.firerocket.ui.base.views.interactors.BaseDelegateInteractor;

/**
 * Created by ajmac1005 on 28/08/16.
 */
public abstract class BaseFireViewDelegate extends BaseViewDelegate{
    protected final BaseDelegateInteractor interactor;
    protected final Context context;

    public BaseFireViewDelegate(BaseDelegateInteractor interactor, Context context) {
        super(interactor,context);
        this.interactor=interactor;
        this.context=context;

    }


    protected <T extends BaseDelegateInteractor>T getDelegateInteractor(Class<T> clazz) {
        if(interactor != null) {
            return (T)interactor;
        } else {
            throw new NullPointerException();
        }
    }

}
