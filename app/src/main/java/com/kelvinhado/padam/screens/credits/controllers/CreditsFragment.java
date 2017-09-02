package com.kelvinhado.padam.screens.credits.controllers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
        mViewMvc = new CreditsViewMvcImpl(mContext, inflater, container);
        mViewMvc.setListener(this);
        return mViewMvc.getRootView();
    }

    @Override
    public void onExternalPageSelected(Uri uri) {
        openWebPage(uri);
    }

    private void openWebPage(Uri webpage) {
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(mContext.getPackageManager()) != null) {
            startActivity(intent);
            Toast.makeText(mContext, "opening :" + webpage.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
