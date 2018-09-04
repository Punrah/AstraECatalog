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

import java.util.ArrayList;

public class FiturAdapter extends RecyclerView.Adapter<FiturAdapter.MyViewHolder> {

    private ArrayList<String> listNamaFitur;
    private ArrayList<String> listDeskripsiFitur;
    private ArrayList<String> listGambarFitur;
    private Context context;




    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView namaFitur;
        public TextView deskripsiFitur;
        public ImageView gambarFitur;

        public MyViewHolder(View view) {
            super(view);
            namaFitur = (TextView) view.findViewById(R.id.nama_fitur);
            deskripsiFitur = (TextView) view.findViewById(R.id.deskripsi_fitur);
            gambarFitur=(ImageView) view.findViewById(R.id.gambar_fitur);
        }
    }


    public FiturAdapter(Context context, ArrayList<String> listNamaFitur, ArrayList<String> listDeskripsiFitur,ArrayList<String> listGambarFitur) {
        this.listNamaFitur = listNamaFitur;
        this.listDeskripsiFitur = listDeskripsiFitur;
        this.listGambarFitur = listGambarFitur;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_fitur, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String namaFitur = listNamaFitur.get(position);
        String deskripsiFitur = listDeskripsiFitur.get(position);
        String gambarFitur = listGambarFitur.get(position);
        holder.setIsRecyclable(false);
        holder.namaFitur.setText(namaFitur);
        holder.deskripsiFitur.setText(deskripsiFitur);
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.image_wait)
                .error(R.drawable.image_not_found);
        Glide.with(context).load(gambarFitur).apply(options).into(holder.gambarFitur);

    }

    @Override
    public int getItemCount() {
        return listNamaFitur.size();
    }
}