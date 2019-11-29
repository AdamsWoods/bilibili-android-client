package com.hotbitmapgg.bilibili.network.api;


import com.hotbitmapgg.bilibili.entity.news.NewsInfo;

import retrofit2.Call;
import retrofit2.http.POST;
import rx.Observable;

public interface NewsService {

    @POST("getWangYiNews?page=1&count=5")
    Observable<NewsInfo> getNews();

    @POST("getWangYiNews?page=1&count=5")
    Call<String> getNews1();

//    Call<NewsInfo> getNews();

}
