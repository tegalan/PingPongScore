package com.pringstudio.pingpong;

import android.app.Application;

import com.orhanobut.hawk.Hawk;

/**
 * Author       : sucipto
 * Created date : 1/25/17 11:39
 * Github       : @showcheap
 * Website      : http://sucipto.net
 */

public class PingPongApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Hawk.init(this).build();
    }
}
