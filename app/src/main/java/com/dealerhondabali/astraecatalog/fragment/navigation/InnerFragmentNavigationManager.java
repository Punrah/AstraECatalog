package com.dealerhondabali.astraecatalog.fragment.navigation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.dealerhondabali.astraecatalog.BuildConfig;
import com.dealerhondabali.astraecatalog.MainActivity;
import com.dealerhondabali.astraecatalog.R;
import com.dealerhondabali.astraecatalog.fragment.FragmentAksesoris;
import com.dealerhondabali.astraecatalog.fragment.FragmentFitur;
import com.dealerhondabali.astraecatalog.fragment.FragmentHome;
import com.dealerhondabali.astraecatalog.fragment.FragmentMotor;
import com.dealerhondabali.astraecatalog.fragment.FragmentSpesifikasi;
import com.dealerhondabali.astraecatalog.fragment.FragmentWarna;

import java.util.ArrayList;
import java.util.List;


/**
 * @author msahakyan
 */

public class InnerFragmentNavigationManager implements InnerNavigationManager {

    private static InnerFragmentNavigationManager sInstance;

    private FragmentManager mFragmentManager;
    private FragmentMotor mActivity;

    public static InnerFragmentNavigationManager obtain(FragmentMotor activity) {
        if (sInstance == null) {
            sInstance = new InnerFragmentNavigationManager();
        }
        sInstance.configure(activity);
        return sInstance;
    }

    private void configure(FragmentMotor activity) {
        mActivity = activity;
        mFragmentManager = mActivity.getChildFragmentManager();
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

    @Override
    public void showFragmentWarna(String title, ArrayList<String> listWarna) {
        showFragment(FragmentWarna.newInstance(title,listWarna), false);
    }

    @Override
    public void showFragmentAksesoris(String title, String aksesoris) {
        showFragment(FragmentAksesoris.newInstance(title , aksesoris), false);
    }

    @Override
    public void showFragmentSpesifikasi(String title, ArrayList<String> listNamaSpek, ArrayList<String> listSpek) {
        showFragment(FragmentSpesifikasi.newInstance(title , listNamaSpek,listSpek), false);
    }

    @Override
    public void showFragmentFitur(String title, ArrayList<String> listNamaFitur, ArrayList<String> listDeskripsiFitur, ArrayList<String> listGambarFitur) {
        showFragment(FragmentFitur.newInstance(title , listNamaFitur,listDeskripsiFitur,listGambarFitur), false);
    }
}
