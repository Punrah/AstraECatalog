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
import com.dealerhondabali.astraecatalog.adapter.SimpleDividerItemDecoration;
import com.dealerhondabali.astraecatalog.adapter.SlidingImage_Adapter;
import com.dealerhondabali.astraecatalog.adapter.SpesifikasiAdapter;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;


public class FragmentSpesifikasi extends Fragment {
    private static final String KEY_MOVIE_TITLE = "key_title";
    private static final String KEY_MOVIE_NAMA_SPEK = "key_nama_spek";
    private static final String KEY_MOVIE_SPEK = "key_spek";
    private  ViewPager mPager;
    CirclePageIndicator indicator;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<String> listNamaSpek;
    private ArrayList<String> listSpek;
    private RecyclerView recyclerView;
    private SpesifikasiAdapter adapter;
    private static LinearLayoutManager mLayoutManager;

    public static FragmentSpesifikasi newInstance(String movieTitle, ArrayList<String> listNamaSpek,ArrayList<String> listSpek) {
        FragmentSpesifikasi fragmentAction = new FragmentSpesifikasi();
        Bundle args = new Bundle();
        args.putString(KEY_MOVIE_TITLE, movieTitle);
        args.putStringArrayList(KEY_MOVIE_NAMA_SPEK,listNamaSpek);
        args.putStringArrayList(KEY_MOVIE_SPEK,listSpek);
        fragmentAction.setArguments(args);

        return fragmentAction;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        listNamaSpek = getArguments().getStringArrayList(KEY_MOVIE_NAMA_SPEK);
        listSpek = getArguments().getStringArrayList(KEY_MOVIE_SPEK);

    }

    private void populate() {
        adapter = new SpesifikasiAdapter(getActivity(),listNamaSpek,listSpek);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myInflater =inflater.inflate(R.layout.fragment_spesifikasi, container, false);
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
