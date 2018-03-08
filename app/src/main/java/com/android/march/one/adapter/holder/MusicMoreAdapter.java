package com.android.march.one.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.march.one.R;
import com.android.march.one.adapter.RecyclerViewAdapter;
import com.android.march.one.model.bean.MusicMoreItemBean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MusicMoreAdapter extends RecyclerViewAdapter.ViewHolder<MusicMoreItemBean> {

    @BindView(R.id.rlItem)
    RelativeLayout rlItem;
    @BindView(R.id.ivIcon)
    ImageView ivIcon;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    public MusicMoreAdapter(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    protected void bindViewHolder(RecyclerViewAdapter<MusicMoreItemBean> adapter, int position, MusicMoreItemBean musicMoreItemBean) {
        ivIcon.setImageResource(musicMoreItemBean.getImageId());
        tvTitle.setText(musicMoreItemBean.getTitle());
        rlItem.setOnClickListener(v -> {
            switch (position) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
            }
        });
    }
}
