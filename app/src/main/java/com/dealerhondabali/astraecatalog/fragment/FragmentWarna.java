package com.dealerhondabali.astraecatalog.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dealerhondabali.astraecatalog.AsyncTask.MyAsyncTask;
import com.dealerhondabali.astraecatalog.MainActivity;
import com.dealerhondabali.astraecatalog.R;
import com.dealerhondabali.astraecatalog.adapter.SlidingImage_Adapter;
import com.dealerhondabali.astraecatalog.app.AppConfig;
import com.dealerhondabali.astraecatalog.persistence.Motor;
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
import java.util.Timer;
import java.util.TimerTask;


public class FragmentWarna extends Fragment {
    private static final String KEY_MOVIE_TITLE = "key_title";
    private static final String KEY_MOVIE_WARNA = "key_warna";
    private  ViewPager mPager;
    CirclePageIndicator indicator;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private List<String> listWarna;

    public static FragmentWarna newInstance(String movieTitle, ArrayList<String> listWarna) {
        FragmentWarna fragmentAction = new FragmentWarna();
        Bundle args = new Bundle();
        args.putString(KEY_MOVIE_TITLE, movieTitle);
        args.putStringArrayList(KEY_MOVIE_WARNA,listWarna);
        fragmentAction.setArguments(args);

        return fragmentAction;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        listWarna = getArguments().getStringArrayList(KEY_MOVIE_WARNA);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myInflater =inflater.inflate(R.layout.fragment_warna, container, false);
        // Inflate the layout for this fragment
        init(myInflater);

        return myInflater;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private void init(View myInflater) {
        mPager = (ViewPager) myInflater.findViewById(R.id.pager);
        indicator = (CirclePageIndicator)
                myInflater.findViewById(R.id.indicator);
//addDrawerItems();

        mPager.setAdapter(new SlidingImage_Adapter(getActivity(),listWarna));
        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);



        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });


    }
}
