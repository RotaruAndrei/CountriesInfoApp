package com.example.countriesinfoapp.model;

import com.google.gson.annotations.SerializedName;

public class CountryModel {

    @SerializedName("name")
    private String countryName;

    @SerializedName("capital")
    private String countryCapital;

    @SerializedName("flagPNG")
    private String countryImage;

    public CountryModel(String countryName, String countryCapital, String countryImage) {
        this.countryName = countryName;
        this.countryCapital = countryCapital;
        this.countryImage = countryImage;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCapital() {
        return countryCapital;
    }

    public String getCountryImage() {
        return countryImage;
    }
}
