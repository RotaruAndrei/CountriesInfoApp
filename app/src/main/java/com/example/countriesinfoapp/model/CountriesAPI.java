package com.example.countriesinfoapp.model;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface CountriesAPI {

    @GET("DevTides/countries/master/countriesV2.json")
    Single<List<CountryModel>> getCountries ();
}
