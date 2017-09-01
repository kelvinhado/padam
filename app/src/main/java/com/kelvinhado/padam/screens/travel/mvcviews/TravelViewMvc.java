package com.kelvinhado.padam.screens.travel.mvcviews;

import android.database.Cursor;
import android.os.Bundle;

import com.google.android.gms.maps.model.PolylineOptions;
import com.kelvinhado.padam.data.models.Address;
import com.kelvinhado.padam.screens.common.mvcviews.ViewMvc;

/**
 * Created by kelvin on 01/09/2017.
 */

/**
 * This interface corresponds to the travel preview and selection
 */
public interface TravelViewMvc extends ViewMvc {

    /**
     * Populate spinner with stored addresses
     * @param addresses all stored addresses
     */
    void bindAddressesData(Cursor addresses);

    /**
     * Show details of a particular travel
     * @param from departure location
     * @param to arrival location
     * @param duration duration of the travel
     */
    void showTravelDetails(Address from, Address to, PolylineOptions polyline, float duration);

    /**
     * Set a listener that will be notified by this MVC view
     * @param listener listener that should be notified;
     */
    void setListener(TravelViewMvcListener listener);

    // Map Management_______________________________________________________________________________
    void initializeMap(Bundle savedInstanceState);

    void resumeMap();

    void destroyMap();

    void lowMemoryMap();
    // END Map Management________________________________________________________________________END

    interface TravelViewMvcListener {
        /**
         * This callback will be invoked when "VALIDATE" button is being clicked
         */
        void onButtonValidatedClicked(Address address);
    }
}
