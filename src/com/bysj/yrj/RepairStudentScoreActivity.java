package com.bysj.yrj;

import com.bysj.yrj.bean.StudentScore;
import com.bysj.yrj.dao.AddStudentScoreDao;
import com.bysj.yrj.dao.ComData;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RepairStudentScoreActivity extends Activity {
	TextView repairnum,repairname;
	EditText repandroid,repjava,rephtml;
	Button repbut,delbut;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_repair_student_score);
		//���÷��� ��ʼ�� �����ؼ�����
		this.init();
		//���÷���   ���������� ��ȡ���ݲ���ʾ
		this.showOldStudentData();
				//�����޸İ�ť
				this.repbut.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						saveAction();
					}
				});
				//ɾ���ɼ���ť
				this.delbut.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						deleteAction();
					}
				});
	}
	//��ʼ�� �����Ŀؼ� ����
	private void init(){
		this.repairnum=(TextView) findViewById(R.id.repscorenumtext);
		this.repairname=(TextView) findViewById(R.id.repscorenametext);
		this.repandroid=(EditText) findViewById(R.id.repscoreandroidtext);
		this.repjava=(EditText) findViewById(R.id.repscorejavatext);
		this.rephtml=(EditText) findViewById(R.id.repscorehtmltext);
		this.repbut=(Button) findViewById(R.id.repscorebutton);
		this.delbut=(Button) findViewById(R.id.repdelscorebutton);
	}
	/**
	 * �ӹ��������� ��ȡ���ݲ���ʾ
	 * 
	 */
	private void showOldStudentData(){
		//��ȡ�洢������
		StudentScore tem=ComData.stem;
		//��ʾ��ȡ������
		this.repairnum.setText(tem.getNum());
		this.repairname.setText(tem.getName());
		this.repandroid.setText(tem.getAndroid());
		this.repjava.setText(tem.getJava());
		this.rephtml.setText(tem.getHtml());
	}
	/**
	 * ɾ�� ѧ���ɼ���ť�¼�����
	 */
	private void deleteAction(){
		String  num=ComData.stem.getNum();
		//���÷��� ɾ�������Ϣ
		AddStudentScoreDao adao=new AddStudentScoreDao(this);
    	long n=adao.deleteById(num);
    	String mes="ѧ���ɼ�ɾ��ʧ��";
    	if(n>0){
    		mes="ѧ���ɼ�ɾ���ɹ�";
    	}
    	Toast.makeText(this, mes, Toast.LENGTH_LONG).show();
	}
	/**
	 * ����ѧ���ɼ���ť�¼�����
	 */
	private void saveAction(){
    	//1.��ȡ�û�����ĳɼ����û��޸ĵĳɼ���
    	String num=this.repairnum.getText().toString();
    	String name=this.repairname.getText().toString();
		String android=this.repandroid.getText().toString();
    	String java=this.repjava.getText().toString();
    	String html=this.rephtml.getText().toString();
    	//�޸Ĺ������ݿ���سɼ�
    	ComData.stem.setNum(num);
    	ComData.stem.setName(name);
    	ComData.stem.setAndroid(android);
    	ComData.stem.setJava(java);
    	ComData.stem.setHtml(html);
    	//���� ��ط��� �޸����ݿ�
    	AddStudentScoreDao adao=new AddStudentScoreDao(this);
    	long n=adao.updateById(ComData.stem);
    	//���ݽ����ʾ
    	String mes="ѧ���ɼ��޸�ʧ��";
    	   if(n>0){
    		   mes="ѧ���ɼ��޸ĳɹ�";
    	   }
    	Toast.makeText(this, mes, Toast.LENGTH_LONG).show();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.repair_student_score, menu);
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
