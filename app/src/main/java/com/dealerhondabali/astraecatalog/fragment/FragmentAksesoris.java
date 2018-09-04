package com.dealerhondabali.astraecatalog.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dealerhondabali.astraecatalog.AsyncTask.MyAsyncTask;
import com.dealerhondabali.astraecatalog.R;
import com.dealerhondabali.astraecatalog.adapter.SlidingImage_Adapter;
import com.dealerhondabali.astraecatalog.app.AppConfig;
import com.github.chrisbanes.photoview.PhotoView;
import com.viewpagerindicator.CirclePageIndicator;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FragmentAksesoris extends Fragment {
    private static final String KEY_MOVIE_TITLE = "key_title";
    private static final String KEY_MOVIE_AKSESORIS = "key_aksesoris";
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    String aksessoris;
    ImageView photoView;

    public static FragmentAksesoris newInstance(String movieTitle,String aksessoris) {
        FragmentAksesoris fragmentAction = new FragmentAksesoris();
        Bundle args = new Bundle();
        args.putString(KEY_MOVIE_TITLE, movieTitle);
        args.putString(KEY_MOVIE_AKSESORIS,aksessoris);
        fragmentAction.setArguments(args);

        return fragmentAction;
    }

    public FragmentAksesoris() {

        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myInflater =inflater.inflate(R.layout.fragment_aksesoris, container, false);
        photoView = myInflater.findViewById(R.id.imageView);
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.image_wait)
                .error(R.drawable.image_not_found);



        aksessoris = getArguments().getString(KEY_MOVIE_AKSESORIS);
        Glide.with(getActivity()).load(aksessoris).apply(options).into(photoView);

        // Inflate the layout for this fragment
        return myInflater;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }



}
