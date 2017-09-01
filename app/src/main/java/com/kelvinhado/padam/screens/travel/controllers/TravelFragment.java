package com.kelvinhado.padam.screens.travel.controllers;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.kelvinhado.padam.R;
import com.kelvinhado.padam.screens.common.controllers.BaseFragment;
import com.kelvinhado.padam.screens.travel.mvcviews.TravelViewMvc;
import com.kelvinhado.padam.screens.travel.mvcviews.TravelViewMvcImpl;

/**
 * Created by kelvin on 01/09/2017.
 */

public class TravelFragment extends BaseFragment implements TravelViewMvc.TravelViewMvcListener {

    Context mContext;
    TravelViewMvc mViewMvc;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mContext = getContext();
        mViewMvc = new TravelViewMvcImpl(inflater, container);
        mViewMvc.setListener(this);
        mViewMvc.bindAddressesData(getAddressesAdapter());
        return mViewMvc.getRootView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewMvc.initializeMap(savedInstanceState);
    }

    private ArrayAdapter<CharSequence> getAddressesAdapter() {
        //get from strings resources
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mContext,
                R.array.array_testaddresses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    @Override
    public void onButtonValidatedClicked(String selectedAddresses) {
        Toast.makeText(getContext(), selectedAddresses, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        mViewMvc.resumeMap();
        super.onResume();
    }

    @Override
    public void onLowMemory() {
        mViewMvc.lowMemoryMap();
        super.onLowMemory();
    }

    @Override
    public void onDestroy() {
        mViewMvc.destroyMap();
        super.onDestroy();
    }
}