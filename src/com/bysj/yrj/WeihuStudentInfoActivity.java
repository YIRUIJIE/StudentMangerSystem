package com.bysj.yrj;

import java.util.ArrayList;
import com.bysj.yrj.RepairStudentInfoActivity;
import com.bysj.yrj.dao.ComData;
import com.bysj.yrj.bean.StudentInfo;
import com.bysj.yrj.dao.AddStudentInfoDao;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class WeihuStudentInfoActivity extends Activity {
	//��Ա����
		Button butshow;
		TextView numedit;
		ListView listshow;
		ArrayList<StudentInfo> adata;//�洢 ��ѯ�Ľ��
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weihu_student_info);
		//���÷������г�ʼ��
		this.init();
		//Ϊ����ѧ�Ž��в�ѯ��ť����¼�����
		this.butshow.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String num=numedit.getText().toString();
				shownumData(num);
			}
		});
//Ϊ listshow �ؼ���� ����
				this.listshow.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
							long arg3) {
						// TODO Auto-generated method stub
						listAction(arg2);
					}
				});
		
	}
	/*
	 * ��ʼ�������Ŀؼ�����
	 */
		private void init(){
			this.butshow=(Button) findViewById(R.id.showbutton);
			this.numedit=(TextView) findViewById(R.id.numedit);
			this.listshow=(ListView) findViewById(R.id.listView1);
		}
		/**
		 *����ѧ�Ų�ѯ����ѧ����Ϣ ���ݲ���ʾ
		 * @param type
		 */
		public void shownumData(String num){
			//1.���� ��ط���  ��ѯ����
			AddStudentInfoDao adao=new AddStudentInfoDao(this);
			this.adata=adao.getStudentnumData(num);
			//2.�����б�����Ҫ������Դ
			ArrayList<String> sdata=new ArrayList<String>();
			for(int i=0;i<this.adata.size();i++){
				//�Ӳֿ��� ��ȡ ��i�� ��¼
				StudentInfo tem=this.adata.get(i);
				//�����б� ÿ����ʾ����
				String str=tem.getNum()+"\t\t"+tem.getName()+"\t\t"+tem.getSex()+"\t\t"+tem.getAge()+"\t\t"+tem.getPro()+"\t\t"+tem.getMark();
				//�������Դ
				sdata.add(str);
			}
			//����Դ �� �б� ���š�
			ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, sdata);
			//�����б��������
			this.listshow.setAdapter(adapter);	
		}
		/**
		 * �û���� ��ѯ����ѧ����Ϣ�б� �¼�����
		 */
		private void listAction(int num){
		     //1.�������� ��ȡ�� num������
			StudentInfo tem=this.adata.get(num);
			//2.����ȡ�����ݴ洢������������   ---��ѧ����Ϣά������ ��ȡ
			ComData.item=tem;
			//3.��ת�� ��Ϣ�޸� ɾ������
			Intent intent=new Intent(this,RepairStudentInfoActivity.class);
			startActivity(intent);
		}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.weihu_student_info, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
