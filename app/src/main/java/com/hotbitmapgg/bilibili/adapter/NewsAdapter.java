package com.hotbitmapgg.bilibili.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hotbitmapgg.bilibili.adapter.helper.AbsRecyclerViewAdapter;
import com.hotbitmapgg.bilibili.entity.news.NewsInfo;
import com.hotbitmapgg.ohmybilibili.R;

import java.util.List;

public class NewsAdapter extends AbsRecyclerViewAdapter {

    private List<NewsInfo.ResultBean> infoList;

    public NewsAdapter(RecyclerView recyclerView, List<NewsInfo.ResultBean> infoList){
        super(recyclerView);
        this.infoList = infoList;
    }

//    public void setInfoList(List<NewsInfo.ResultBean> infoList) {
//        this.infoList = infoList;
//    }

    @NonNull
    @Override
    public ClickableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        bindContext(parent.getContext());
        return new ItemViewHolder(LayoutInflater.from(getContext())
                .inflate(R.layout.item_news_index, parent, false));
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            NewsInfo.ResultBean newsInfo = infoList.get(position);

            Glide.with(getContext())
                    .load(newsInfo.getImage())
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.bili_default_image_tv)
                    .dontAnimate()
                    .into(itemViewHolder.iv_background);

            itemViewHolder.tv_title.setText(newsInfo.getTitle());
            itemViewHolder.tv_passtime.setText(newsInfo.getPasstime());
        }
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }

    private class ItemViewHolder extends AbsRecyclerViewAdapter.ClickableViewHolder {

        TextView tv_title;
        ImageView iv_background;
        TextView tv_passtime;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_title = $(R.id.tv_title);
            iv_background = $(R.id.iv_background);
            tv_passtime = $(R.id.tv_passtime);
        }
    }
}
