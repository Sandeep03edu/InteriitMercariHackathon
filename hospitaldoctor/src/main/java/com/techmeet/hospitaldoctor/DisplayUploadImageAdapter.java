package com.techmeet.hospitaldoctor;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;
import com.techmeet.common.R;

import java.util.ArrayList;

public class DisplayUploadImageAdapter  extends PagerAdapter {

    private Context mContext;
    private ArrayList<String> imagesList;
    private String TAG = "DisplayUploadImageAdapterTag";

    public DisplayUploadImageAdapter(Context mContext, ArrayList<String> imagesList) {
        this.mContext = mContext;
        this.imagesList = imagesList;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return imagesList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup view, int position) {
        View imageLayout = LayoutInflater.from(mContext).inflate(R.layout.cart_upload_image_new, view, false);
        assert imageLayout != null;
        ImageView imageView = imageLayout.findViewById(R.id.cart_upload_image_new_imageview);
        Picasso.get().load(imagesList.get(position)).into(imageView);
        imageView.setDrawingCacheEnabled(true);
        view.addView(imageLayout);
        return imageLayout;
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

}