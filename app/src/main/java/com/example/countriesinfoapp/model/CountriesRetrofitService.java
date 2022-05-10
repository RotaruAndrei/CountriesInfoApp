package com.example.countriesinfoapp.model;


import com.example.countriesinfoapp.dInjection.DaggerAPIComponent;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;


public class CountriesRetrofitService {

    private static CountriesRetrofitService instance;

    private CountriesRetrofitService () {
        DaggerAPIComponent.create().inject(this);
    }

    public static CountriesRetrofitService getInstance(){

        if (instance == null){

            instance = new CountriesRetrofitService();
        }
        return instance;
    }

    @Inject
    public CountriesAPI api;

//    private CountriesAPI api =

    public Single<List<CountryModel>> getCountries () {
        return api.getCountries();
    }
}
