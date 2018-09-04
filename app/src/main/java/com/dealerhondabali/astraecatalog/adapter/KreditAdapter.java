package com.dealerhondabali.astraecatalog.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dealerhondabali.astraecatalog.R;
import com.dealerhondabali.astraecatalog.persistence.Kredit;

import java.util.ArrayList;

public class KreditAdapter extends RecyclerView.Adapter<KreditAdapter.MyViewHolder> {

    private ArrayList<Kredit> listKredit;
    private Context context;




    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nama;
        public TextView cash;
        public TextView credit;
        public TextView bonus;

        public MyViewHolder(View view) {
            super(view);
            nama = (TextView) view.findViewById(R.id.nama_kredit);
            cash = (TextView) view.findViewById(R.id.cash);
            credit = (TextView) view.findViewById(R.id.kredit);
            bonus = (TextView) view.findViewById(R.id.bonus);
        }
    }


    public KreditAdapter(Context context, ArrayList<Kredit> listKredit) {
        this.listKredit = listKredit;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_kredit, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Kredit kredit = listKredit.get(position);
        holder.setIsRecyclable(false);
        holder.nama.setText(kredit.nama);
        holder.cash.setText(kredit.cash);
        holder.credit.setText(kredit.credit);
        holder.bonus.setText(kredit.bonus);
    }

    @Override
    public int getItemCount() {
        return listKredit.size();
    }
}