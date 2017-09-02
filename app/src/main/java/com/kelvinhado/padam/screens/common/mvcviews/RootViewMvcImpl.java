package com.kelvinhado.padam.screens.common.mvcviews;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kelvinhado.padam.R;

/**
 * Created by kelvin on 01/09/2017.
 */

/**
 * Very simple MVC view containing just single FrameLayout
 */
public class RootViewMvcImpl implements ViewMvc {

    private View mRootView;

    public RootViewMvcImpl(Context context, ViewGroup container) {
        mRootView = LayoutInflater.from(context).inflate(R.layout.mvc_view_activity_main, container);
    }

    @Override
    public View getRootView() {
        return mRootView;
    }

    @Override
    public Bundle getViewState() {
        // This MVC view has no state that could be retrieved
        return null;
    }
}
