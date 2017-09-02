package com.kelvinhado.padam.data.utils;

import android.content.ContentValues;

import com.google.android.gms.maps.model.LatLng;
import com.kelvinhado.padam.data.AddressesContract;
import com.kelvinhado.padam.data.models.Address;

/**
 * Created by kelvin on 02/09/2017.
 */

public class AddressUtils {

    public static LatLng toLatLng(Address address) {
        return new LatLng(address.getLatitude(), address.getLongitude());
    }

    public static ContentValues toContentValues(Address address) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AddressesContract.AddressesEntry.COLUMN_ADDRESS_NAME, address.getName());
        contentValues.put(AddressesContract.AddressesEntry.COLUMN_ADDRESS_LATITUDE, address.getLatitude());
        contentValues.put(AddressesContract.AddressesEntry.COLUMN_ADDRESS_LONGITUDE, address.getLongitude());
        return contentValues;
    }
}
