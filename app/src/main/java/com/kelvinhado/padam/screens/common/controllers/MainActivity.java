package com.kelvinhado.padam.screens.common.controllers;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.kelvinhado.padam.R;
import com.kelvinhado.padam.data.AddressesContract;
import com.kelvinhado.padam.data.models.Address;
import com.kelvinhado.padam.data.utils.AddressUtils;
import com.kelvinhado.padam.screens.common.mvcviews.RootViewMvcImpl;
import com.kelvinhado.padam.screens.credits.controllers.CreditsFragment;
import com.kelvinhado.padam.screens.travel.controllers.TravelFragment;

public class MainActivity extends AppCompatActivity
        implements BaseFragment.AbstractFragmentCallback, NavigationView.OnNavigationItemSelectedListener {

    private RootViewMvcImpl mRootViewMvc;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Instantiate MVC view associated with this activity
        mRootViewMvc = new RootViewMvcImpl(this, null);
        // Set the root view of the associated MVC view as the content of this activity
        setContentView(mRootViewMvc.getRootView());

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // These lines are needed to display the top-left hamburger button
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Make the hamburger button work
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initFakeDatabase();

        // Show the default fragment if the application is not restored
        if (savedInstanceState == null) {
            navigationView.setCheckedItem(R.id.btn_redirect_home);
            replaceFragment(TravelFragment.class, false, null);
        }
    }

    private void initFakeDatabase() {
        Cursor cursor = getContentResolver().query(AddressesContract.AddressesEntry.CONTENT_URI,
                null,
                null,
                null,
                AddressesContract.AddressesEntry._ID);
        if(cursor == null) return;

        if(!cursor.moveToFirst()) {
            // instead duplicating call to insert, we could have implemented a bulkInsert with ContentValues[].
            getContentResolver().insert(AddressesContract.AddressesEntry.CONTENT_URI,
                    AddressUtils.toContentValues(new Address("27 Rue Oudinot, 75008 Paris, France", 48.849355, 2.316055)));
            getContentResolver().insert(AddressesContract.AddressesEntry.CONTENT_URI,
                    AddressUtils.toContentValues(new Address("9 Avenue du Plateau, 94100 Saint-Maur-des-Fossés, France", 48.808672, 2.500076)));
            getContentResolver().insert(AddressesContract.AddressesEntry.CONTENT_URI,
                    AddressUtils.toContentValues(new Address("58 Avenue Gambetta, 93170 Bagnolet, France", 48.874651, 2.421799)));
            getContentResolver().insert(AddressesContract.AddressesEntry.CONTENT_URI,
                    AddressUtils.toContentValues(new Address("20 Avenue Rachel, 75018 Paris, France", 48.885489, 2.331161)));
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

    /**
     * Triggered when an item is selected in the drawer layout
     * @param item item selected
     * @return boolean
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_redirect_home:
                replaceFragment(TravelFragment.class, false, null);
                break;
            case R.id.btn_redirect_credits:
                replaceFragment(CreditsFragment.class, false, null);
                break;
            default:
                return false;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...
        return super.onOptionsItemSelected(item);
    }
}
