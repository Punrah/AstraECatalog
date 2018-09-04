package com.dealerhondabali.astraecatalog.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dealerhondabali.astraecatalog.R;
import com.dealerhondabali.astraecatalog.persistence.HargaKompetitor;

import java.util.ArrayList;

public class HargaDetailAdapter extends RecyclerView.Adapter<HargaDetailAdapter.MyViewHolder> {

    private ArrayList<HargaKompetitor> listHargaKompetitor;
    private Context context;




    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView jenis;
        public TextView harga;

        public MyViewHolder(View view) {
            super(view);
            jenis = (TextView) view.findViewById(R.id.jenis);
            harga = (TextView) view.findViewById(R.id.harga);
        }
    }


    public HargaDetailAdapter(Context context, ArrayList<HargaKompetitor> listhargaKompetitor) {
        this.listHargaKompetitor = listhargaKompetitor;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_harga_detail, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        holder.jenis.setText(listHargaKompetitor.get(position).jenis);
        holder.harga.setText(listHargaKompetitor.get(position).harga);
    }

    @Override
    public int getItemCount() {
        return listHargaKompetitor.size();
    }
}