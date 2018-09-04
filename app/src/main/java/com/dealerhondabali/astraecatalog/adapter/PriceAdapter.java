package com.dealerhondabali.astraecatalog.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dealerhondabali.astraecatalog.R;
import com.dealerhondabali.astraecatalog.persistence.Kredit;

import java.util.ArrayList;

public class PriceAdapter extends RecyclerView.Adapter<PriceAdapter.MyViewHolder> {

    private ArrayList<String> listPrice;
    private Context context;




    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView gambar;

        public MyViewHolder(View view) {
            super(view);
            gambar = (ImageView) view.findViewById(R.id.imageView);
        }
    }


    public PriceAdapter(Context context, ArrayList<String> listPrice) {
        this.listPrice = listPrice;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_price, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String s = listPrice.get(position);
        holder.setIsRecyclable(false);
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.image_wait)
                .error(R.drawable.image_not_found);
        Glide.with(context).load(s).apply(options).into(holder.gambar);
    }

    @Override
    public int getItemCount() {
        return listPrice.size();
    }
}