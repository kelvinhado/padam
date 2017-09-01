package com.kelvinhado.padam.network.direction;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.PolylineOptions;
import com.kelvinhado.padam.network.direction.utils.DirectionUtils;
import com.kelvinhado.padam.network.direction.utils.DirectionsJSONParser;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import static com.kelvinhado.padam.network.direction.utils.DirectionUtils.downloadUrl;

/**
 * Created by kelvin on 01/09/2017.
 */

public class DownloadTask extends AsyncTask<String, Void, PolylineOptions> {

    @Override
    protected PolylineOptions doInBackground(String... url) {

        String data = "";

        try {
            data = downloadUrl(url[0]);
        } catch (Exception e) {
            Log.d("Background Task", e.toString());
        }

        JSONObject jObject;
        List<List<HashMap<String, String>>> routes = null;

        try {
            jObject = new JSONObject(data);
            DirectionsJSONParser parser = new DirectionsJSONParser();

            routes = parser.parse(jObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DirectionUtils.parseDirection(routes);
    }

    @Override
    protected void onPostExecute(PolylineOptions result) {
        super.onPostExecute(result);
    }
}