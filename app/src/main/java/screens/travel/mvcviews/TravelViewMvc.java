package screens.travel.mvcviews;

import android.widget.ArrayAdapter;

import com.kelvinhado.padam.common.screens.mvcviews.ViewMvc;

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
    void bindAddressesData(ArrayAdapter<CharSequence> addresses);

    /**
     * Show details of a particular travel
     * @param from departure location
     * @param to arrival location
     * @param duration duration of the travel
     */
    void showTravelDetails(Object from, Object to, float duration);

    /**
     * Set a listener that will be notified by this MVC view
     * @param listener listener that should be notified; null to clear
     */
    void setListener(TravelViewMvcListener listener);

    interface TravelViewMvcListener {
        /**
         * This callback will be invoked when "VALIDATE" button is being clicked
         */
        void onButtonValidatedClicked(String selectedAddresses);
    }
}
