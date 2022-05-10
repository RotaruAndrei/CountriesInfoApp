package com.example.countriesinfoapp.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.countriesinfoapp.dInjection.DaggerAPIComponent;
import com.example.countriesinfoapp.model.CountriesRetrofitService;
import com.example.countriesinfoapp.model.CountryModel;

import java.util.List;
import javax.inject.Inject;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CountriesViewModel extends ViewModel {

    public MutableLiveData<List<CountryModel>> getCountries = new MutableLiveData<>();
    public MutableLiveData<Boolean> loadingData             = new MutableLiveData<>();
    public MutableLiveData<Boolean> loadingError            = new MutableLiveData<>();

    // for injection if the class is extending another class u have to call super counstructor
    public CountriesViewModel (){
        super();
        DaggerAPIComponent.create().inject(this);
    }

    @Inject
    public CountriesRetrofitService service;


    private CompositeDisposable disposable = new CompositeDisposable();

    public void refreshData () {
        fetchData();
    }

    private void fetchData () {
        loadingData.setValue(true);
        disposable.add(
                service.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<CountryModel>>() {
                    @Override
                    public void onSuccess(@NonNull List<CountryModel> countryModels) {
                        loadingData.setValue(false);
                        loadingError.setValue(false);
                        getCountries.setValue(countryModels);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        loadingError.setValue(true);
                        loadingData.setValue(false);
                        e.printStackTrace();
                    }
                })
        );


    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
