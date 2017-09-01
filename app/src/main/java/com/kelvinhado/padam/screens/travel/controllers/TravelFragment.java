package com.kelvinhado.padam.screens.travel.controllers;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kelvinhado.padam.data.AddressesContract;
import com.kelvinhado.padam.screens.common.controllers.BaseFragment;
import com.kelvinhado.padam.screens.travel.mvcviews.TravelViewMvc;
import com.kelvinhado.padam.screens.travel.mvcviews.TravelViewMvcImpl;

/**
 * Created by kelvin on 01/09/2017.
 */

public class TravelFragment extends BaseFragment implements TravelViewMvc.TravelViewMvcListener,
        LoaderManager.LoaderCallbacks<Cursor> {

    Context mContext;
    TravelViewMvc mViewMvc;
    SimpleCursorAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mContext = getContext();
        initCursorLoader();
        mViewMvc = new TravelViewMvcImpl(inflater, container);
        mViewMvc.setListener(this);
        mViewMvc.bindAddressesData(mAdapter);
        return mViewMvc.getRootView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewMvc.initializeMap(savedInstanceState);
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

    // Fetching data from the content provider______________________________________________________

    private void initCursorLoader() {
        getLoaderManager().initLoader(0, null, this);
        mAdapter = new SimpleCursorAdapter(
                mContext,
                android.R.layout.simple_spinner_item,
                null,
                new String[]{AddressesContract.AddressesEntry.COLUMN_ADDRESS_NAME},
                new int[]{android.R.id.text1},
                0);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cursorLoader = new CursorLoader(
                mContext,
                AddressesContract.AddressesEntry.CONTENT_URI,
                null,
                null,
                null,
                AddressesContract.AddressesEntry._ID);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    // END Fetching data from the content provider_______________________________________________END
}