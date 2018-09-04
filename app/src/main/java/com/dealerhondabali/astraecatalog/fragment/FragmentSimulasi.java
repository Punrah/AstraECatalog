package com.dealerhondabali.astraecatalog.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dealerhondabali.astraecatalog.AsyncTask.MyAsyncTask;
import com.dealerhondabali.astraecatalog.MainActivity;
import com.dealerhondabali.astraecatalog.R;
import com.dealerhondabali.astraecatalog.app.AppConfig;
import com.dealerhondabali.astraecatalog.fragment.navigation.InnerFragmentNavigationManager;
import com.dealerhondabali.astraecatalog.fragment.navigation.InnerNavigationManager;
import com.dealerhondabali.astraecatalog.persistence.HargaKompetitor;
import com.dealerhondabali.astraecatalog.persistence.Kredit;
import com.dealerhondabali.astraecatalog.persistence.MerkKompetitor;
import com.dealerhondabali.astraecatalog.persistence.Motor;

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


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSimulasi#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSimulasi extends Fragment {

    private InnerNavigationManager mNavigationManager;
    private static final String KEY_MOVIE_TITLE = "key_title";
    private ArrayList<MerkKompetitor> listMerkKompetitor;

    public FragmentSimulasi() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment FragmentMotor.
     */
    public static FragmentSimulasi newInstance(String movieTitle) {
        FragmentSimulasi fragmentDrama = new FragmentSimulasi();
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

        final View myInflater = inflater.inflate(R.layout.fragment_simulasi, container, false);
        new getSimulasi(myInflater).execute();

        return  myInflater;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void init(View myInflater)
    {
        ViewPager viewPager = (ViewPager) myInflater.findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

        // Add Fragments to adapter one by one
        adapter.addFragment(FragmentDP.newInstance("Simulasi DP",new ArrayList<String>()), "Simulasi DP");
        adapter.addFragment(FragmentAngsuran.newInstance("Simulasi Angsuran",new ArrayList<String>()), "Simulasi Angsuran");
        adapter.addFragment(FragmentHarga.newInstance("Harga Kompetitor",listMerkKompetitor), "Harga Kompetitor");
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) myInflater.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }




    // Adapter for the viewpager using FragmentPagerAdapter
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    private class getSimulasi extends MyAsyncTask {
        View myInflater;

        public getSimulasi(View myInflater)
        {
            this.myInflater=myInflater;

        }

        @Override
        public Context getContext () {
            return getActivity();
        }



        @Override
        public void setSuccessPostExecute() {

            //addDrawerItems();
            init(myInflater);



        }

        @Override
        public void setFailPostExecute() {

        }

        public void postData() {
            String url = AppConfig.URL_HARGA_KOMPETITOR;
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            try {
                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httpGet);
                HttpEntity entity = response.getEntity();
                String jsonStr = EntityUtils.toString(entity, "UTF-8");

                if (jsonStr != null) {
                    try {
                        isSucces=true;
                        JSONObject obj = new JSONObject(jsonStr);

                        JSONArray jsonArray = obj.getJSONArray("harga_kompetitor");
                        listMerkKompetitor =new ArrayList<>();

                        for(int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            MerkKompetitor motor = new MerkKompetitor();
                            motor.merk = jsonObject.getString("merk");
                            JSONArray jsonArray1 =jsonObject.getJSONArray("list_harga");
                            motor.hargaKompetitorList = new ArrayList<>()
;                           for(int j=0;j<jsonArray1.length();j++)
                            {
                                JSONObject jsonObject1= jsonArray1.getJSONObject(j);
                                HargaKompetitor hargaKompetitor = new HargaKompetitor();
                                hargaKompetitor.jenis=jsonObject1.getString("jenis");
                                hargaKompetitor.harga=jsonObject1.getString("harga");
                                motor.hargaKompetitorList.add(hargaKompetitor);
                            }
                            listMerkKompetitor.add(motor);
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
