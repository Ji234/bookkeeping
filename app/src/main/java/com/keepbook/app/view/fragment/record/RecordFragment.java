package com.keepbook.app.view.fragment.record;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.keepbook.app.R;
import com.keepbook.app.view.fragment.base.BaseFragment;
import com.xuexiang.xui.adapter.FragmentAdapter;
import com.xuexiang.xui.widget.tabbar.TabControlView;
import com.xuexiang.xui.widget.toast.XToast;

import java.util.ArrayList;
import java.util.List;

public class RecordFragment extends BaseFragment {


    private TabControlView tabControlView;
    private ViewPager recordViewPager;

    public RecordFragment() {
        super(R.layout.fragment_record);

    }

    protected void initView(View view) {
        tabControlView = (TabControlView) view.findViewById(R.id.tab_control_view);
        recordViewPager = (ViewPager) view.findViewById(R.id.record_view_pager);
        recordViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tabControlView.setSelectionTitle(position == 0 ? "支出" : "收入");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        try {
            tabControlView.setDefaultSelection(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void init() {
        List<Fragment> fragments = initFragments();

        recordViewPager.setAdapter(new FragmentAdapter<Fragment>(getActivity().getSupportFragmentManager(), fragments));
        tabControlView.setOnTabSelectionChangedListener(new TabControlView.OnTabSelectionChangedListener() {
            @Override
            public void newSelection(String title, String value) {
                switch (value) {
//                        支出
                    case "OUT":
                        recordViewPager.setCurrentItem(0);
                        XToast.success(getContext(), "支出").show();
                        break;
//                        收入
                    case "IN":
                        recordViewPager.setCurrentItem(1);
                        XToast.success(getContext(), "收入").show();
                        break;
                }
            }
        });
    }

    /**
     * 初始化 收入和支出 片段
     * @return
     */
    private List<Fragment> initFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new PayFragment());
        fragments.add(new ComeInFragment());
        return fragments;
    }

    @Override
    public void onClick(View view) {

    }
}
