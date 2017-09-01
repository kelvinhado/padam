package screens.map.mvcviews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.kelvinhado.padam.R;

/**
 * Created by kelvin on 01/09/2017.
 */

/**
 * An implementation of {@link TravelViewMvc} interface
 */
public class TravelViewMvcImpl implements TravelViewMvc {

    private View mRootView;
    private TravelViewMvcListener mListener;
    private String selectedAddress;

    private Spinner mSpinnerAddresses;
    private Button mBtnValidate;

    public TravelViewMvcImpl(LayoutInflater inflater, ViewGroup container) {
        mRootView = inflater.inflate(R.layout.mvc_view_fragment_travel, container, false);
        initializeViews();
        selectedAddress = "";

        mBtnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    //TODO replace with accurate value
                    mListener.onButtonValidatedClicked("HELLO");
                }
            }
        });
    }

    private void initializeViews() {
        mSpinnerAddresses = (Spinner) mRootView.findViewById(R.id.sp_addresses);
        mBtnValidate = (Button) mRootView.findViewById(R.id.bt_validate);
    }

    @Override
    public void bindAddressesData(ArrayAdapter<CharSequence> addressesAdapter) {
        mSpinnerAddresses.setAdapter(addressesAdapter);
    }

    @Override
    public void showTravelDetails(Object from, Object to, float duration) {
        //TODO show travel details once calculated
    }

    @Override
    public void setListener(TravelViewMvcListener listener) {
        mListener = listener;
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
