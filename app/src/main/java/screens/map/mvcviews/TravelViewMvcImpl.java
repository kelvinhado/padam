package screens.map.mvcviews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kelvinhado.padam.R;

/**
 * Created by kelvin on 01/09/2017.
 */

/**
 * An implementation of {@link TravelViewMvc} interface
 */
public class TravelViewMvcImpl implements TravelViewMvc {

    private View mRootView;
    private TravelViewMvcListener mlistener;

    public TravelViewMvcImpl(LayoutInflater inflater, ViewGroup container) {
        mRootView = inflater.inflate(R.layout.mvc_view_fragment_travel, container, false);
    }

    @Override
    public void bindAddressesData(String[] storedAddresses) {

    }

    @Override
    public void showTravelDetails(Object from, Object to, float duration) {

    }

    @Override
    public void setListener(TravelViewMvcListener listener) {
        mlistener = listener;
    }

    @Override
    public View getRootView() {
        return mRootView;
    }

    @Override
    public Bundle getViewState() {
        return null;
    }
}
