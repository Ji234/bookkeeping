package com.keepbook.app.util;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.keepbook.app.model.dto.KeepBookDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class BookUtils {

    private final SqlLiteUtils sqlLiteUtils;
    private final SQLiteDatabase writableDatabase;
    private final SQLiteDatabase readableDatabase;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);

    public BookUtils(SqlLiteUtils sqlLiteUtils) {
        this.sqlLiteUtils = sqlLiteUtils;
        writableDatabase = sqlLiteUtils.getWritableDatabase();
        readableDatabase = sqlLiteUtils.getReadableDatabase();
    }
    //
    public Long writeDataOfOne(KeepBookDTO bookDTO) {
        //创建对象装存入SQLit的数据
        ContentValues values = new ContentValues();
        values.put("category", bookDTO.getCategory());
        values.put("remark", bookDTO.getRemark());
        values.put("money", bookDTO.getMoney());

        Date date = bookDTO.getTime();
        String dateStr = simpleDateFormat.format(date);
        values.put("time", dateStr);
        return writableDatabase.insert(
                "keepbook", null, values
        );
    }

    public List<KeepBookDTO> readData() {
        ArrayList<KeepBookDTO> keepBookDTOS = new ArrayList<>();
        //从数据库中读取数据
        Cursor cursor = readableDatabase.query("keepbook", null, null, null, null, null, null);
        for (; cursor.moveToNext(); ) {
            int i1 = cursor.getColumnIndex("category");
            int i2 = cursor.getColumnIndex("remark");
            int i3 = cursor.getColumnIndex("money");
            int i4 = cursor.getColumnIndex("time");
            if (i1 >= 0 && i2 >= 0 && i3 >= 0 && i4 >= 0) {
                String category = cursor.getString(i1);
                String remark = cursor.getString(i2);
                double money = cursor.getDouble(i3);
                String time = cursor.getString(i4);
                KeepBookDTO keepBookDTO = null;
                try {
                    keepBookDTO = new KeepBookDTO(category, money, simpleDateFormat.parse(time), remark);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                keepBookDTOS.add(keepBookDTO);
            }

        }
        //返回keepBookDTOS对象
        return keepBookDTOS;
    }

    //将读取数据转换成map
    public Map<String, com.keepbook.app.model.vo.BillVO> dataToBillVO(List<KeepBookDTO> keepBookDTOS) {
        HashMap<String, com.keepbook.app.model.vo.BillVO> map = new HashMap<>();

        for (KeepBookDTO keepBookDTO : keepBookDTOS) {

            String time = simpleDateFormat.format(keepBookDTO.getTime());
            com.keepbook.app.model.vo.BillVO billVO = map.get(time);
            if (billVO == null) {
                billVO = new com.keepbook.app.model.vo.BillVO();
                map.put(time,billVO);
            }
            billVO.setTime(time);
            Double money = keepBookDTO.getMoney();
            if (money > 0) {

                billVO.setCome(billVO.getCome() + Math.abs(keepBookDTO.getMoney()));
            } else {
                billVO.setPay(billVO.getPay() + Math.abs(keepBookDTO.getMoney()));
            }
            billVO.setLeft(billVO.getCome() - billVO.getPay());


        }
        return map;
    }


    //算出总的收入和支出返回
    public Map<String,Float> payAndCome() {
        HashMap<String, Float> map = new HashMap<>();
        String sql = "select (select SUM(money) from keepbook where money>0) as come," +
                "(select SUM(money) from keepbook where money<0) as pay from keepbook ";
        Cursor cursor = readableDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int i1 = cursor.getColumnIndex("come");
            int i2 = cursor.getColumnIndex("pay");
            if (i1 >= 0 && i2 >= 0) {
                float come = cursor.getFloat(i1);
                float pay = cursor.getFloat(i2);
                map.put("come", come);
                map.put("pay", pay );
            }
        }
        return map;
    }
}
