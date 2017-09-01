package com.kelvinhado.padam.screens.travel.controllers;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.PolylineOptions;
import com.kelvinhado.padam.data.AddressesContract;
import com.kelvinhado.padam.data.models.Address;
import com.kelvinhado.padam.network.direction.DownloadTask;
import com.kelvinhado.padam.screens.common.controllers.BaseFragment;
import com.kelvinhado.padam.screens.travel.mvcviews.TravelViewMvc;
import com.kelvinhado.padam.screens.travel.mvcviews.TravelViewMvcImpl;

import static com.kelvinhado.padam.network.direction.utils.DirectionUtils.getDirectionsUrl;

/**
 * Created by kelvin on 01/09/2017.
 */

public class TravelFragment extends BaseFragment implements TravelViewMvc.TravelViewMvcListener,
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final int ID_ADDRESSES_LOADER = 1993;

    Context mContext;
    TravelViewMvc mViewMvc;
    Address mDestinationAddress;
    Address mDepartureAddress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mContext = getContext();
        mViewMvc = new TravelViewMvcImpl(mContext, inflater, container);
        mViewMvc.setListener(this);
        getLoaderManager().initLoader(ID_ADDRESSES_LOADER, null, this);

        mDestinationAddress = new Address(0, "46 quai de la Rapée, 75012 Paris", 48.841978, 2.372773700000039);

        return mViewMvc.getRootView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewMvc.initializeMap(savedInstanceState);
    }

    @Override
    public void onButtonValidatedClicked(Address address) {
        mDepartureAddress = address;
        String url = getDirectionsUrl(mDepartureAddress.getLatLng(), mDestinationAddress.getLatLng());
        Log.d("TAG", url);
        DownloadTask downloadTask = new DownloadTask() {
            @Override
            protected void onPostExecute(PolylineOptions result) {
                super.onPostExecute(result);
                mViewMvc.showTravelDetails(mDepartureAddress, mDestinationAddress, result, 0);
            }
        };
        // Start downloading json data from Google Directions API
        downloadTask.execute(url);
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
        mViewMvc.bindAddressesData(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mViewMvc.bindAddressesData(null);
    }

    // END Fetching data from the content provider_______________________________________________END
}