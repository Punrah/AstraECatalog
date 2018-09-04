package com.dealerhondabali.astraecatalog.fragment;

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

import com.dealerhondabali.astraecatalog.R;
import com.dealerhondabali.astraecatalog.fragment.navigation.InnerNavigationManager;
import com.dealerhondabali.astraecatalog.persistence.MerkKompetitor;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHarga#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHarga extends Fragment {

    private InnerNavigationManager mNavigationManager;
    private static final String KEY_MOVIE_TITLE = "key_title";
    private static final String KEY_MOVIE_MERK = "key_merk";
    ArrayList<MerkKompetitor> listMerkKompetitor;

    public FragmentHarga() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment FragmentMotor.
     */
    public static FragmentHarga newInstance(String movieTitle, ArrayList<MerkKompetitor> listMerkKompetitor) {
        FragmentHarga fragmentDrama = new FragmentHarga();
        Bundle args = new Bundle();
        args.putString(KEY_MOVIE_TITLE, movieTitle);
        args.putParcelableArrayList(KEY_MOVIE_MERK,listMerkKompetitor);
        fragmentDrama.setArguments(args);

        return fragmentDrama;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listMerkKompetitor = getArguments().getParcelableArrayList(KEY_MOVIE_MERK);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View myInflater = inflater.inflate(R.layout.fragment_harga, container, false);
        ViewPager viewPager = (ViewPager) myInflater.findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

        // Add Fragments to adapter one by one
        for (MerkKompetitor merkKompetitor:listMerkKompetitor)
        {
            adapter.addFragment(FragmentHargaDetail.newInstance(merkKompetitor.merk,merkKompetitor.hargaKompetitorList), merkKompetitor.merk);
        }
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) myInflater.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        return  myInflater;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
}
