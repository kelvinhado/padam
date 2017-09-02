package com.kelvinhado.padam.screens.credits.mvcviews;

import android.net.Uri;

import com.kelvinhado.padam.screens.common.mvcviews.ViewMvc;

/**
 * Created by kelvin on 02/09/2017.
 */

/**
 * View to show the credits of the app
 */
public interface CreditsViewMvc extends ViewMvc{

    /**
     * Set a listener that will be notified by this MVC view
     * @param listener listener that should be notified;
     */
    void setListener(CreditsViewMvcListener listener);

    interface CreditsViewMvcListener {
        /**
         * This callback will be invoked when "VALIDATE" button is being clicked
         */
        void onExternalPageSelected(Uri uri);
    }
}
