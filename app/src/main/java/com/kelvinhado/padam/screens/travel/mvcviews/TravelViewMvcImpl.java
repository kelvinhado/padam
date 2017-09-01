package com.kelvinhado.padam.screens.travel.mvcviews;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.kelvinhado.padam.R;
import com.kelvinhado.padam.data.AddressesContract;
import com.kelvinhado.padam.data.models.Address;

/**
 * Created by kelvin on 01/09/2017.
 */

/**
 * An implementation of {@link TravelViewMvc} interface
 */
public class TravelViewMvcImpl implements TravelViewMvc, AdapterView.OnItemSelectedListener, OnMapReadyCallback {

    private View mRootView;
    private TravelViewMvcListener mListener;
    private SimpleCursorAdapter mAdapter;
    private Cursor mAddressesCursor;
    private Address mSelectedAddress;

    private Spinner mSpinnerAddresses;
    private Button mBtnValidate;
    private MapView mMapView;
    private GoogleMap mGoogleMap;


    public TravelViewMvcImpl(LayoutInflater inflater, ViewGroup container) {
        mRootView = inflater.inflate(R.layout.mvc_view_fragment_travel, container, false);
        initializeViews();

        mBtnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null && mSelectedAddress != null) {
                    mListener.onButtonValidatedClicked(mSelectedAddress);
                }
            }
        });

        mSpinnerAddresses.setOnItemSelectedListener(this);
    }

    private void initializeViews() {
        mSpinnerAddresses = (Spinner) mRootView.findViewById(R.id.sp_addresses);
        mBtnValidate = (Button) mRootView.findViewById(R.id.bt_validate);
    }

    @Override
    public void setupViewWithContext(Context context) {
        mAdapter = new SimpleCursorAdapter(
                context,
                android.R.layout.simple_spinner_item,
                null,
                new String[]{AddressesContract.AddressesEntry.COLUMN_ADDRESS_NAME},
                new int[]{android.R.id.text1},
                0);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mSpinnerAddresses.setAdapter(mAdapter);
    }

    @Override
    public void bindAddressesData(Cursor addressesCursor) {
        mAdapter.swapCursor(addressesCursor);
        mAddressesCursor = addressesCursor;
    }

    @Override
    public void showTravelDetails(Address from, Address to, float duration) {
        //TODO show travel details once calculated
    }

    @Override
    public void setListener(TravelViewMvcListener listener) {
        mListener = listener;
    }

    // Spinner item selection_______________________________________________________________________
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
        if (mAddressesCursor == null)
            return;
        mAddressesCursor.moveToPosition(pos);
        int idU = mAddressesCursor.getInt(mAddressesCursor.getColumnIndex(AddressesContract.AddressesEntry._ID));
        String name = mAddressesCursor.getString(mAddressesCursor.getColumnIndex(AddressesContract.AddressesEntry.COLUMN_ADDRESS_NAME));
        Float lat = mAddressesCursor.getFloat(mAddressesCursor.getColumnIndex(AddressesContract.AddressesEntry.COLUMN_ADDRESS_LATITUDE));
        Float lon = mAddressesCursor.getFloat(mAddressesCursor.getColumnIndex(AddressesContract.AddressesEntry.COLUMN_ADDRESS_LONGITUDE));
        mSelectedAddress = new Address(idU, name, lat, lon);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    // END spinner item selection________________________________________________________________END

    // Map Management_______________________________________________________________________________
    @Override
    public void initializeMap(Bundle savedInstanceState) {
        mMapView = (MapView) mRootView.findViewById(R.id.mv_google_mapview);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        mMapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mGoogleMap = googleMap;
        //custom settings
        this.mGoogleMap.getUiSettings().setMapToolbarEnabled(false);
        this.mGoogleMap.getUiSettings().setZoomControlsEnabled(false);
        this.mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
        this.mGoogleMap.setMaxZoomPreference(18);

        /* TODO add markers and stuffs */
    }

    @Override
    public void resumeMap() {
        mMapView.onResume();
    }

    @Override
    public void destroyMap() {
        mMapView.onDestroy();
    }

    @Override
    public void lowMemoryMap() {
        mMapView.onLowMemory();
    }
    // End Map Management________________________________________________________________________END

    @Override
    public View getRootView() {
        return mRootView;
    }

    @Override
    public Bundle getViewState() {
        return null;
    }
}
