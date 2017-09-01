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

public class DownloadTask extends AsyncTask<String, Void, DownloadTask.DownloadTaskResults> {

    @Override
    protected DownloadTaskResults doInBackground(String... url) {

        String data = "";

        try {
            data = downloadUrl(url[0]);
        } catch (Exception e) {
            Log.d("Background Task", e.toString());
        }
        JSONObject jObject;
        List<List<HashMap<String, String>>> routes = null;
        String duration = "";
        String distance = "";

        try {
            jObject = new JSONObject(data);
            Log.d("Background Task", jObject.toString());
            DirectionsJSONParser parser = new DirectionsJSONParser();
            routes = parser.parse(jObject);
            String[] results = parser.parseDurationDistance(jObject);
            duration = results[0];
            distance = results[1];

        } catch (Exception e) {
            e.printStackTrace();
        }
        PolylineOptions polylineOptions = DirectionUtils.parseDirection(routes);

        DownloadTaskResults dlResults = new DownloadTaskResults();
        dlResults.setPolylineOptions(polylineOptions);
        dlResults.setDuration(duration);
        dlResults.setDistance(distance);
        return dlResults;
    }

    @Override
    protected void onPostExecute(DownloadTaskResults result) {
        super.onPostExecute(result);
    }

    public class DownloadTaskResults {
        private PolylineOptions polylineOptions;
        private String duration;
        private String distance;

        public PolylineOptions getPolylineOptions() {
            return polylineOptions;
        }

        public void setPolylineOptions(PolylineOptions polylineOptions) {
            this.polylineOptions = polylineOptions;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }
    }
}