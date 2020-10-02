package com.bysj.yrj.dao;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.bysj.yrj.bean.StudentInfo;
import com.bysj.yrj.dao.MySqitHelper;
/**
 * 学生信息添加表 相关
 * @author k01
 *
 */
public class AddStudentInfoDao {
	//成员变量
	MySqitHelper myhelper;//数据库的连接
	SQLiteDatabase st;//数据库管理员
	String TB_NAME="AddInfotb";//学生的信息表
	String COL1="_id";
	String COL2="num";
	String COL3="name";
	String COL4="sex";
	String COL5="age";
	String COL6="pro";
	String COL7="mark";
	//构造方法
		public AddStudentInfoDao(Context context){
			//创建打开数据库
			this.myhelper=new MySqitHelper(context);
			//连接，初始化管理员
			try {
				this.st=this.myhelper.getWritableDatabase();
			} catch (Exception e) {
				this.st=this.myhelper.getReadableDatabase();
			}
			//创建表
			try {
				String sql="create table if not exists "+TB_NAME+"("+COL1+"   integer primary key autoincrement , "+
						COL2+"  varchar(10),"+COL3+"  varchar(10),"+COL4+"  varchar(10),"+COL5+"" +
								"  varchar(10),"+COL6+"  varchar(100) ,"+COL7+"  varchar(200) )";
				//发送指令
				this.st.execSQL(sql);
			} catch (Exception e) {
				// 数据表 创建 异常
				Log.e("学生信息表创建异常",e.toString());
			}
		}
/**
 * 添加学生信息 
* @param num,name,sex,age,pro,mark---包装为一个 StudentInfo对象
* @return long
 */
		public long addStudentInfo(StudentInfo tem){
			ContentValues values=new ContentValues();
			values.put(COL2, tem.getNum());
			values.put(COL3, tem.getName());
			values.put(COL4, tem.getSex());
			values.put(COL5, tem.getAge());
			values.put(COL6, tem.getPro());
			values.put(COL7, tem.getMark());
			//发送管理员指令
			long n=this.st.insert(TB_NAME, null, values);
			this.free();
			return n;
		}

		/**
		 * 查询添加的所有学生信息  ，按所有进行查询
		 *@parma all
		 *@return ArrayList<StudentInfo>
		 */
		public ArrayList<StudentInfo> getStudentData(){
			//初始化一个对象 
			ArrayList<StudentInfo> adata=new ArrayList<StudentInfo>();
			//查询添加的学生信息表中所有的学生信息
			String sql="select * from  "+TB_NAME;
			
			//管理员 发送查寻指令 
			Cursor cursor=this.st.rawQuery(sql, null);
			//从结果中逐条 取出 相关记录
			while(cursor.moveToNext()){
				//取出当前记录
				int id=cursor.getInt(cursor.getColumnIndex(COL1));
				String num=cursor.getString(cursor.getColumnIndex(COL2));
				String name=cursor.getString(cursor.getColumnIndex(COL3));
				String sex=cursor.getString(cursor.getColumnIndex(COL4));
				String age=cursor.getString(cursor.getColumnIndex(COL5));
				String pro=cursor.getString(cursor.getColumnIndex(COL6));
				String mark=cursor.getString(cursor.getColumnIndex(COL7));
				//创建一个对象，进行初始化学生信息的相关bean
				StudentInfo tem=new StudentInfo();
				//用初始化的字段进行设置 相关的信息
				tem.setId(id);
				tem.setNum(num);
				tem.setName(name);
				tem.setSex(sex);
				tem.setAge(age);
				tem.setPro(pro);
				tem.setMark(mark);
				//将相关的数据进行添加到初始化的学生信息表的相关存储中...
				adata.add(tem);
			}
			cursor.close();//关闭查询
			this.st.close();//关闭管理员命令
			this.myhelper.close();//关闭数据库连接
			return adata;//
		}
		/**
		 * 查询添加的学生信息  按学号进行查询
		 *@parma num0
		 *@return ArrayList<StudentInfo>
		 */
		public ArrayList<StudentInfo> getStudentnumData(String num0){ 
			ArrayList<StudentInfo> adata=new ArrayList<StudentInfo>();
			//根据用户输入的学号查询单个学生信息
			String sql="select * from  "+TB_NAME+"  where "+COL2+"=?";
			//管理员 发送查寻指令 
			Cursor cursor=this.st.rawQuery(sql, new  String[]{num0});
			//从结果中逐条 取出 相关记录
			if(cursor.moveToNext()){
				//查询成功取出当前记录
				int id=cursor.getInt(cursor.getColumnIndex(COL1));
				String num=cursor.getString(cursor.getColumnIndex(COL2));
				String name=cursor.getString(cursor.getColumnIndex(COL3));
				String sex=cursor.getString(cursor.getColumnIndex(COL4));
				String age=cursor.getString(cursor.getColumnIndex(COL5));
				String pro=cursor.getString(cursor.getColumnIndex(COL6));
				String mark=cursor.getString(cursor.getColumnIndex(COL7));
				//
				StudentInfo tem=new StudentInfo();
				//
				tem.setId(id);
				tem.setNum(num);
				tem.setName(name);
				tem.setSex(sex);
				tem.setAge(age);
				tem.setPro(pro);
				tem.setMark(mark);
				//
				adata.add(tem);
			}
			cursor.close();
			this.st.close();
			this.myhelper.close();
			return adata;
		}
		/**
		 * 根据 学号删除 对应的学生信息
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
		 * 学生信息数据修改
		 * @param num,name,sex,age,pro,mark---包装为 StudentInfo 类对象
		 * @return long
		 */
		public long updateById(StudentInfo tem){
			ContentValues values=new ContentValues();
			//设置要进行 修改的字段值
			values.put(COL2, tem.getNum());
			values.put(COL3, tem.getName());
			values.put(COL4, tem.getSex());
			values.put(COL5, tem.getAge());
			values.put(COL6, tem.getPro());
			values.put(COL7, tem.getMark());
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
		public  static void main(String args[]){
//			AddStudentInfoDao adao=new AddStudentInfoDao();
		}
}
