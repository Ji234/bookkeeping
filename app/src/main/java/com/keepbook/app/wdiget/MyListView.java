package com.keepbook.app.wdiget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.keepbook.app.R;

//表格
public class MyListView extends ListView {
    public MyListView(Context context) {
        this(context,null);
    }

    public MyListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        View view = LayoutInflater.from(context).inflate(R.layout.item_bill_list, this, false);

        view.setBackgroundColor(Color.parseColor("#eeff41"));

        addHeaderView(view);
        Log.i("TAG",   "添加头部成功");
    }


//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        // TODO Auto-generated method stub
//        int hms = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
//                MeasureSpec.AT_MOST);
//        super.onMeasure(widthMeasureSpec, hms);
//    }

}
