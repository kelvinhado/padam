package com.kelvinhado.padam.screens.credits.controllers;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kelvinhado.padam.screens.common.controllers.BaseFragment;
import com.kelvinhado.padam.screens.credits.mvcviews.CreditsViewMvc;
import com.kelvinhado.padam.screens.credits.mvcviews.CreditsViewMvcImpl;

/**
 * Created by kelvin on 02/09/2017.
 */

public class CreditsFragment extends BaseFragment implements CreditsViewMvc.CreditsViewMvcListener {

    Context mContext;
    CreditsViewMvc mViewMvc;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mContext = getContext();
        mViewMvc = new CreditsViewMvcImpl(inflater, container);
        mViewMvc.setListener(this);
        return mViewMvc.getRootView();
    }

    @Override
    public void onExternalPageSelected(Uri uri) {

    }
}
