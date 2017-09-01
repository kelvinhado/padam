package com.kelvinhado.padam.screens.common.controllers;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.kelvinhado.padam.R;
import com.kelvinhado.padam.data.AddressesContract;
import com.kelvinhado.padam.screens.common.mvcviews.RootViewMvcImpl;
import com.kelvinhado.padam.screens.travel.controllers.TravelFragment;

public class MainActivity extends AppCompatActivity
        implements BaseFragment.AbstractFragmentCallback {

    RootViewMvcImpl mRootViewMvc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initFakeDatabase();
        // Instantiate MVC view associated with this activity
        mRootViewMvc = new RootViewMvcImpl(this, null);

        // Set the root view of the associated MVC view as the content of this activity
        setContentView(mRootViewMvc.getRootView());

        // Show the default fragment if the application is not restored
        if (savedInstanceState == null) {
            replaceFragment(TravelFragment.class, false, null);
        }
    }

    private void initFakeDatabase() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AddressesContract.AddressesEntry.COLUMN_ADDRESS_NAME, "27 Rue Oudinot, 75008 Paris, France");
        contentValues.put(AddressesContract.AddressesEntry.COLUMN_ADDRESS_LATITUDE, 48.849355);
        contentValues.put(AddressesContract.AddressesEntry.COLUMN_ADDRESS_LONGITUDE, 2.316055);

        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(AddressesContract.AddressesEntry.COLUMN_ADDRESS_NAME,
                "9 Avenue du Plateau, 94100 Saint-Maur-des-Fossés, France");
        contentValues2.put(AddressesContract.AddressesEntry.COLUMN_ADDRESS_LATITUDE, 48.808672);
        contentValues2.put(AddressesContract.AddressesEntry.COLUMN_ADDRESS_LONGITUDE, 2.500076);
        Uri uri = getContentResolver().insert(AddressesContract.AddressesEntry.CONTENT_URI, contentValues);
        Uri uri2 = getContentResolver().insert(AddressesContract.AddressesEntry.CONTENT_URI, contentValues2);
    }

    // FRAGMENT MANAGEMENT
    @Override
    public void replaceFragment(Class<? extends Fragment> claz, boolean addToBackStack, Bundle args) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();


        Fragment newFragment;

        try {
            // Create new fragment
            newFragment = claz.newInstance();
            if (args != null) newFragment.setArguments(args);
        } catch (InstantiationException e) {
            e.printStackTrace();
            return;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return;
        }

        if (addToBackStack) {
            // Add this transaction to the back stack
            ft.addToBackStack(null);
        }

        // Change to a new fragment
        ft.replace(R.id.frame_contents, newFragment, claz.getClass().getSimpleName());
        ft.commit();
    }
    // END FRAGMENT MANAGEMENT
}
