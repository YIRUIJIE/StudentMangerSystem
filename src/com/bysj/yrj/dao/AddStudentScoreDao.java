package com.bysj.yrj.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.bysj.yrj.bean.StudentScore;
/**
 * 学生成绩添加表 相关
 * @author k01
 *
 */
public class AddStudentScoreDao {
	//成员变量
	MySqitHelper myhelper;
	SQLiteDatabase st;//
	String TB_NAME="AddScoretb";
	String COL1="_id";
	String COL2="num";
	String COL3="name";
	String COL4="android";
	String COL5="java";
	String COL6="html";
	//构造方法
		public AddStudentScoreDao(Context context){
			//创建打开数据库
			this.myhelper=new MySqitHelper(context);
			//连接，初始化管理员
			try {
				//当内存足够用读写的方式进行打开
				this.st=this.myhelper.getWritableDatabase();
			} catch (Exception e) {
				//当内存不足时就只用只读的方式进行打开
				this.st=this.myhelper.getReadableDatabase();
			}
			//创建表
			try {
				String sql="create table if not exists "+TB_NAME+"("+COL1+"   integer primary key autoincrement , "+
						COL2+"  varchar(10),"+COL3+"  varchar(10),"+COL4+"  varchar(10),"+COL5+"" +
								"  varchar(10),"+COL6+"  varchar(10))";
				//发送指令
				this.st.execSQL(sql);
			} catch (Exception e) {
				// 数据表 创建 异常
				Log.e("学生成绩表创建异常",e.toString());
			}
		}
/**
 * 添加学生成绩 
* @param num,name,sex,age,pro,mark---包装为一个 StudentScore对象
* @return long
 */
		public long addStudentScore(StudentScore tem){
			ContentValues values=new ContentValues();
			values.put(COL2, tem.getNum());
			values.put(COL3, tem.getName());
			values.put(COL4, tem.getAndroid());
			values.put(COL5, tem.getJava());
			values.put(COL6, tem.getHtml());
			//发送管理员指令
			long n=this.st.insert(TB_NAME, null, values);
			this.free();
			return n;
		}

		/**
		 * 查询添加的所有学生成绩  ，按所有进行查询
		 *@parma all
		 *@return ArrayList<StudentScore>
		 */
		public ArrayList<StudentScore> getallscoreData(){
			ArrayList<StudentScore> adata=new ArrayList<StudentScore>();
			//查询添加的学生成绩表中所有的学生成绩
			String sql="select * from  "+TB_NAME;
			
			//管理员 发送查寻指令 
			Cursor cursor=this.st.rawQuery(sql, null);
			//从结果中逐条 取出 相关记录
			while(cursor.moveToNext()){
				//取出当前记录
				int id=cursor.getInt(cursor.getColumnIndex(COL1));
				String num=cursor.getString(cursor.getColumnIndex(COL2));
				String name=cursor.getString(cursor.getColumnIndex(COL3));
				String android=cursor.getString(cursor.getColumnIndex(COL4));
				String java=cursor.getString(cursor.getColumnIndex(COL5));
				String html=cursor.getString(cursor.getColumnIndex(COL6));
				//
				StudentScore tem=new StudentScore();
				//
				tem.setId(id);
				tem.setNum(num);
				tem.setName(name);
				tem.setAndroid(android);
				tem.setJava(java);
				tem.setHtml(html);
				//
				adata.add(tem);
			}
			cursor.close();
			this.st.close();
			this.myhelper.close();
			return adata;
		}
		/**
		 * 查询添加的学生成绩  按学号进行查询
		 *@parma num1
		 *@return ArrayList<StudentScore>
		 */
		public ArrayList<StudentScore> getScorenumData(String num0){ 
			ArrayList<StudentScore> adata=new ArrayList<StudentScore>();
			//根据学号查询单个学生信息
			String sql="select * from  "+TB_NAME+"  where "+COL2+"=?";
			//管理员 发送查寻指令 
			Cursor cursor=this.st.rawQuery(sql, new  String[]{num0});
			//从结果中逐条 取出 相关记录
			if(cursor.moveToNext()){
				//查询成功取出当前记录
				int id=cursor.getInt(cursor.getColumnIndex(COL1));
				String num=cursor.getString(cursor.getColumnIndex(COL2));
				String name=cursor.getString(cursor.getColumnIndex(COL3));
				String android=cursor.getString(cursor.getColumnIndex(COL4));
				String java=cursor.getString(cursor.getColumnIndex(COL5));
				String html=cursor.getString(cursor.getColumnIndex(COL6));
				//
				StudentScore tem=new StudentScore();
				//
				tem.setId(id);
				tem.setNum(num);
				tem.setName(name);
				tem.setAndroid(android);
				tem.setJava(java);
				tem.setHtml(html);
				//
				adata.add(tem);
			}
			cursor.close();
			this.st.close();
			this.myhelper.close();
			return adata;
		}
		/**
		 * 根据 学号删除 对应的学生成绩
		 * @param num
		 * @return long
		 */
		public long deleteById(String num){
			long n=this.st.delete(TB_NAME, COL2+"=?", new String[]{num});
			this.st.close();
			this.myhelper.close();
			return n;
		}
		/**
		 * 学生成绩数据修改
		 * @param num,name,sex,age,pro,mark---包装为 StudentScore 类对象
		 * @return long
		 */
		public long updateById(StudentScore tem){
			ContentValues values=new ContentValues();
			//设置要进行 修改的字段值
			values.put(COL2, tem.getNum());
			values.put(COL3, tem.getName());
			values.put(COL4, tem.getAndroid());
			values.put(COL5, tem.getJava());
			values.put(COL6, tem.getHtml());
			//通过管理员 发指令                                                                              where 列 1=？             设置参数                  String.values（整形数） 将整形数 转换为字符串
			long n=this.st.update(TB_NAME, values, COL2+"=?", new String[]{  String.valueOf(  tem.getNum())  });
			this.free();
			return n;
		}

/**
* 释放
*/
		public void free(){
			this.st.close();
			this.myhelper.close();
		}
}