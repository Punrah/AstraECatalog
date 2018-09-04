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
import com.dealerhondabali.astraecatalog.adapter.SimpleDividerItemDecoration;
import com.dealerhondabali.astraecatalog.adapter.SpesifikasiAdapter;
import com.dealerhondabali.astraecatalog.persistence.Kredit;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;


public class FragmentKredit extends Fragment {
    private static final String KEY_MOVIE_TITLE = "key_title";
    private static final String KEY_MOVIE_KREDIT = "key_kredit";
    private static final String KEY_MOVIE_SPEK = "key_spek";
    private  ViewPager mPager;
    CirclePageIndicator indicator;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<Kredit> listKredit;
    private RecyclerView recyclerView;
    private KreditAdapter adapter;
    private static LinearLayoutManager mLayoutManager;

    public static FragmentKredit newInstance(String movieTitle, ArrayList<Kredit> listKredit) {
        FragmentKredit fragmentAction = new FragmentKredit();
        Bundle args = new Bundle();
        args.putString(KEY_MOVIE_TITLE, movieTitle);
        args.putParcelableArrayList(KEY_MOVIE_KREDIT,listKredit);
        fragmentAction.setArguments(args);

        return fragmentAction;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        listKredit = getArguments().getParcelableArrayList(KEY_MOVIE_KREDIT);

    }

    private void populate() {
        adapter = new KreditAdapter(getActivity(),listKredit);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myInflater =inflater.inflate(R.layout.fragment_kredit, container, false);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView = (RecyclerView) myInflater.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(
                getActivity()
        ));
        populate();
        return myInflater;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

}
