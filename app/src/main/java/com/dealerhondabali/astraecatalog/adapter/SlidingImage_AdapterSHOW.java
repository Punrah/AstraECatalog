package com.dealerhondabali.astraecatalog.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dealerhondabali.astraecatalog.R;
import com.dealerhondabali.astraecatalog.WebActivity;
import com.dealerhondabali.astraecatalog.persistence.Show;

import java.util.List;


public class SlidingImage_AdapterSHOW extends PagerAdapter {


    private List<Show> list;
    private LayoutInflater inflater;
    private Context context;


    public SlidingImage_AdapterSHOW(Context context, List<Show> list) {
        this.context = context;
        this.list=list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position) {
        View imageLayout = inflater.inflate(R.layout.slidingimages_layout_show, view, false);

        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.image);
        final TextView textView = (TextView) imageLayout
                .findViewById(R.id.label);
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.image_wait)
                .error(R.drawable.image_not_found);
        textView.setText(list.get(position).label);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, WebActivity.class);
                i.putExtra("url",list.get(position).link);
                context.startActivity(i);
            }
        });


        Glide.with(context).load(list.get(position).gambar).apply(options).into(imageView);

        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


}