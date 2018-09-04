package com.dealerhondabali.astraecatalog;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.dealerhondabali.astraecatalog.AsyncTask.MyAsyncTask;
import com.dealerhondabali.astraecatalog.adapter.CustomExpandableListAdapter;
import com.dealerhondabali.astraecatalog.app.AppConfig;
import com.dealerhondabali.astraecatalog.fragment.navigation.FragmentNavigationManager;
import com.dealerhondabali.astraecatalog.fragment.navigation.NavigationManager;
import com.dealerhondabali.astraecatalog.persistence.Kredit;
import com.dealerhondabali.astraecatalog.persistence.Motor;

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
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    private String[] items;

    private ExpandableListView mExpandableListView;
    private ExpandableListAdapter mExpandableListAdapter;
    private ArrayList<Motor> listMotor;
    private ArrayList<Kredit> listKredit;
    private ArrayList<String> listPrice;
    public NavigationManager mNavigationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        mExpandableListView = (ExpandableListView) findViewById(R.id.navList);
        mNavigationManager = FragmentNavigationManager.obtain(this);




        new getMotor(savedInstanceState).execute();
        setupDrawer();

        if (savedInstanceState == null) {
            selectFirstItemAsDefault();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.list_product);

    }

    private void selectFirstItemAsDefault() {
        if (mNavigationManager != null) {
            setHomeFragment();
        }
    }





    private void addDrawerItems() {
        mExpandableListAdapter = new CustomExpandableListAdapter(this, listMotor);
        mExpandableListView.setAdapter(mExpandableListAdapter);


        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                if(groupPosition==(listMotor.size()-1))
                {
                    String title = listMotor.get(groupPosition).arrayMotor[childPosition];
                    mNavigationManager.showFragmentShow(title);
                    mActivityTitle = title;
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                }
                else
                {
                    String title = listMotor.get(groupPosition).arrayMotor[childPosition];
                    mNavigationManager.showFragmentMotor(title);
                    mActivityTitle = title;

                    mDrawerLayout.closeDrawer(GravityCompat.START);
                }
                return false;
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(R.string.product_titile);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
//        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {

            return true;
        }
        else
        {
            setHomeFragment();
        }

        return super.onOptionsItemSelected(item);
    }

    private void  setHomeFragment()
    {
        String firstActionMovie = getResources().getString(R.string.app_name);
        mNavigationManager.showFragmentHome(firstActionMovie);
        getSupportActionBar().setTitle(firstActionMovie);
        mActivityTitle=firstActionMovie;
        mDrawerLayout.closeDrawers();
    }

    public void setKreditFragment()
    {
        String firstActionMovie = getResources().getString(R.string.app_name);
        mNavigationManager.showFragmentKredit(firstActionMovie,listKredit);
        getSupportActionBar().setTitle(firstActionMovie);
        mActivityTitle=firstActionMovie;
    }

    public void setPriceFragment()
    {
        String firstActionMovie = getResources().getString(R.string.app_name);
        mNavigationManager.showFragmentPrice(firstActionMovie,listPrice);
        getSupportActionBar().setTitle(firstActionMovie);
        mActivityTitle=firstActionMovie;
    }

    public void setSimulasiFragment() {
        String firstActionMovie = getResources().getString(R.string.app_name);
        mNavigationManager.showFragmentSimulasi(firstActionMovie,listPrice);
        getSupportActionBar().setTitle(firstActionMovie);
        mActivityTitle=firstActionMovie;
    }

    private class getMotor extends MyAsyncTask {
Bundle savedInstanceState;

        public getMotor(Bundle savedInstanceState)
        {
            this.savedInstanceState=savedInstanceState;

        }




        @Override
        public Context getContext () {
            return MainActivity.this;
        }



        @Override
        public void setSuccessPostExecute() {

            addDrawerItems();



        }

        @Override
        public void setFailPostExecute() {

        }

        public void postData() {
            String url = AppConfig.URL_LIST_MOTOR;
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

                            JSONArray jsonArray = obj.getJSONArray("result");
                            listMotor =new ArrayList<>();

                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Motor motor = new Motor();
                                motor.kategoriMotor = jsonObject.getString("kategori_motor");
                                JSONArray jsonArray1 =jsonObject.getJSONArray("list_motor");
                                motor.arrayMotor = new String[jsonArray1.length()];
                                for(int j=0;j<jsonArray1.length();j++)
                                {
                                    motor.arrayMotor[j] = jsonArray1.getString(j);
                                }
                               listMotor.add(motor);
                            }
                        listKredit =new ArrayList<>();
                        //JSONArray jsonArray1 = obj.getJSONArray("kredit");

//                        for(int i=0;i<jsonArray1.length();i++)
//                        {
//                            JSONObject jsonObject = jsonArray1.getJSONObject(i);
//                            Kredit kredit = new Kredit();
//                            kredit.nama= jsonObject.getString("nama");
//                            kredit.cash= jsonObject.getString("cash");
//                            kredit.credit= jsonObject.getString("kredit");
//                            kredit.bonus= jsonObject.getString("bonus");
//                            listKredit.add(kredit);
//                        }
                        listPrice =new ArrayList<>();
                        JSONArray jsonArray2 = obj.getJSONArray("price");


                        for(int i=0;i<jsonArray2.length();i++)
                        {
                            JSONObject jsonObject = jsonArray2.getJSONObject(i);
                            String gambar =jsonObject.getString("gambar");
                            listPrice.add(gambar);
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

    @Override
    public void onBackPressed() {
        if (this.mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
