package com.bysj.yrj.dao;

import com.bysj.yrj.bean.UserInfo;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * 
 * 对用户有信息表的相关操作（创建 查询  添加 ）
 * @author k01
 *
 */
public class UserDao {
  //
	MySqitHelper myhelper;
	SQLiteDatabase st;//
	String TB_NAME="userinfo";
	String COL1="_id";
	String COL2="username";
	String COL3="userpass";
	//
	public UserDao(Context context){
		//打开数据库
		this.myhelper=new MySqitHelper(context);
		//连接数据库，--初始化 管理员
		try {
			// 数据库的读
			this.st=this.myhelper.getWritableDatabase();
		} catch (Exception e) {
			// 数据库的写
			this.st=this.myhelper.getReadableDatabase();
		}
		//表创建(判断表是否存在)、
		String sql="create table if not exists "+TB_NAME+"("+COL1+"    integer primary key autoincrement , "+
		COL2+"  vachar(20),"+COL3+"  varchar(20))";
		try {
			//通过管理员发送指令
			this.st.execSQL(sql);
		} catch (Exception e) {
			Log.e("user表创建异常",e.toString());
		}
	}
	/**
	 * 添加用户  
	 * @param username userpass
	 * @return long
	 */
	public long addUser(String username,String userpass){
		ContentValues valuse=new ContentValues();
		//
		valuse.put(COL2,username);
		valuse.put(COL3,userpass);
		//通过st发送指令
		long n=this.st.insert(TB_NAME,null,valuse);
		return n;
	}
	/**
	 * 根据用户名 和 密码 --判断用户合法性
	 * @param name,pass
	 * @return UserInfo
	 */
	public UserInfo checkUser(String name,String pass){
		UserInfo utem=null;
		String sql="select * from "+TB_NAME+" where "+COL2+"=?  and "+COL3+"=?";
		//发送 查询语句进行查询
		Cursor cursor=this.st.rawQuery(sql, new String[]{name,pass});
		//判断 结果集是否有数据
		if(cursor.moveToNext()){
			//取出当前记录
			int _id=cursor.getInt(cursor.getColumnIndex(COL1));
			//初始化  utem
			utem=new UserInfo(); //本句执行 完后 ，utem值不等于null
			//设置属性
			utem.setId(_id);
			utem.setUsername(name);
			utem.setUserpass(pass);
		}
		return utem;
	}
	/**
	 * 根据id修改对应的密码
	 * @param _id,pass
	 * @return long
	 */
	public long repairPassById(int id,String pass){
		ContentValues values=new ContentValues();
		values.put(COL3, pass);
		long n=this.st.update(TB_NAME, values, COL1+"=?", new String[]{String.valueOf(id)});
		return n;
	}
}
























































