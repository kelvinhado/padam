package com.kelvinhado.padam.data;

import android.provider.BaseColumns;

/**
 * Created by kelvin on 01/09/2017.
 */

public class AddressesContract {
    public static final class AddressesEntry implements BaseColumns {
        public static final String TABLE_NAME = "addresses";
        public static final String COLUMN_ADDRESS_NAME = "address";
        public static final String COLUMN_ADDRESS_LATITUDE = "latitude";
        public static final String COLUMN_ADDRESS_LONGITUDE = "longitude";
    }
}
