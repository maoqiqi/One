package com.android.march.one.ui.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;

import com.android.march.one.Constants;
import com.android.march.one.GlideImageLoader;
import com.android.march.one.OneUtils;
import com.android.march.one.R;
import com.android.march.one.RootView;
import com.android.march.one.adapter.RecyclerViewAdapter;
import com.android.march.one.adapter.TypeFactory;
import com.android.march.one.model.bean.ItemBean;
import com.android.march.one.presenter.contract.HomeContract;
import com.android.march.one.ui.activity.MainActivity;
import com.youth.banner.Banner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class HomeView extends RootView<HomeContract.Presenter> implements HomeContract.View {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private String year;
    private String month;
    private String day;

    public HomeView(Context context) {
        super(context);
    }

    public HomeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayout() {
        return R.layout.layout_home;
    }

    public void setData() {
        MainActivity mainActivity = (MainActivity) getContext();
        mainActivity.associateDrawerLayout(toolbar);

        // 设置是否添加显示NavigationIcon,这个方法是ActionBar的方法,Toolbar没有这个方法.
        // mainActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // 设置icon
        // toolbar.setNavigationIcon(R.drawable.ic_tab_home_normal);
        // icon和文字之间的间距
        toolbar.setContentInsetStartWithNavigation(0);
        // 设置监听,setSupportActionBar()会覆盖这个方法,所以必须在setSupportActionBar()之后调用
        // toolbar.setNavigationOnClickListener(v -> {});

        List<String> imageUrls = new ArrayList<>();
        Collections.addAll(imageUrls, Constants.BANNER_URLS);
        banner.setImages(imageUrls).setImageLoader(new GlideImageLoader()).setDelayTime(3000).start();

        // 获取当天日期
        String data = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String[] split = data.split("-");
        year = split[0];
        month = split[1];
        day = split[2];

        getData();
    }

    // 没请求到数据就一直请求前一天数据
    public void getData() {
        presenter.getDay(year, month, day);
    }

    @Override
    public void setDay(List<List<ItemBean>> list) {
        if (list.size() == 0) {
            // 得到上一天的时间
            Calendar calendar = Calendar.getInstance();
            //月份是从0开始的,所以11表示12月
            calendar.set(Integer.valueOf(year), Integer.valueOf(month) - 1, Integer.valueOf(day));

            // 使用roll方法进行向前回滚
            // calendar.roll(Calendar.DATE, -1);
            //使用set方法直接进行设置
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);

            year = String.valueOf(calendar.get(Calendar.YEAR));
            month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
            day = String.valueOf(calendar.get(Calendar.DATE));

            getData();
        } else {
            OneUtils.setRecyclerView(getContext(), recyclerView);
            recyclerView.setAdapter(new RecyclerViewAdapter<>(list, new TypeFactory().setHomeData(activity)));
        }
    }
}