package com.keepbook.app.view;

import android.content.Intent;

import com.keepbook.app.R;
import com.keepbook.app.view.base.BaseActivity;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

//创建开屏内容,先加载GuideActivity,在GuideActivity中启动MainActivity

public class GuideActivity extends BaseActivity {
    @Override
    protected void initView() {

    }

    //初始化
    @Override
    protected void init() {

        new Timer().schedule(
//                下面是任务
                new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                在一个Activity当中启动另外一个ACtivity
                                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                                finish();
                            }
                        });
                    }
                },
//                时间为当前时间
                new Date(System.currentTimeMillis()));


    }

//    设置layout
    @Override
    protected int initLayout() {
        return R.layout.activity_guide;
    }
}
