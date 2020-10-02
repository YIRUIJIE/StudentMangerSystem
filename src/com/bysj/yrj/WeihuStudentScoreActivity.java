package com.bysj.yrj;

import java.util.ArrayList;
import com.bysj.yrj.bean.StudentScore;
import com.bysj.yrj.dao.AddStudentScoreDao;
import com.bysj.yrj.dao.ComData;
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

public class WeihuStudentScoreActivity extends Activity {
	//��Ա����
	Button weihuscorebut;
	TextView weihuscoreedit;
	ListView listView;
	ArrayList<StudentScore> adata;//�洢 ��ѯ�Ľ��
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weihu_student_score);
		//���÷������г�ʼ��
				this.init();
				//Ϊ����ѧ�Ž��в�ѯ��ť����¼�����
				this.weihuscorebut.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String num=weihuscoreedit.getText().toString();
						shownumscoreData(num);
					}
				});
		//Ϊ listshow �ؼ���� ����
						this.listView.setOnItemClickListener(new OnItemClickListener() {

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
					this.weihuscorebut=(Button) findViewById(R.id.showscorebutton);
					this.weihuscoreedit=(TextView) findViewById(R.id.numscoreedit);
					this.listView=(ListView) findViewById(R.id.listView12);
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
					this.listView.setAdapter(adapter);	
				}
				/**
				 * �û���� ��ѯ����ѧ���ɼ��б� �¼�����
				 */
				private void listAction(int num){
				     //1.�������� ��ȡ�� num������
					StudentScore tem=this.adata.get(num);
					//2.����ȡ�����ݴ洢������������   ---��ѧ���ɼ�ά������ ��ȡ
					ComData.stem=tem;
					//3.��ת�� �ɼ��޸� ɾ������
					Intent intent=new Intent(this,RepairStudentScoreActivity.class);
					startActivity(intent);
				}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.weihu_student_score, menu);
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
