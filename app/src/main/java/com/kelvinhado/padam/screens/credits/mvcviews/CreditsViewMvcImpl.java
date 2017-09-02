package com.kelvinhado.padam.screens.credits.mvcviews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kelvinhado.padam.R;

/**
 * Created by kelvin on 02/09/2017.
 */

public class CreditsViewMvcImpl implements CreditsViewMvc {
    private View mRootView;
    private CreditsViewMvcListener mListener;

    public CreditsViewMvcImpl(LayoutInflater inflater, ViewGroup container) {
        mRootView = inflater.inflate(R.layout.mvc_view_fragment_credits, container, false);
        initializeViews();
    }

    private void initializeViews() {

    }

    @Override
    public void setListener(CreditsViewMvcListener listener) {
        mListener = listener;
    }

    @Override
    public View getRootView() {
        return mRootView;
    }

    @Override
    public Bundle getViewState() {
        return null;
    }
}
