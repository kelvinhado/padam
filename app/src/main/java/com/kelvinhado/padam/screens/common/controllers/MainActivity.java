package com.kelvinhado.padam.screens.common.controllers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.kelvinhado.padam.R;
import com.kelvinhado.padam.screens.common.mvcviews.RootViewMvcImpl;
import com.kelvinhado.padam.screens.travel.controllers.TravelFragment;

public class MainActivity extends AppCompatActivity
        implements BaseFragment.AbstractFragmentCallback {

    RootViewMvcImpl mRootViewMvc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Instantiate MVC view associated with this activity
        mRootViewMvc = new RootViewMvcImpl(this, null);

        // Set the root view of the associated MVC view as the content of this activity
        setContentView(mRootViewMvc.getRootView());

        // Show the default fragment if the application is not restored
        if (savedInstanceState == null) {
            replaceFragment(TravelFragment.class, false, null);
        }
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
