package com.dealerhondabali.astraecatalog.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dealerhondabali.astraecatalog.AsyncTask.MyAsyncTask;
import com.dealerhondabali.astraecatalog.R;
import com.dealerhondabali.astraecatalog.app.AppConfig;
import com.dealerhondabali.astraecatalog.fragment.navigation.InnerFragmentNavigationManager;
import com.dealerhondabali.astraecatalog.fragment.navigation.InnerNavigationManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentMotor#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMotor extends Fragment {

    private InnerNavigationManager mNavigationManager;
    private static final String KEY_MOVIE_TITLE = "key_title";
    TextView warna;
    TextView spesifikasi;
    TextView aksesoris;
    TextView fitur;
    ArrayList<String> listWarna;
    ArrayList<String> listNamaSpek;
    ArrayList<String> listSpek;
    ArrayList<String> listNamaFitur;
    ArrayList<String> listDeskripsiFitur;
    ArrayList<String> listGambarFitur;
    String stringAksesoris;

    public FragmentMotor() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment FragmentMotor.
     */
    public static FragmentMotor newInstance(String movieTitle) {
        FragmentMotor fragmentDrama = new FragmentMotor();
        Bundle args = new Bundle();
        args.putString(KEY_MOVIE_TITLE, movieTitle);
        fragmentDrama.setArguments(args);

        return fragmentDrama;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View myInflater = inflater.inflate(R.layout.fragment_motor, container, false);
        // Inflate the layout for this fragment
        warna = (TextView) myInflater.findViewById(R.id.warna);
        spesifikasi = (TextView) myInflater.findViewById(R.id.spesifikasi);
        aksesoris = (TextView) myInflater.findViewById(R.id.aksesoris);
        fitur = (TextView) myInflater.findViewById(R.id.fitur);
        mNavigationManager = InnerFragmentNavigationManager.obtain(FragmentMotor.this);

        warna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setWarnaFragment();
            }
        });
        spesifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSpesifikasiFragment();
            }
        });
        aksesoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAksesorisFragment();
            }
        });
        fitur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFiturFragment();
            }
        });

        new getMotor(myInflater).execute();

        return  myInflater;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void initiateFragment(View myInflater)
    {
        if (myInflater.findViewById(R.id.container) != null) {
            String title = getArguments().getString(KEY_MOVIE_TITLE);
            mNavigationManager.showFragmentWarna(title,listWarna);


        }
    }

    private void  setWarnaFragment()
    {
        String title = getArguments().getString(KEY_MOVIE_TITLE);
        mNavigationManager.showFragmentWarna(title,listWarna);
    }

    private void  setAksesorisFragment()
    {
        String title = getArguments().getString(KEY_MOVIE_TITLE);
        mNavigationManager.showFragmentAksesoris(title,stringAksesoris);
    }

    private void  setSpesifikasiFragment()
    {
        String title = getArguments().getString(KEY_MOVIE_TITLE);
        mNavigationManager.showFragmentSpesifikasi(title,listNamaSpek,listSpek);
    }

    private void  setFiturFragment()
    {
        String title = getArguments().getString(KEY_MOVIE_TITLE);
        mNavigationManager.showFragmentFitur(title,listNamaFitur,listDeskripsiFitur,listGambarFitur);
    }

    private class getMotor extends MyAsyncTask {
View myInflater;

        public getMotor(View myInflater)
        {
            this.myInflater=myInflater;

        }
        @Override
        public Context getContext () {
            return getActivity();
        }



        @Override
        public void setSuccessPostExecute() {
            initiateFragment(myInflater);


        }

        @Override
        public void setFailPostExecute() {

        }

        public void postData() {
            String url = AppConfig.URL_MOTOR+"&id="+getArguments().getString(KEY_MOVIE_TITLE).replace(" ","%20");;
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

                        JSONArray jsonArray = obj.getJSONArray("warna");
                        listWarna =new ArrayList<>();

                        for(int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String warna = jsonObject.getString("gambar");
                            listWarna.add(warna);
                        }
                        stringAksesoris = obj.getString("aksesoris");
                        JSONArray jsonArray1 = obj.getJSONArray("spesifikasi");
                        listNamaSpek =new ArrayList<>();
                        listSpek =new ArrayList<>();

                        for(int i=0;i<jsonArray1.length();i++)
                        {
                            JSONObject jsonObject = jsonArray1.getJSONObject(i);
                            String nama_spek = jsonObject.getString("nama_spek");
                            listNamaSpek.add(nama_spek);
                            String spek = jsonObject.getString("spek");
                            listSpek.add(spek);
                        }

                        listNamaFitur = new ArrayList<>();
                        listDeskripsiFitur = new ArrayList<>();
                        listGambarFitur = new ArrayList<>();
//                        JSONArray jsonArray2 = obj.getJSONArray("fitur");
//                       for(int i=0;i<jsonArray2.length();i++)
//                        {
//                            JSONObject jsonObject = jsonArray2.getJSONObject(i);
//                            String namaFitur = jsonObject.getString("nama");
//                            listNamaFitur.add(namaFitur);
//                            String deskripsiFitur = jsonObject.getString("deskripsi");
//                            listDeskripsiFitur.add(deskripsiFitur);
//                            String gambarFitur = jsonObject.getString("gambar");
//                            listGambarFitur.add(gambarFitur);
//                        }
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
