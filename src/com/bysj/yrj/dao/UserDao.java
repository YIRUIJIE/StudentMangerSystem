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
 * ���û�����Ϣ�����ز��������� ��ѯ  ��� ��
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
		//�����ݿ�
		this.myhelper=new MySqitHelper(context);
		//�������ݿ⣬--��ʼ�� ����Ա
		try {
			// ���ݿ�Ķ�
			this.st=this.myhelper.getWritableDatabase();
		} catch (Exception e) {
			// ���ݿ��д
			this.st=this.myhelper.getReadableDatabase();
		}
		//����(�жϱ��Ƿ����)��
		String sql="create table if not exists "+TB_NAME+"("+COL1+"    integer primary key autoincrement , "+
		COL2+"  vachar(20),"+COL3+"  varchar(20))";
		try {
			//ͨ������Ա����ָ��
			this.st.execSQL(sql);
		} catch (Exception e) {
			Log.e("user�����쳣",e.toString());
		}
	}
	/**
	 * ����û�  
	 * @param username userpass
	 * @return long
	 */
	public long addUser(String username,String userpass){
		ContentValues valuse=new ContentValues();
		//
		valuse.put(COL2,username);
		valuse.put(COL3,userpass);
		//ͨ��st����ָ��
		long n=this.st.insert(TB_NAME,null,valuse);
		return n;
	}
	/**
	 * �����û��� �� ���� --�ж��û��Ϸ���
	 * @param name,pass
	 * @return UserInfo
	 */
	public UserInfo checkUser(String name,String pass){
		UserInfo utem=null;
		String sql="select * from "+TB_NAME+" where "+COL2+"=?  and "+COL3+"=?";
		//���� ��ѯ�����в�ѯ
		Cursor cursor=this.st.rawQuery(sql, new String[]{name,pass});
		//�ж� ������Ƿ�������
		if(cursor.moveToNext()){
			//ȡ����ǰ��¼
			int _id=cursor.getInt(cursor.getColumnIndex(COL1));
			//��ʼ��  utem
			utem=new UserInfo(); //����ִ�� ��� ��utemֵ������null
			//��������
			utem.setId(_id);
			utem.setUsername(name);
			utem.setUserpass(pass);
		}
		return utem;
	}
	/**
	 * ����id�޸Ķ�Ӧ������
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
























































