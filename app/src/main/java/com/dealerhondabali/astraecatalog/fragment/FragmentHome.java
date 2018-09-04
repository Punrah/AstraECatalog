package com.dealerhondabali.astraecatalog.fragment;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dealerhondabali.astraecatalog.MainActivity;
import com.dealerhondabali.astraecatalog.R;
import com.dealerhondabali.astraecatalog.WebActivity;
import com.dealerhondabali.astraecatalog.fragment.navigation.FragmentNavigationManager;
import com.dealerhondabali.astraecatalog.fragment.navigation.InnerFragmentNavigationManager;
import com.dealerhondabali.astraecatalog.fragment.navigation.InnerNavigationManager;
import com.dealerhondabali.astraecatalog.fragment.navigation.NavigationManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHome extends Fragment {

    private static final String KEY_MOVIE_TITLE = "key_title";
    private InnerNavigationManager mNavigationManager;
    private TextView kredit;
    private TextView price;
    private TextView simulasi;
    private TextView elearning;
    private TextView product;

    public FragmentHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment FragmentHome.
     */
    public static FragmentHome newInstance(String movieTitle) {
        FragmentHome fragmentAction = new FragmentHome();
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
        View myInflater = inflater.inflate(R.layout.fragment_home, container, false);
        product = (TextView) myInflater.findViewById(R.id.product);
        kredit = (TextView) myInflater.findViewById(R.id.kredit);
        price = (TextView) myInflater.findViewById(R.id.price);
        simulasi = (TextView) myInflater.findViewById(R.id.simulasi);
        elearning = (TextView) myInflater.findViewById(R.id.elearning);

        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).mDrawerLayout.openDrawer((int) Gravity.LEFT);
            }
        });
        kredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).setKreditFragment();
            }
        });
        price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).setPriceFragment();
            }
        });
        elearning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), WebActivity.class);
                i.putExtra("url","https://dealerhondabali.com/elearning");
                startActivity(i);
            }
        });
        simulasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                populateLoginDialog();
            }
        });




        // Inflate the layout for this fragment
        return myInflater;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private void populateLoginDialog()
    {

        LinearLayout layout;
        LayoutInflater myInflater = LayoutInflater.from(getActivity());
        layout = (LinearLayout) myInflater.inflate(R.layout.login_layout,null);
        final EditText editTextLink = (EditText) layout.findViewById(R.id.link);
        final EditText editTextCaptions = (EditText) layout.findViewById(R.id.captions);
        TextView sendButton = (TextView) layout.findViewById(R.id.send);

        final AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity()).setCancelable(true).
                        setView(layout);

        final AlertDialog alertDialog = builder.create();
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setSimulasiFragment();

                alertDialog.dismiss();

            }
        });


        alertDialog.show();
    }
}
