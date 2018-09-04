package com.dealerhondabali.astraecatalog.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dealerhondabali.astraecatalog.R;
import com.dealerhondabali.astraecatalog.adapter.KreditAdapter;
import com.dealerhondabali.astraecatalog.adapter.PriceAdapter;
import com.dealerhondabali.astraecatalog.adapter.SimpleDividerItemDecoration;
import com.dealerhondabali.astraecatalog.persistence.Kredit;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;


public class FragmentPrice extends Fragment {
    private static final String KEY_MOVIE_TITLE = "key_title";
    private static final String KEY_MOVIE_PRICE = "key_price";
    private static final String KEY_MOVIE_SPEK = "key_spek";
    private  ViewPager mPager;
    CirclePageIndicator indicator;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<String> listPrice;
    private RecyclerView recyclerView;
    private PriceAdapter adapter;
    private static LinearLayoutManager mLayoutManager;

    public static FragmentPrice newInstance(String movieTitle, ArrayList<String> listPrice) {
        FragmentPrice fragmentAction = new FragmentPrice();
        Bundle args = new Bundle();
        args.putString(KEY_MOVIE_TITLE, movieTitle);
        args.putStringArrayList(KEY_MOVIE_PRICE,listPrice);
        fragmentAction.setArguments(args);

        return fragmentAction;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        listPrice = getArguments().getStringArrayList(KEY_MOVIE_PRICE);

    }

    private void populate() {
        adapter = new PriceAdapter(getActivity(),listPrice);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myInflater =inflater.inflate(R.layout.fragment_price, container, false);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView = (RecyclerView) myInflater.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        populate();
        return myInflater;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

}
