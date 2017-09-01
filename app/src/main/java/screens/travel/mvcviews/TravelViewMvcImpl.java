package screens.travel.mvcviews;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
public class TravelViewMvcImpl implements TravelViewMvc, AdapterView.OnItemSelectedListener {

    private View mRootView;
    private TravelViewMvcListener mListener;
    private String mSelectedAddress;

    private Spinner mSpinnerAddresses;
    private Button mBtnValidate;

    public TravelViewMvcImpl(LayoutInflater inflater, ViewGroup container) {
        mRootView = inflater.inflate(R.layout.mvc_view_fragment_travel, container, false);
        initializeViews();
        mSelectedAddress = "";

        mBtnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null && !mSelectedAddress.isEmpty()) {
                    mListener.onButtonValidatedClicked(mSelectedAddress);
                }
            }
        });

        mSpinnerAddresses.setOnItemSelectedListener(this);
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

    // Spinner item selection
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
        mSelectedAddress = (String) adapterView.getItemAtPosition(pos);
        Log.d("LOL", mSelectedAddress);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    // End spinner item selection

    @Override
    public View getRootView() {
        return mRootView;
    }

    @Override
    public Bundle getViewState() {
        return null;
    }
}
