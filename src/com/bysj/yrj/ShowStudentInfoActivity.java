package com.bysj.yrj;

import java.util.ArrayList;
import com.bysj.yrj.bean.StudentInfo;
import com.bysj.yrj.dao.AddStudentInfoDao;
import com.bysj.yrj.R;
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

public class ShowStudentInfoActivity extends Activity {
	//��Ա����
	Button butall,butshow;
	TextView numedit;
	ListView listshow;
	ArrayList<StudentInfo> adata;//�洢 ��ѯ�Ľ��
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_student_info);
		//���÷������г�ʼ��
		this.init();
		//showdata�ڷ�����ʼ��֮����ý���ֱ�ӽ�����ʾѧ������Ϣ
//		this.showData();
		//Ϊ��ѯ����ѧ����Ϣ��ť�¼���Ӽ���
		this.butall.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//showdata�ڰ�ť�����¼������ �����ѯ���в�ѯȫ��
				showData();
			}
		});
		//����ѧ�Ų�ѯ����ѧ����Ϣ��ť��Ӽ���
		this.butshow.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String num=numedit.getText().toString();
				shownumData(num);
			}
		});
	}
/*
 * ��ʼ�������Ŀؼ�����
 */
	private void init(){
		this.butall=(Button) findViewById(R.id.showallbutton);
		this.butshow=(Button) findViewById(R.id.showbutton);
		this.numedit=(TextView) findViewById(R.id.numedit);
		this.listshow=(ListView) findViewById(R.id.listView1);
	}
	/**
	 *��ѯ����ѧ����Ϣ ���ݱ���ʾ
	 * @param type
	 */
	private void showData(){
		//1.���� ��ط���  ��Ѱ����
		AddStudentInfoDao adao=new AddStudentInfoDao(this);
		this.adata=adao.getStudentData();
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_student_info, menu);
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
