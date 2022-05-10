package com.example.countriesinfoapp.dInjection;

import com.example.countriesinfoapp.model.CountriesRetrofitService;
import com.example.countriesinfoapp.viewModel.CountriesViewModel;

import dagger.Component;

@Component(modules = {InjectionModule.class})
public interface APIComponent {

    // where i want to inject the module provider
    void inject (CountriesRetrofitService service);

    void inject (CountriesViewModel retrofitService);
}
