package com.bysj.yrj;

import java.util.ArrayList;

import com.bysj.yrj.bean.StudentInfo;
import com.bysj.yrj.bean.StudentScore;
import com.bysj.yrj.dao.AddStudentInfoDao;
import com.bysj.yrj.dao.AddStudentScoreDao;

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

public class AddStudentScoreActivity extends Activity {
	//��Ա����
	TextView num,name;
	EditText numtext,androidtext,javatext,htmltext;
	Button showbut,addscorebut,resscorebut;
	ArrayList<StudentInfo> adata;//�洢 ��ѯ�Ľ��
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_student_score);
		//����init()����
		this.init();
		
		//Ϊ��ѧ�Ų�ѯ��ť����¼�����
		this.showbut.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String num=numtext.getText().toString();
				getStudentnumData(num);
			}
		});
		 //��Ӱ�ť�¼���Ӽ���
        this.addscorebut.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View V) {
				//��Ӱ�ť���¼��������
				addscoreaction();
			}
		});
        //��հ�ť�¼�������
        this.resscorebut.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				qingkongscoreaction();
			}
		});
	}
	//��ʼ�������Ŀؼ�����
	public void init(){
		this.numtext=(EditText) findViewById(R.id.numeditscore);
		this.num=(TextView) findViewById(R.id.addscorenumtext);
		this.name=(TextView) findViewById(R.id.addscorenametext);
		this.androidtext=(EditText) findViewById(R.id.addscoreandroidtext);
		this.javatext=(EditText) findViewById(R.id.addscorejavatext);
		this.htmltext=(EditText) findViewById(R.id.addscorehtmltext);
		this.showbut=(Button) findViewById(R.id.showbuttonscore);
		this.addscorebut=(Button) findViewById(R.id.addscorebutton);
		this.resscorebut=(Button) findViewById(R.id.resscorebutton);
	}
	/**
	 * ͨ�������ѧ��ѧ�Ž��в�ѯ ����ѧ����Ϣ���л�ȡ���ݲ���ʾ
	 * 
	 */
	public void getStudentnumData(String num){
		//1.���� ��ط���  ��ѯ����
		AddStudentInfoDao adao=new AddStudentInfoDao(this);
		this.adata=adao.getStudentnumData(num);
		//2.�����б�����Ҫ������Դ
		//ArrayList<String> sdata=new ArrayList<String>();
		for(int i=0;i<this.adata.size();i++){
			//�Ӳֿ��� ��ȡ ��i�� ��¼
			StudentInfo tem=this.adata.get(i);
			//�����б� ÿ����ʾ����
			String str=tem.getNum();
			String str1=tem.getName();
			//�������Դ
			 this.num.setText(str);
			 this.name.setText(str1);
		}
	}
	
	//��Ӱ�ť�¼�������
    public void addscoreaction(){
 			//��ȡ�û��������Ϣ
    		String num=this.num.getText().toString();
    		String name=this.name.getText().toString();
     		String android=this.androidtext.getText().toString();
     		String java=this.javatext.getText().toString();
 			String html=this.htmltext.getText().toString();
 			//������� �洢
 			StudentScore tem=new StudentScore();
 			tem.setNum(num);
 			tem.setName(name);
 			tem.setAndroid(android);
 			tem.setJava(java);
 			tem.setHtml(html);
 			AddStudentScoreDao adao=new AddStudentScoreDao(this);
 			long n=adao.addStudentScore(tem);
 			//3.���ݽ����ʾ
 			String mes="ѧ���ɼ����ʧ��";
 			if(n>0){
 				mes="ѧ���ɼ���ӳɹ�";
 			}
 			Toast.makeText(this, mes, Toast.LENGTH_LONG).show();  		
 	}
    //��հ�ť�¼�������
    public void qingkongscoreaction(){
    	this.numtext.setText("");
 	    this.num.setText("");
 	    this.name.setText("");
 	    this.androidtext.setText("");
 	    this.javatext.setText("");
 	    this.htmltext.setText("");
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_student_score, menu);
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
