package com.imooc.liulinpeng.imocctantan;

import android.app.Application;

/**
 * Created by george on 2017/7/31.
 */

public class TanTanApplition extends Application {
    private static TanTanApplition sTanTanApplition;

    @Override
    public void onCreate() {
        super.onCreate();
        sTanTanApplition = this;
    }

    public static TanTanApplition getInstance() {
        return sTanTanApplition;
    }
}
