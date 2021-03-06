package com.kelvinhado.padam.screens.common.controllers;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by kelvin on 01/09/2017.
 */

public class BaseFragment extends Fragment {

    private AbstractFragmentCallback mCallback;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (AbstractFragmentCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement " + AbstractFragmentCallback.class.getCanonicalName());
        }

    }

    /**
     * This method replaces the currently shown fragment with a new fragment of a particular class.
     * If a fragment of the required class already shown - does nothing.
     * @param claz the class of the fragment to show
     * @param addToBackStack whether the replacement should be added to back-stack
     * @param args arguments for the newly created fragment (can be null)
     */
    public void replaceFragment(Class<? extends Fragment> claz, boolean addToBackStack,
                                Bundle args) {
        mCallback.replaceFragment(claz, addToBackStack, args);
    }


    /**
     * The enclosing activity must implement this interface
     */
    public interface AbstractFragmentCallback {

        /**
         * Call to this method replaces the currently shown fragment with a new one
         *
         * @param claz           the class of the new fragment
         * @param addToBackStack whether the old fragment should be added to the back stack
         * @param args           arguments to be set for the new fragment
         */
        void replaceFragment(Class<? extends Fragment> claz, boolean addToBackStack,
                             Bundle args);

    }
}
