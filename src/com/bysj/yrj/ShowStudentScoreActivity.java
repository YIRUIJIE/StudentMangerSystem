package com.bysj.yrj;

import java.util.ArrayList;
import com.bysj.yrj.bean.StudentScore;
import com.bysj.yrj.dao.AddStudentScoreDao;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ShowStudentScoreActivity extends Activity {
	//��Ա����
	Button butscoreall,butscoreshow;
	TextView scoreedit;
	ListView listshow;
	ArrayList<StudentScore> adata;//�洢 ��ѯ�Ľ��
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_student_score);
		//���÷������г�ʼ��
				this.init();
				//showdata�ڷ�����ʼ��֮����ý���ֱ�ӽ�����ʾѧ������Ϣ
//				this.showData();
				//Ϊ��ѯ����ѧ����Ϣ��ť�¼���Ӽ���
				this.butscoreall.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						//showdata�ڰ�ť�����¼������ �����ѯ���в�ѯȫ��
						showscoreData();
					}
				});
				//����ѧ�Ų�ѯ����ѧ����Ϣ��ť��Ӽ���
				this.butscoreshow.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String num=scoreedit.getText().toString();
						shownumscoreData(num);
					}
				});
	}
	/*
	 * ��ʼ�������Ŀؼ�����
	 */
		private void init(){
			this.butscoreall=(Button) findViewById(R.id.showallbutton);
			this.butscoreshow=(Button) findViewById(R.id.showscorebutton);
			this.scoreedit=(TextView) findViewById(R.id.numscoreedit);
			this.listshow=(ListView) findViewById(R.id.listView11);
		}
		/**
		 *��ѯ����ѧ���ɼ����ݱ���ʾ��ListView
		 * @param type
		 */
		private void showscoreData(){
			//1.���� ��ط���  ��Ѱ����
			AddStudentScoreDao adao=new AddStudentScoreDao(this);
			this.adata=adao.getallscoreData();
			//2.�����б�����Ҫ������Դ
			ArrayList<String> sdata=new ArrayList<String>();
			for(int i=0;i<this.adata.size();i++){
				//�Ӳֿ��� ��ȡ ��i�� ��¼
				StudentScore tem=this.adata.get(i);
				//�����б� ÿ����ʾ����
				String str=tem.getNum()+"\t\t"+tem.getName()+"\t\t"+tem.getAndroid()+"\t\t"+tem.getJava()+"\t\t"+tem.getHtml();
				//�������Դ
				sdata.add(str);
			}
			//����Դ �� �б� ���š�
			ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, sdata);
			//�����б��������
			this.listshow.setAdapter(adapter);	
		}
		/**
		 *����ѧ�Ų�ѯ����ѧ���ɼ� ���ݲ���ʾ��ListView
		 * @param type
		 */
		public void shownumscoreData(String num){
			//1.���� ��ط���  ��ѯ����
			AddStudentScoreDao adao=new AddStudentScoreDao(this);
			this.adata=adao.getScorenumData(num);
			//2.�����б�����Ҫ������Դ
			ArrayList<String> sdata=new ArrayList<String>();
			for(int i=0;i<this.adata.size();i++){
				//�Ӳֿ��� ��ȡ ��i�� ��¼
				StudentScore tem=this.adata.get(i);
				//�����б� ÿ����ʾ����
				String str=tem.getNum()+"\t\t"+tem.getName()+"\t\t"+tem.getAndroid()+"\t\t"+tem.getJava()+"\t\t"+tem.getHtml();
				//�������Դ
				sdata.add(str);
			}
			//����Դ �� �б� ���š�
			ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, sdata);
			//�����б��������
			this.listshow.setAdapter(adapter);	
		}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_student_score, menu);
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
