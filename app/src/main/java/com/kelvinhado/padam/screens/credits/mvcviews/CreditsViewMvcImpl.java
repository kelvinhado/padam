package com.kelvinhado.padam.screens.credits.mvcviews;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.kelvinhado.padam.R;

/**
 * Created by kelvin on 02/09/2017.
 */

public class CreditsViewMvcImpl implements CreditsViewMvc, ListView.OnItemClickListener {
    private View mRootView;
    private CreditsViewMvcListener mListener;
    private ListView mListView;

    public CreditsViewMvcImpl(Context context, LayoutInflater inflater, ViewGroup container) {
        mRootView = inflater.inflate(R.layout.mvc_view_fragment_credits, container, false);
        initializeViews();

        String[] ulrsString = context.getResources().getStringArray(R.array.author_web_sources);
        ArrayAdapter<String> UrlsAdapter = new ArrayAdapter<>(context,
                android.R.layout.simple_list_item_1, ulrsString);
        mListView.setAdapter(UrlsAdapter);
        mListView.setOnItemClickListener(this);
    }

    private void initializeViews() {
        mListView = (ListView) mRootView.findViewById(R.id.lv_author_sources);
    }

    @Override
    public void setListener(CreditsViewMvcListener listener) {
        mListener = listener;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String url = (String) adapterView.getItemAtPosition(i);
        Uri selectedUrl = Uri.parse(url);
        mListener.onExternalPageSelected(selectedUrl);
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
