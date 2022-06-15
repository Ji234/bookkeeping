package com.keepbook.app.view.listener;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationBarView;
import com.keepbook.app.R;


//实现记账和账单的切换
public class PageSelectListener implements NavigationBarView.OnItemSelectedListener {

    private final ViewPager mainViewpager;
    private  final Integer[] FRAGMENTS_ID = {R.id.record,R.id.bill};

    public PageSelectListener(ViewPager mainViewpager) {
        this.mainViewpager = mainViewpager;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        boolean flag = false;
        for (int i = 0; i < FRAGMENTS_ID.length; i++) {
            if (item.getItemId() == FRAGMENTS_ID[i]) {
                mainViewpager.setCurrentItem(i);
                flag = true;
                break;
            }
        }
        return flag;
    }
}
