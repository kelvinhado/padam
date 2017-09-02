package com.kelvinhado.padam.screens.travel.mvcviews;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
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

    private Context mContext;
    private View mRootView;
    private TravelViewMvcListener mListener;
    private SimpleCursorAdapter mAdapter;
    private Cursor mAddressesCursor;
    private Address mSelectedAddress;

    private Spinner mSpinnerAddresses;
    private Button mBtnValidate;
    private MapView mMapView;
    private GoogleMap mGoogleMap;


    public TravelViewMvcImpl(Context context, LayoutInflater inflater, ViewGroup container) {
        mContext = context;
        mRootView = inflater.inflate(R.layout.mvc_view_fragment_travel, container, false);
        initializeViews();

        mBtnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null && mSelectedAddress != null) {
                    mListener.onButtonValidatedClicked(mSelectedAddress);
                    animateMapCameraToSelectedAddress();
                }
            }
        });

        mSpinnerAddresses.setOnItemSelectedListener(this);
        mAdapter = new SimpleCursorAdapter(
                mContext,
                android.R.layout.simple_spinner_item,
                null,
                new String[]{AddressesContract.AddressesEntry.COLUMN_ADDRESS_NAME},
                new int[]{android.R.id.text1},
                0);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mSpinnerAddresses.setAdapter(mAdapter);
    }

    private void initializeViews() {
        mSpinnerAddresses = (Spinner) mRootView.findViewById(R.id.sp_departure_addresses);
        mBtnValidate = (Button) mRootView.findViewById(R.id.bt_validate);
    }

    @Override
    public void bindAddressesData(Cursor addressesCursor) {
        mAdapter.swapCursor(addressesCursor);
        mAddressesCursor = addressesCursor;
    }

    @Override
    public void showTravelDetails(Address from, Address to, PolylineOptions polylineOptions) {
        mGoogleMap.clear();
        mGoogleMap.addMarker(new MarkerOptions()
                .position(from.getLatLng())
                .title(from.getName()));
        mGoogleMap.addMarker(new MarkerOptions()
                .position(to.getLatLng())
                .title(to.getName()));
        mGoogleMap.addPolyline(polylineOptions);

        // move camera
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(from.getLatLng());
        builder.include(to.getLatLng());
        LatLngBounds bounds = builder.build();
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
    }

    @Override
    public void showTravelPopupDetalils(String duration, String distance) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(R.string.dialog_travel_title);
        builder.setMessage(mContext.getString(R.string.dialog_travel_information, duration, distance))
                .setNeutralButton(R.string.dialog_travel_button_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.create();
        builder.show();
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
        double lat = mAddressesCursor.getDouble(mAddressesCursor.getColumnIndex(AddressesContract.AddressesEntry.COLUMN_ADDRESS_LATITUDE));
        double lon = mAddressesCursor.getDouble(mAddressesCursor.getColumnIndex(AddressesContract.AddressesEntry.COLUMN_ADDRESS_LONGITUDE));
        mSelectedAddress = new Address(idU, name, lat, lon);
        animateMapCameraToSelectedAddress();
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
        mGoogleMap = googleMap;
        //custom settings
        mGoogleMap.getUiSettings().setMapToolbarEnabled(false);
        mGoogleMap.getUiSettings().setZoomControlsEnabled(false);
        mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
        mGoogleMap.setMaxZoomPreference(18);
        LatLng position = new LatLng(48.866667, 2.333333);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 10));
    }

    private void animateMapCameraToSelectedAddress() {
        mGoogleMap.clear();
        if (mSelectedAddress != null) {
            LatLng position = new LatLng(mSelectedAddress.getLatitude(), mSelectedAddress.getLongitude());
            Marker marker = mGoogleMap.addMarker(new MarkerOptions()
                    .position(position)
                    .title(mContext.getString(R.string.map_marker_title_departure))
                    .snippet(mSelectedAddress.getName()));
            marker.showInfoWindow();
            mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 12));
        }
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
