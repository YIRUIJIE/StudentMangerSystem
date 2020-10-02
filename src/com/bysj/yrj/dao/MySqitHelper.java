package com.bysj.yrj.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MySqitHelper extends SQLiteOpenHelper {
    //
	static String DB_NAME="studentsystem.db";
	static int version=1;
	//构造方法 
	public MySqitHelper(Context context){
		super(context,DB_NAME,null,version);
	}
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
