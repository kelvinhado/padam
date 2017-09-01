package com.kelvinhado.padam.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by kelvin on 01/09/2017.
 */

public class AddressesContract {

    public static final String AUTHORITY = "com.kelvinhado.padam";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_ADDRESSES = "addresses";

    public static final class AddressesEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ADDRESSES).build();

        public static final String TABLE_NAME = "addresses";
        public static final String COLUMN_ADDRESS_NAME = "address";
        public static final String COLUMN_ADDRESS_LATITUDE = "latitude";
        public static final String COLUMN_ADDRESS_LONGITUDE = "longitude";
    }
}
