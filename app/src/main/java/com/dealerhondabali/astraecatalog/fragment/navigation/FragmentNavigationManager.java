package com.dealerhondabali.astraecatalog.fragment.navigation;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.dealerhondabali.astraecatalog.BuildConfig;
import com.dealerhondabali.astraecatalog.MainActivity;
import com.dealerhondabali.astraecatalog.R;
import com.dealerhondabali.astraecatalog.fragment.FragmentHome;
import com.dealerhondabali.astraecatalog.fragment.FragmentKredit;
import com.dealerhondabali.astraecatalog.fragment.FragmentMotor;
import com.dealerhondabali.astraecatalog.fragment.FragmentPrice;
import com.dealerhondabali.astraecatalog.fragment.FragmentShow;
import com.dealerhondabali.astraecatalog.fragment.FragmentSimulasi;
import com.dealerhondabali.astraecatalog.persistence.Kredit;

import java.util.ArrayList;
import java.util.List;


/**
 * @author msahakyan
 */

public class FragmentNavigationManager implements NavigationManager {

    private static FragmentNavigationManager sInstance;

    private FragmentManager mFragmentManager;
    private MainActivity mActivity;

    public static FragmentNavigationManager obtain(MainActivity activity) {
        if (sInstance == null) {
            sInstance = new FragmentNavigationManager();
        }
        sInstance.configure(activity);
        return sInstance;
    }

    private void configure(MainActivity activity) {
        mActivity = activity;
        mFragmentManager = mActivity.getSupportFragmentManager();
    }

    @Override
    public void showFragmentHome(String title) {
        showFragment(FragmentHome.newInstance(title), false);
    }



    @Override
    public void showFragmentMotor(String title) {
        showFragment(FragmentMotor.newInstance(title), false);
    }

    @Override
    public void showFragmentKredit(String title, ArrayList<Kredit> listKredit) {
        showFragment(FragmentKredit.newInstance(title,listKredit), false);
    }

    @Override
    public void showFragmentPrice(String title, ArrayList<String> listPrice) {
        showFragment(FragmentPrice.newInstance(title,listPrice), false);
    }

    @Override
    public void showFragmentShow(String title) {
        showFragment(FragmentShow.newInstance(title), false);

    }

    @Override
    public void showFragmentSimulasi(String firstActionMovie, ArrayList<String> listPrice) {
        showFragment(FragmentSimulasi.newInstance(firstActionMovie), false);
    }


    private void showFragment(Fragment fragment, boolean allowStateLoss) {
        FragmentManager fm = mFragmentManager;

        @SuppressLint("CommitTransaction")
        FragmentTransaction ft = fm.beginTransaction()
            .replace(R.id.container, fragment);

        ft.addToBackStack(null);

        if (allowStateLoss || !BuildConfig.DEBUG) {
            ft.commitAllowingStateLoss();
        } else {
            ft.commit();
        }

        fm.executePendingTransactions();
    }
}
