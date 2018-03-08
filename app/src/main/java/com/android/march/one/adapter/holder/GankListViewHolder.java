package com.android.march.one.adapter.holder;

import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.march.one.R;
import com.android.march.one.UrlMatch;
import com.android.march.one.adapter.RecyclerViewAdapter;
import com.android.march.one.model.bean.ItemBean;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by March on 2018/1/29.
 */

public class GankListViewHolder extends RecyclerViewAdapter.ViewHolder<ItemBean> {

    private final String category;

    @BindView(R.id.cardView)
    CardView cardView;
    @BindView(R.id.ivWelfare)
    ImageView ivWelfare;
    @BindView(R.id.layoutInfo)
    LinearLayout layoutInfo;
    @BindView(R.id.tvDes)
    TextView tvDes;
    @BindView(R.id.ivImage)
    ImageView ivImage;
    @BindView(R.id.tvTag)
    TextView tvTag;
    @BindView(R.id.tvTime)
    TextView tvTime;

    public GankListViewHolder(String category, View itemView) {
        super(itemView);
        this.category = category;
        ButterKnife.bind(this, itemView);
    }

    @Override
    protected void bindViewHolder(RecyclerViewAdapter<ItemBean> adapter, int position, ItemBean itemBean) {
        if (itemBean.getType().equals("福利")) {
            ivWelfare.setVisibility(View.VISIBLE);
            layoutInfo.setVisibility(View.GONE);
            setImage(ivWelfare, itemBean.getUrl());
        } else {
            ivWelfare.setVisibility(View.GONE);
            layoutInfo.setVisibility(View.VISIBLE);
            tvDes.setText(itemBean.getDesc());
            if (itemBean.getImages() != null && itemBean.getImages().size() > 0 && !TextUtils.isEmpty(itemBean.getImages().get(0))) {
                ivImage.setVisibility(View.VISIBLE);
                setImage(ivImage, itemBean.getImages().get(0));
            } else {
                ivImage.setVisibility(View.GONE);
            }
        }

        setTag(tvTag, itemBean.getType(), itemBean.getUrl());

        String name = itemBean.getWho() == null ? "佚名" : itemBean.getWho();
        tvTime.setText(name + " " + getTranslateTime(itemBean.getPublishedAt()));

        setOnClick(cardView, itemBean.getUrl());
    }

    private void setTag(TextView tvTag, String type, String url) {
        if (type.equals("福利")) {
            tvTag.setVisibility(View.GONE);
            return;
        }
        String prefix = "";
        if (category.equals("all")) prefix = type + " · ";

        String key = UrlMatch.processUrl(url);
        GradientDrawable drawable = (GradientDrawable) tvTag.getBackground();
        if (UrlMatch.URL_2_CONTENT.containsKey(key)) {
            drawable.setColor(UrlMatch.URL_2_COLOR.get(key));
            tvTag.setText(prefix + UrlMatch.URL_2_CONTENT.get(key));
        } else {
            if (type.equals("休息视频")) {
                drawable.setColor(UrlMatch.VIDEO_COLOR);
                tvTag.setText(prefix + UrlMatch.VIDEO_CONTENT);
            } else {
                // gitHub 的要特殊处理
                if (url.contains(UrlMatch.GITHUB_PREFIX)) {
                    drawable.setColor(UrlMatch.GITHUB_COLOR);
                    tvTag.setText(prefix + UrlMatch.GITHUB_CONTENT);
                } else {
                    drawable.setColor(UrlMatch.OTHER_BLOG_COLOR);
                    tvTag.setText(prefix + UrlMatch.OTHER_BLOG_CONTENT);
                }
            }
        }
        tvTag.setVisibility(View.VISIBLE);
    }

    private void setImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext()).load(imageUrl).into(imageView);
    }

    private void setOnClick(View view, String url) {
        //view.setOnClickListener(v -> WebViewActivity.start(view.getContext(), R.color.colorHome, "", url));
    }

    /**
     * 如果在1分钟之内发布的显示"刚刚" 如果在1个小时之内发布的显示"XX分钟之前" 如果在1天之内发布的显示"XX小时之前"
     * 如果在今年的1天之外的只显示“月-日”,例如“05-03” 如果不是今年的显示“年-月-日”,例如“2014-03-11”
     *
     * @param time
     * @return
     */
    public String getTranslateTime(String time) {
        long timeMilliseconds = 0;
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        // 在主页面中设置当天时间
        Date nowTime = new Date();
        String currDate = sdf1.format(nowTime);
        long currentMilliseconds = nowTime.getTime();// 当前日期的毫秒值
        Date date = null;
        try {
            // 将给定的字符串中的日期提取出来
            date = sdf1.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
            return time;
        }
        if (date != null) {
            timeMilliseconds = date.getTime();
        }

        long timeDifferent = currentMilliseconds - timeMilliseconds;


        if (timeDifferent < 60000) {// 一分钟之内

            return "刚刚";
        }
        if (timeDifferent < 3600000) {// 一小时之内
            long longMinute = timeDifferent / 60000;
            int minute = (int) (longMinute % 100);
            return minute + "分钟之前";
        }
        long l = 24 * 60 * 60 * 1000; // 每天的毫秒数
        if (timeDifferent < l) {// 小于一天
            long longHour = timeDifferent / 3600000;
            int hour = (int) (longHour % 100);
            return hour + "小时之前";
        }
        if (timeDifferent >= l) {
            String currYear = currDate.substring(0, 4);
            String year = time.substring(0, 4);
            if (!year.equals(currYear)) {
                return time.substring(0, 10);
            }
            return time.substring(5, 10);
        }
        return time;
    }
}