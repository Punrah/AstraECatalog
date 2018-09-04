package com.dealerhondabali.astraecatalog.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dealerhondabali.astraecatalog.AsyncTask.MyAsyncTask;
import com.dealerhondabali.astraecatalog.R;
import com.dealerhondabali.astraecatalog.adapter.SlidingImage_Adapter;
import com.dealerhondabali.astraecatalog.adapter.SlidingImage_AdapterSHOW;
import com.dealerhondabali.astraecatalog.app.AppConfig;
import com.dealerhondabali.astraecatalog.persistence.Show;
import com.viewpagerindicator.CirclePageIndicator;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FragmentShow extends Fragment {
    private static final String KEY_MOVIE_TITLE = "key_title";
    private  ViewPager mPager;
    CirclePageIndicator indicator;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private List<Show> listShow;

    public static FragmentShow newInstance(String movieTitle) {
        FragmentShow fragmentAction = new FragmentShow();
        Bundle args = new Bundle();
        args.putString(KEY_MOVIE_TITLE, movieTitle);
        fragmentAction.setArguments(args);

        return fragmentAction;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myInflater =inflater.inflate(R.layout.fragment_show, container, false);
        // Inflate the layout for this fragment
        new getShow(myInflater).execute();

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

        mPager.setAdapter(new SlidingImage_AdapterSHOW(getActivity(),listShow));
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


    private class getShow extends MyAsyncTask {
        View myInflater;

        public getShow(View myInflater)
        {
            this.myInflater=myInflater;

        }
        @Override
        public Context getContext () {
            return getActivity();
        }



        @Override
        public void setSuccessPostExecute() {
            init(myInflater);


        }

        @Override
        public void setFailPostExecute() {

        }

        public void postData() {
            String url = AppConfig.URL_SHOW;
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httppost = new HttpGet(url);
            try {
                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                String jsonStr = EntityUtils.toString(entity, "UTF-8");

                if (jsonStr != null) {
                    try {
                        isSucces=true;
                        JSONObject obj = new JSONObject(jsonStr);

                        JSONArray jsonArray = obj.getJSONArray("show");
                        listShow =new ArrayList<>();

                        for(int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Show show = new Show();
                            show.gambar=jsonObject.getString("gambar");
                            show.label=jsonObject.getString("label");
                            show.link=jsonObject.getString("link");
                            listShow.add(show);
                        }

                    } catch (final JSONException e) {
                        badServerAlert();
                    }
                } else {
                    badServerAlert();
                }
            } catch (IOException e) {
                badInternetAlert();
            }
        }






    }
}
