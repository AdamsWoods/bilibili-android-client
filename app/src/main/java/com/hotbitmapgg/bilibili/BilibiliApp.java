package com.hotbitmapgg.bilibili;

import android.app.Application;
import android.content.Context;

import com.didichuxing.doraemonkit.DoraemonKit;
import com.didichuxing.doraemonkit.kit.webdoor.WebDoorManager;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by hcc on 16/8/7 21:18
 * 100332338@qq.com
 * <p/>
 * 哔哩哔哩动画App
 */
public class BilibiliApp extends Application {

    public static BilibiliApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        init();
    }

    private void init() {
        //初始化Leak内存泄露检测工具
        LeakCanary.install(this);
        //初始化Stetho调试工具
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());

        //滴滴的DoKit工具
        DoraemonKit.install(this);
        // H5任意门功能需要，非必须
        DoraemonKit.setWebDoorCallback(new WebDoorManager.WebDoorCallback() {
            @Override
            public void overrideUrlLoading(Context context, String s) {
                // 使用自己的H5容器打开这个链接
            }
        });
    }

    public static BilibiliApp getInstance() {
        return mInstance;
    }

}
