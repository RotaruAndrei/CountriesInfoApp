package com.example.countriesinfoapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.countriesinfoapp.R;
import com.example.countriesinfoapp.model.CountryModel;
import com.example.countriesinfoapp.viewModel.CountriesViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView loadingError;
    private ProgressBar progressBar;
    private CountriesAdapter adapter;
    private CountriesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        viewModel = ViewModelProviders.of(this).get(CountriesViewModel.class);
        viewModel.refreshData();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CountriesAdapter();
        recyclerView.setAdapter(adapter);
        observeViewModel();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.refreshData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    //init ui components
    private void initUI () {

        recyclerView       = findViewById(R.id.main_recyclerView_id);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout_id);
        loadingError       = findViewById(R.id.main_errorLoading_id);
        progressBar        = findViewById(R.id.main_progressBar_id);
    }

    //observe view model data
    private void observeViewModel () {

        viewModel.getCountries.observe(this, new Observer<List<CountryModel>>() {
            @Override
            public void onChanged(List<CountryModel> countryModels) {

                if (countryModels != null){
                    adapter.setCountryList(countryModels);
                    adapter.notifyDataSetChanged();
                    recyclerView.setVisibility(View.VISIBLE);

                }
            }
        });

        viewModel.loadingData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

                if (aBoolean != null){
                    progressBar.setVisibility(aBoolean? View.VISIBLE:View.INVISIBLE);
                }

                if (aBoolean){
                    loadingError.setVisibility(View.INVISIBLE);
                    recyclerView.setVisibility(View.INVISIBLE);
                }
            }
        });

        viewModel.loadingError.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

                if (aBoolean != null){
                    loadingError.setVisibility(aBoolean? View.VISIBLE:View.INVISIBLE);
                }
            }
        });
    }
}