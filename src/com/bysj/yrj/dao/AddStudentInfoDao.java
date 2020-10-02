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
 * ѧ����Ϣ��ӱ� ���
 * @author k01
 *
 */
public class AddStudentInfoDao {
	//��Ա����
	MySqitHelper myhelper;//���ݿ������
	SQLiteDatabase st;//���ݿ����Ա
	String TB_NAME="AddInfotb";//ѧ������Ϣ��
	String COL1="_id";
	String COL2="num";
	String COL3="name";
	String COL4="sex";
	String COL5="age";
	String COL6="pro";
	String COL7="mark";
	//���췽��
		public AddStudentInfoDao(Context context){
			//���������ݿ�
			this.myhelper=new MySqitHelper(context);
			//���ӣ���ʼ������Ա
			try {
				this.st=this.myhelper.getWritableDatabase();
			} catch (Exception e) {
				this.st=this.myhelper.getReadableDatabase();
			}
			//������
			try {
				String sql="create table if not exists "+TB_NAME+"("+COL1+"   integer primary key autoincrement , "+
						COL2+"  varchar(10),"+COL3+"  varchar(10),"+COL4+"  varchar(10),"+COL5+"" +
								"  varchar(10),"+COL6+"  varchar(100) ,"+COL7+"  varchar(200) )";
				//����ָ��
				this.st.execSQL(sql);
			} catch (Exception e) {
				// ���ݱ� ���� �쳣
				Log.e("ѧ����Ϣ�����쳣",e.toString());
			}
		}
/**
 * ���ѧ����Ϣ 
* @param num,name,sex,age,pro,mark---��װΪһ�� StudentInfo����
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
			//���͹���Աָ��
			long n=this.st.insert(TB_NAME, null, values);
			this.free();
			return n;
		}

		/**
		 * ��ѯ��ӵ�����ѧ����Ϣ  �������н��в�ѯ
		 *@parma all
		 *@return ArrayList<StudentInfo>
		 */
		public ArrayList<StudentInfo> getStudentData(){
			//��ʼ��һ������ 
			ArrayList<StudentInfo> adata=new ArrayList<StudentInfo>();
			//��ѯ��ӵ�ѧ����Ϣ�������е�ѧ����Ϣ
			String sql="select * from  "+TB_NAME;
			
			//����Ա ���Ͳ�Ѱָ�� 
			Cursor cursor=this.st.rawQuery(sql, null);
			//�ӽ�������� ȡ�� ��ؼ�¼
			while(cursor.moveToNext()){
				//ȡ����ǰ��¼
				int id=cursor.getInt(cursor.getColumnIndex(COL1));
				String num=cursor.getString(cursor.getColumnIndex(COL2));
				String name=cursor.getString(cursor.getColumnIndex(COL3));
				String sex=cursor.getString(cursor.getColumnIndex(COL4));
				String age=cursor.getString(cursor.getColumnIndex(COL5));
				String pro=cursor.getString(cursor.getColumnIndex(COL6));
				String mark=cursor.getString(cursor.getColumnIndex(COL7));
				//����һ�����󣬽��г�ʼ��ѧ����Ϣ�����bean
				StudentInfo tem=new StudentInfo();
				//�ó�ʼ�����ֶν������� ��ص���Ϣ
				tem.setId(id);
				tem.setNum(num);
				tem.setName(name);
				tem.setSex(sex);
				tem.setAge(age);
				tem.setPro(pro);
				tem.setMark(mark);
				//����ص����ݽ�����ӵ���ʼ����ѧ����Ϣ�����ش洢��...
				adata.add(tem);
			}
			cursor.close();//�رղ�ѯ
			this.st.close();//�رչ���Ա����
			this.myhelper.close();//�ر����ݿ�����
			return adata;//
		}
		/**
		 * ��ѯ��ӵ�ѧ����Ϣ  ��ѧ�Ž��в�ѯ
		 *@parma num0
		 *@return ArrayList<StudentInfo>
		 */
		public ArrayList<StudentInfo> getStudentnumData(String num0){ 
			ArrayList<StudentInfo> adata=new ArrayList<StudentInfo>();
			//�����û������ѧ�Ų�ѯ����ѧ����Ϣ
			String sql="select * from  "+TB_NAME+"  where "+COL2+"=?";
			//����Ա ���Ͳ�Ѱָ�� 
			Cursor cursor=this.st.rawQuery(sql, new  String[]{num0});
			//�ӽ�������� ȡ�� ��ؼ�¼
			if(cursor.moveToNext()){
				//��ѯ�ɹ�ȡ����ǰ��¼
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
		 * ���� ѧ��ɾ�� ��Ӧ��ѧ����Ϣ
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
		 * ѧ����Ϣ�����޸�
		 * @param num,name,sex,age,pro,mark---��װΪ StudentInfo �����
		 * @return long
		 */
		public long updateById(StudentInfo tem){
			ContentValues values=new ContentValues();
			//����Ҫ���� �޸ĵ��ֶ�ֵ
			values.put(COL2, tem.getNum());
			values.put(COL3, tem.getName());
			values.put(COL4, tem.getSex());
			values.put(COL5, tem.getAge());
			values.put(COL6, tem.getPro());
			values.put(COL7, tem.getMark());
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
		public  static void main(String args[]){
//			AddStudentInfoDao adao=new AddStudentInfoDao();
		}
}
