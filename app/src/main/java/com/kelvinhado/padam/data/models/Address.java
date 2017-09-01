package com.kelvinhado.padam.data.models;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by kelvin on 01/09/2017.
 */

public class Address {

    private int id;
    private String name;
    private double latitude;
    private double longitude;

    public Address(int id, String name, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public LatLng getLatLng() {
        return new LatLng(latitude, longitude);
    }
}
