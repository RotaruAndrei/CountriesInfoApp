package com.example.countriesinfoapp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countriesinfoapp.R;
import com.example.countriesinfoapp.model.CountryModel;

import java.util.ArrayList;
import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ViewHolder> {

    private List<CountryModel> countryList = new ArrayList<>();



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_country,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bindData(countryList.get(position));
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public void setCountryList(List<CountryModel> countryList) {
        this.countryList = countryList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView cardCountryImage;
        private TextView cardCountryName, cardCountryCapital;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardCountryCapital = itemView.findViewById(R.id.card_countryCapital_id);
            cardCountryName    = itemView.findViewById(R.id.card_countryName_id);
            cardCountryImage   = itemView.findViewById(R.id.card_countryImage_id);
        }

        public void bindData (CountryModel countryModel) {

            cardCountryName.setText(countryModel.getCountryName());
            cardCountryCapital.setText(countryModel.getCountryCapital());
            Util.insertImage(cardCountryImage,countryModel.getCountryImage(),Util.getCircularDrawable(cardCountryImage.getContext()));
        }
    }
}
