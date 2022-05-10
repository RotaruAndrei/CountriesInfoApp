package com.example.countriesinfoapp;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.countriesinfoapp.model.CountriesRetrofitService;
import com.example.countriesinfoapp.model.CountryModel;
import com.example.countriesinfoapp.viewModel.CountriesViewModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.internal.schedulers.ExecutorScheduler;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;

public class ViewModelTest {

    // any task will be instant (sync)
    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    public CountriesRetrofitService service;

    @InjectMocks
    CountriesViewModel viewModel = new CountriesViewModel();

    private Single<List<CountryModel>> testSingle;

    @Before
    public void setup () {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSuccess () {
        CountryModel romania = new CountryModel("Romania","Bucharest","");
        ArrayList<CountryModel>  list = new ArrayList<>();
        list.add(romania);

        testSingle = Single.just(list);
        Mockito.when(service.getCountries()).thenReturn(testSingle);
        viewModel.refreshData();

        Assert.assertEquals(1,viewModel.getCountries.getValue().size());
        Assert.assertEquals(false,viewModel.loadingError.getValue());
        Assert.assertEquals(false,viewModel.loadingData.getValue());
    }

    @Test
    public void testUnsuccessful () {
        testSingle = Single.error(new Throwable());

        Mockito.when(service.getCountries()).thenReturn(testSingle);
        viewModel.refreshData();

        Assert.assertEquals(true,viewModel.loadingError.getValue());
        Assert.assertEquals(false,viewModel.loadingData.getValue());
    }

    @Before
    public void setRxSchedulers () {
        Scheduler instant = new Scheduler() {
            @Override
            public @NonNull Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run,true,true);


            }
        };

        RxJavaPlugins.setInitNewThreadSchedulerHandler(schedulerSupplier -> instant);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> instant);
    }
}
