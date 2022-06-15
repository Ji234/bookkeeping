package com.keepbook.app.util;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//使用数据库,创建表
public class SqlLiteUtils extends SQLiteOpenHelper {

    //指定数据库名称
    private static final String NAME = "KeepBook.db";
    //数据库对象
    private static SqlLiteUtils sqlLiteUtils;
    //版本
    private final static int VERSION = 1;
    //创建table
    private final String CREATE_TABLE = "create table keepbook(id integer primary key autoincrement,category text,remark text,money real,time text)";

    private SqlLiteUtils(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }


    private SqlLiteUtils(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static SqlLiteUtils getInstance(Context context) {
        if (sqlLiteUtils == null) {
            synchronized (SqlLiteUtils.class) {
                if (sqlLiteUtils == null) {
                    sqlLiteUtils = new SqlLiteUtils(context, NAME,null, VERSION);
                }
            }
        }
        return sqlLiteUtils;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //执行sql
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
