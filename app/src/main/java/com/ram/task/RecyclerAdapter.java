package com.ram.task;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RAMJEE on 16-02-2018.
 */


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<Country> countries;// = new ArrayList<>();
    private Context context;

    public RecyclerAdapter(ArrayList<Country> countries, Context context) {

        this.countries = countries;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_layout,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.rank.setText("Rank: "+countries.get(position).getRank());
        holder.countryName.setText("Country: "+countries.get(position).getCountry());
        holder.population.setText("Population: "+countries.get(position).getPopulation());

       final String url = countries.get(position).getFlag();
        Picasso.with(context).load(url).into(holder.flag);

        holder.flag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent viewImage = new Intent(context,ViewImageActivity.class);

                viewImage.putExtra("image",url);
                context.startActivity(viewImage);
            }
        });
    }

    @Override
    public int getItemCount() {
        return  countries == null ? 0 : countries.size();

    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView flag;
        TextView rank, countryName,population;
        public MyViewHolder(View itemView) {
            super(itemView);

            rank = itemView.findViewById(R.id.rank);
            countryName = itemView.findViewById(R.id.countryName);
            population = itemView.findViewById(R.id.population);
            flag = itemView.findViewById(R.id.flag);
        }
    }
}
