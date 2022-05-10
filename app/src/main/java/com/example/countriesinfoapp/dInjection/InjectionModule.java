package com.example.countriesinfoapp.dInjection;

import com.example.countriesinfoapp.model.CountriesAPI;
import com.example.countriesinfoapp.model.CountriesRetrofitService;
import com.example.countriesinfoapp.viewModel.CountriesViewModel;

import dagger.Module;
import dagger.Provides;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class InjectionModule {

    public static final String BASE_URL = "https://raw.githubusercontent.com";

    @Provides
    public CountriesAPI getCountriesApi () {

        return  new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(CountriesAPI.class);
    }

    @Provides
    public CountriesRetrofitService getViewModel () {
        return CountriesRetrofitService.getInstance();
    }
}
