package com.android.march.one.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class RecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder<T>> {

    private List<T> list;
    private ITypeFactory typeFactory;

    public RecyclerViewAdapter(List<T> list, ITypeFactory typeFactory) {
        this.list = list;
        this.typeFactory = typeFactory;
    }

    @Override
    public int getItemViewType(int position) {
        return typeFactory.getViewType(list, position);
    }

    @Override
    public ViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        return typeFactory.createViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolder<T> holder, int position) {
        holder.bindViewHolder(this, position, list.get(position));
    }

    @Override
    public int getItemCount() {
        if (null == list) {
            return 0;
        }
        return list.size();
    }

    public static abstract class ViewHolder<T> extends RecyclerView.ViewHolder {

        private final View itemView;
        private final SparseArray<View> views;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.views = new SparseArray<>();
        }

        public View getItemView() {
            return itemView;
        }

        // 通过 viewId 获取 view
        public final View findViewById(int viewId) {
            View view = views.get(viewId);
            if (null == view) {
                view = itemView.findViewById(viewId);
                views.put(viewId, view);
            }
            return view;
        }

        protected abstract void bindViewHolder(RecyclerViewAdapter<T> adapter, int position, T t);
    }
}