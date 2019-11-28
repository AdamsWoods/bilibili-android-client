package com.hotbitmapgg.bilibili.network.api;


import com.hotbitmapgg.bilibili.entity.news.NewsInfo;

import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface NewsService {
    @POST("getWangYiNews")
    Observable<NewsInfo> getNews();

}
