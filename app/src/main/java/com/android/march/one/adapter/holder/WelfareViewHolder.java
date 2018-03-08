package com.android.march.one.adapter.holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.march.one.R;
import com.android.march.one.adapter.RecyclerViewAdapter;
import com.android.march.one.model.bean.ItemBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelfareViewHolder extends RecyclerViewAdapter.ViewHolder<ItemBean> {

    private final AppCompatActivity activity;
    private final int ivWidth;
    private final HashMap<Integer, Integer> hashMap;

    @BindView(R.id.ivWelfare)
    ImageView ivWelfare;

    public WelfareViewHolder(AppCompatActivity activity, View itemView) {
        super(itemView);
        this.activity = activity;
        ivWidth = (getScreenWidth(activity) - dp2px(activity, 16)) / 2;
        hashMap = new HashMap<>();
        ButterKnife.bind(this, itemView);
    }

    @Override
    protected void bindViewHolder(RecyclerViewAdapter<ItemBean> adapter, int position, ItemBean itemBean) {
        Integer viewHeight = hashMap.get(position);
        if (viewHeight != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ivWelfare.getLayoutParams();
            layoutParams.height = viewHeight;
            ivWelfare.setLayoutParams(layoutParams);
            Glide.with(ivWelfare.getContext()).load(itemBean.getUrl()).into(ivWelfare);
        } else {
            Glide.with(ivWelfare.getContext()).load(itemBean.getUrl()).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    int ivHeight = resource.getHeight() * ivWidth / resource.getWidth();
                    hashMap.put(position, ivHeight);
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ivWelfare.getLayoutParams();
                    layoutParams.height = ivHeight;
                    ivWelfare.setLayoutParams(layoutParams);
                    ivWelfare.setImageBitmap(resource);
                }
            });
        }
    }

    public static int getScreenWidth(Context context) {
        if (context == null) {
            return -1;
        }

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, context.getResources().getDisplayMetrics());
    }
}