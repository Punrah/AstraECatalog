package com.dealerhondabali.astraecatalog.adapter;
        import android.content.Context;
        import android.os.Parcelable;
        import android.support.v4.view.PagerAdapter;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;

        import com.bumptech.glide.Glide;
        import com.bumptech.glide.request.RequestOptions;
        import com.dealerhondabali.astraecatalog.R;

        import java.util.ArrayList;
        import java.util.List;


public class SlidingImage_Adapter extends PagerAdapter {


    private List<String> IMAGES;
    private LayoutInflater inflater;
    private Context context;


    public SlidingImage_Adapter(Context context,List<String> IMAGES) {
        this.context = context;
        this.IMAGES=IMAGES;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return IMAGES.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false);

        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.image);

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.image_wait)
                .error(R.drawable.image_not_found);



        Glide.with(context).load(IMAGES.get(position)).apply(options).into(imageView);

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