package com.dealerhondabali.astraecatalog.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dealerhondabali.astraecatalog.R;

import java.util.ArrayList;
import java.util.List;

public class SpesifikasiAdapter extends RecyclerView.Adapter<SpesifikasiAdapter.MyViewHolder> {

    private ArrayList<String> listNamaSpek;
    private ArrayList<String> listSpek;
    private Context context;




    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView namaSpek;
        public TextView spek;

        public MyViewHolder(View view) {
            super(view);
            namaSpek = (TextView) view.findViewById(R.id.nama_spek);
            spek = (TextView) view.findViewById(R.id.spek);
        }
    }


    public SpesifikasiAdapter(Context context, ArrayList<String> listNamaSpek,ArrayList<String> listSpek) {
        this.listNamaSpek = listNamaSpek;
        this.listSpek = listSpek;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_spesifikasi, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String namaSpek = listNamaSpek.get(position);
        String spek = listSpek.get(position);
        holder.setIsRecyclable(false);
        holder.namaSpek.setText(namaSpek);
        holder.spek.setText(spek);
    }

    @Override
    public int getItemCount() {
        return listNamaSpek.size();
    }
}