package com.bysj.yrj.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.bysj.yrj.bean.StudentScore;
/**
 * ѧ���ɼ���ӱ� ���
 * @author k01
 *
 */
public class AddStudentScoreDao {
	//��Ա����
	MySqitHelper myhelper;
	SQLiteDatabase st;//
	String TB_NAME="AddScoretb";
	String COL1="_id";
	String COL2="num";
	String COL3="name";
	String COL4="android";
	String COL5="java";
	String COL6="html";
	//���췽��
		public AddStudentScoreDao(Context context){
			//���������ݿ�
			this.myhelper=new MySqitHelper(context);
			//���ӣ���ʼ������Ա
			try {
				//���ڴ��㹻�ö�д�ķ�ʽ���д�
				this.st=this.myhelper.getWritableDatabase();
			} catch (Exception e) {
				//���ڴ治��ʱ��ֻ��ֻ���ķ�ʽ���д�
				this.st=this.myhelper.getReadableDatabase();
			}
			//������
			try {
				String sql="create table if not exists "+TB_NAME+"("+COL1+"   integer primary key autoincrement , "+
						COL2+"  varchar(10),"+COL3+"  varchar(10),"+COL4+"  varchar(10),"+COL5+"" +
								"  varchar(10),"+COL6+"  varchar(10))";
				//����ָ��
				this.st.execSQL(sql);
			} catch (Exception e) {
				// ���ݱ� ���� �쳣
				Log.e("ѧ���ɼ������쳣",e.toString());
			}
		}
/**
 * ���ѧ���ɼ� 
* @param num,name,sex,age,pro,mark---��װΪһ�� StudentScore����
* @return long
 */
		public long addStudentScore(StudentScore tem){
			ContentValues values=new ContentValues();
			values.put(COL2, tem.getNum());
			values.put(COL3, tem.getName());
			values.put(COL4, tem.getAndroid());
			values.put(COL5, tem.getJava());
			values.put(COL6, tem.getHtml());
			//���͹���Աָ��
			long n=this.st.insert(TB_NAME, null, values);
			this.free();
			return n;
		}

		/**
		 * ��ѯ��ӵ�����ѧ���ɼ�  �������н��в�ѯ
		 *@parma all
		 *@return ArrayList<StudentScore>
		 */
		public ArrayList<StudentScore> getallscoreData(){
			ArrayList<StudentScore> adata=new ArrayList<StudentScore>();
			//��ѯ��ӵ�ѧ���ɼ��������е�ѧ���ɼ�
			String sql="select * from  "+TB_NAME;
			
			//����Ա ���Ͳ�Ѱָ�� 
			Cursor cursor=this.st.rawQuery(sql, null);
			//�ӽ�������� ȡ�� ��ؼ�¼
			while(cursor.moveToNext()){
				//ȡ����ǰ��¼
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
		 * ��ѯ��ӵ�ѧ���ɼ�  ��ѧ�Ž��в�ѯ
		 *@parma num1
		 *@return ArrayList<StudentScore>
		 */
		public ArrayList<StudentScore> getScorenumData(String num0){ 
			ArrayList<StudentScore> adata=new ArrayList<StudentScore>();
			//����ѧ�Ų�ѯ����ѧ����Ϣ
			String sql="select * from  "+TB_NAME+"  where "+COL2+"=?";
			//����Ա ���Ͳ�Ѱָ�� 
			Cursor cursor=this.st.rawQuery(sql, new  String[]{num0});
			//�ӽ�������� ȡ�� ��ؼ�¼
			if(cursor.moveToNext()){
				//��ѯ�ɹ�ȡ����ǰ��¼
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
		 * ���� ѧ��ɾ�� ��Ӧ��ѧ���ɼ�
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
		 * ѧ���ɼ������޸�
		 * @param num,name,sex,age,pro,mark---��װΪ StudentScore �����
		 * @return long
		 */
		public long updateById(StudentScore tem){
			ContentValues values=new ContentValues();
			//����Ҫ���� �޸ĵ��ֶ�ֵ
			values.put(COL2, tem.getNum());
			values.put(COL3, tem.getName());
			values.put(COL4, tem.getAndroid());
			values.put(COL5, tem.getJava());
			values.put(COL6, tem.getHtml());
			//ͨ������Ա ��ָ��                                                                              where �� 1=��             ���ò���                  String.values���������� �������� ת��Ϊ�ַ���
			long n=this.st.update(TB_NAME, values, COL2+"=?", new String[]{  String.valueOf(  tem.getNum())  });
			this.free();
			return n;
		}

/**
* �ͷ�
*/
		public void free(){
			this.st.close();
			this.myhelper.close();
		}
}