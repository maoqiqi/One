package com.android.march.one.adapter.holder;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.march.one.R;
import com.android.march.one.adapter.RecyclerViewAdapter;
import com.android.march.one.adapter.TypeFactory;
import com.android.march.one.model.bean.ItemBean;
import com.android.march.one.ui.activity.GankActivity;
import com.android.march.one.ui.activity.WebViewActivity;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class HomeViewHolder extends RecyclerViewAdapter.ViewHolder<List<ItemBean>> {

    private final String[] classifies = {"Android", "iOS", "前端", "休息视频", "拓展资源", "瞎推荐", "福利"};
    private final int[] drawableIds = {R.drawable.ic_home_android, R.drawable.ic_home_ios, R.drawable.ic_home_front, R.drawable.ic_home_rest_movie,
            R.drawable.ic_home_resource, R.drawable.ic_home_recommend, R.drawable.ic_home_welfare};
    private final int[] colorIds = {R.color.homeAndroid, R.color.homeIos, R.color.homeFront, R.color.homeRestMovie,
            R.color.homeResource, R.color.homeRecommend, R.color.homeWelfare};

    private final String[] showTexts;

    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvMore)
    TextView tvMore;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.ivTwo1)
    ImageView ivTwo1;
    @BindView(R.id.tvTwo1)
    TextView tvTwo1;
    @BindView(R.id.ivTwo2)
    ImageView ivTwo2;
    @BindView(R.id.tvTwo2)
    TextView tvTwo2;
    @BindView(R.id.layoutTwo)
    LinearLayout layoutTwo;
    @BindView(R.id.layoutTwo1)
    LinearLayout layoutTwo1;
    @BindView(R.id.layoutTwo2)
    LinearLayout layoutTwo2;

    @BindView(R.id.ivOne)
    ImageView ivOne;
    @BindView(R.id.tvOne)
    TextView tvOne;
    @BindView(R.id.layoutOne)
    LinearLayout layoutOne;

    private AppCompatActivity activity;

    public HomeViewHolder(AppCompatActivity activity, View itemView) {
        super(itemView);
        this.activity = activity;
        this.showTexts = activity.getResources().getStringArray(R.array.gank_classify);
        ButterKnife.bind(this, itemView);
    }

    @Override
    protected void bindViewHolder(RecyclerViewAdapter<List<ItemBean>> adapter, int position, List<ItemBean> list) {
        String type = list.get(0).getType();
        for (int i = 0; i < classifies.length; i++) {
            if (type.equals(classifies[i])) {
                iv.setImageResource(drawableIds[i]);
                tvName.setTextColor(activity.getResources().getColor(colorIds[i]));
                tvName.setText(showTexts[i + 1]);
                break;
            }
        }
        tvMore.setOnClickListener(v -> GankActivity.start(activity, type));

        int size = list.size();
        if (size % 3 == 0) {
            setRecyclerView(list);
            layoutOne.setVisibility(View.GONE);
            layoutTwo.setVisibility(View.GONE);
        } else if (size % 3 == 1) {
            setRecyclerView(list.subList(0, list.size() - 1));
            layoutOne.setVisibility(View.VISIBLE);
            layoutTwo.setVisibility(View.GONE);
            setItem(layoutOne, tvOne, ivOne, list.get(size - 1));
        } else {
            setRecyclerView(list.subList(0, list.size() - 2));
            layoutOne.setVisibility(View.GONE);
            layoutTwo.setVisibility(View.VISIBLE);
            setItem(layoutTwo1, tvTwo1, ivTwo1, list.get(size - 2));
            setItem(layoutTwo2, tvTwo2, ivTwo2, list.get(size - 1));
        }
    }

    private void setRecyclerView(List<ItemBean> list) {
        recyclerView.setLayoutManager(new GridLayoutManager(activity, 3));
        recyclerView.setAdapter(new RecyclerViewAdapter<>(list, new TypeFactory().setHomeItem()));
    }

    private void setItem(LinearLayout layout, TextView textView, ImageView imageView, ItemBean bean) {
        if (bean.getType().equals(classifies[6])) {
            textView.setVisibility(View.GONE);
            textView.setText("");
            Glide.with(imageView.getContext()).load(bean.getImageUrl()).into(imageView);
            layout.setOnClickListener(v -> WebViewActivity.start(v.getContext(), R.color.colorHome, "", bean.getUrl()));
        } else {
            textView.setVisibility(View.VISIBLE);
            textView.setText(bean.getDesc());
            Glide.with(imageView.getContext()).load(bean.getImageUrl()).into(imageView);
            layout.setOnClickListener(v -> WebViewActivity.start(v.getContext(), R.color.colorHome, "", bean.getUrl(), true));
        }
    }

    public static final class HomeItemViewHolder extends RecyclerViewAdapter.ViewHolder<ItemBean> {

        @BindView(R.id.layout)
        LinearLayout layout;
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv)
        TextView tv;

        public HomeItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void bindViewHolder(RecyclerViewAdapter<ItemBean> adapter, int position, ItemBean itemBean) {
            tv.setText(itemBean.getDesc());
            Glide.with(iv.getContext()).load(itemBean.getImageUrl()).into(iv);
            layout.setOnClickListener(v -> WebViewActivity.start(v.getContext(), R.color.colorHome, "", itemBean.getUrl(), true));
        }
    }
}