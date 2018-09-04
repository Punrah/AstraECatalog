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
import com.dealerhondabali.astraecatalog.adapter.FiturAdapter;
import com.dealerhondabali.astraecatalog.adapter.SimpleDividerItemDecoration;
import com.dealerhondabali.astraecatalog.adapter.SpesifikasiAdapter;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;


public class FragmentFitur extends Fragment {
    private static final String KEY_MOVIE_TITLE = "key_title";
    private static final String KEY_MOVIE_NAMA_FITUR = "key_nama_fitur";
    private static final String KEY_MOVIE_DESKRIPSI_FITUR = "key_deskripsi_fitur";
    private static final String KEY_MOVIE_GAMBAR_FITUR = "key_gambar_fitur";
    private  ViewPager mPager;
    CirclePageIndicator indicator;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<String> listNamaFitur;
    private ArrayList<String> listDeskripsiFitur;
    private ArrayList<String> listGambarFitur;
    private RecyclerView recyclerView;
    private FiturAdapter adapter;
    private static LinearLayoutManager mLayoutManager;

    public static FragmentFitur newInstance(String movieTitle, ArrayList<String> listNamaFitur, ArrayList<String> listDeskripsiFitur,ArrayList<String> listGambarFitur) {
        FragmentFitur fragmentAction = new FragmentFitur();
        Bundle args = new Bundle();
        args.putString(KEY_MOVIE_TITLE, movieTitle);
        args.putStringArrayList(KEY_MOVIE_NAMA_FITUR,listNamaFitur);
        args.putStringArrayList(KEY_MOVIE_DESKRIPSI_FITUR,listDeskripsiFitur);
        args.putStringArrayList(KEY_MOVIE_GAMBAR_FITUR,listGambarFitur);
        fragmentAction.setArguments(args);

        return fragmentAction;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        listNamaFitur = getArguments().getStringArrayList(KEY_MOVIE_NAMA_FITUR);
        listDeskripsiFitur = getArguments().getStringArrayList(KEY_MOVIE_DESKRIPSI_FITUR);
        listGambarFitur = getArguments().getStringArrayList(KEY_MOVIE_GAMBAR_FITUR);

    }

    private void populate() {
        adapter = new FiturAdapter(getActivity(),listNamaFitur,listDeskripsiFitur,listGambarFitur);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myInflater =inflater.inflate(R.layout.fragment_fitur, container, false);
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
