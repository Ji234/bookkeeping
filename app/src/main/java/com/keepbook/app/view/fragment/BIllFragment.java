package com.keepbook.app.view.fragment;

import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.keepbook.app.R;
import com.keepbook.app.model.vo.BillVO;
import com.keepbook.app.util.BookUtils;
import com.keepbook.app.util.SqlLiteUtils;
import com.keepbook.app.view.fragment.base.BaseFragment;
import com.keepbook.app.viewholder.BillListViewHolder;
import com.keepbook.app.viewmodel.DataModel;
import com.keepbook.app.wdiget.MyListView;
import com.xuexiang.xui.adapter.listview.XListAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * 账单Fragment
 */
public class BIllFragment extends BaseFragment {
    //饼图
    private PieChart pieChart;
    //列表头
    private MyListView billListview;
    //列表下面的内容
    private XListAdapter<BillVO> adapter;

    //主要页面
    public BIllFragment() {
        super(R.layout.fragment_bill);
    }

    @Override
    public boolean isDisplay() {
        boolean display = super.isDisplay();
//        Log.i("TAG", "display=" + display);
        return display;
    }

    @Override
    protected void init() {
        new ViewModelProvider(requireActivity()).get(DataModel.class).getData().observe(requireActivity(), new Observer<Map<String, BillVO>>() {
            @Override
            public void onChanged(Map<String, BillVO> stringBillVOMap) {
                adapter.setData(stringBillVOMap.values());
                initChart();
            }
        });
        initChart();
        adapter = new XListAdapter<BillVO>(getContext(), initData()) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                //账单数据
                BillListViewHolder viewHolder;

                BillVO item = getItem(position);
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_bill_list, parent, false);
                    viewHolder = new BillListViewHolder();
                    viewHolder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
                    viewHolder.tvPay = (TextView) convertView.findViewById(R.id.tv_pay);
                    viewHolder.tvCome = (TextView) convertView.findViewById(R.id.tv_come);
                    viewHolder.tvBalance = (TextView) convertView.findViewById(R.id.tv_balance);
                    convertView.setTag(viewHolder);
                } else {
                    viewHolder = (BillListViewHolder) convertView.getTag();
                }
                viewHolder.tvTime.setText(item.getTime());
                viewHolder.tvCome.setText(item.getCome() + "");
                viewHolder.tvPay.setText(item.getPay() + "");
                viewHolder.tvBalance.setText(item.getLeft() + "");
                return convertView;
            }
        };
        billListview.setAdapter(adapter);
    }

    //初始化 数据连接
    private Collection<BillVO> initData() {
        BookUtils bookUtils = new BookUtils(SqlLiteUtils.getInstance(getContext()));

        return bookUtils.dataToBillVO(bookUtils.readData()).values();

    }

    @SuppressWarnings("all")
    private void initChart() {

        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        BookUtils bookUtils = new BookUtils(SqlLiteUtils.getInstance(getContext()));

        Map<String, Float> map = bookUtils.payAndCome();
        if (!map.isEmpty()) {
            pieEntries.add(new PieEntry(Math.abs(map.get("come")), "收入"));
            pieEntries.add(new PieEntry(Math.abs(map.get("pay")), "支出"));
        } else {
            return;
        }


        //标题
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "");
        //设置颜色
        pieDataSet.setColors(Color.parseColor("#00e676"), Color.parseColor("#ff1744"));
        pieDataSet.setDrawValues(true);
        //间隔
        pieDataSet.setSliceSpace(1f);
        pieDataSet.setHighlightEnabled(true);




        pieDataSet.setValueTextColor(Color.parseColor("#ebeee8"));

        PieData pieData = new PieData(pieDataSet);


        //字体
        pieData.setValueTextSize(12f);
        //描述
        pieChart.setDescription(null);
//        pieChart.setCenterText("收入:支出");
        //中间不为空
        pieChart.setDrawHoleEnabled(false);

        //设置pieChart是否只显示饼图上百分比不显示文字
        pieChart.setDrawEntryLabels(true);
        //设置数据
        pieChart.setData(pieData);


        pieChart.invalidate();

    }

    private float dp2px(int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    @Override
    protected void initView(View view) {

        pieChart = (PieChart) view.findViewById(R.id.pie_chart);
        billListview = (MyListView) view.findViewById(R.id.bill_listview);
    }

    @Override
    public void onClick(View view) {

    }
}
