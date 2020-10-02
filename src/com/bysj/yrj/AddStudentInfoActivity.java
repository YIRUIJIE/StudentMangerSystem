package com.bysj.yrj;

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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class AddStudentInfoActivity extends Activity {
	//��Ա����
	EditText editnum,editname,editage,editmark;
	RadioButton radiomen,radiowomen;
	ArrayAdapter<String> proadapter;
	String[] proname={"�����Ӧ��","���������","�ƶ���������","WEBǰ�˿���"};
	Spinner pro;
	Button butok,butre;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_student_info);
		//����init()����
        this.init();
      //�ύ��ť�¼���Ӽ���
        this.butok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View V) {
				//�ύ��ť��ʱ�䴦�����
				addaction();
			}
		});
        //Ϊ��հ�ť�¼���Ӽ���
        this.butre.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				qingkongaction();
			}
		});
	}
	//��ʼ�������Ŀؼ�����
	public void init(){
    	this.editnum=(EditText) findViewById(R.id.addnumedit);
    	this.editname=(EditText) findViewById(R.id.addnameedit);
    	this.radiomen=(RadioButton) findViewById(R.id.addradioman);
    	this.radiowomen=(RadioButton) findViewById(R.id.addradiowoman);
    	this.editage=(EditText) findViewById(R.id.addageedit);
    	this.pro=(Spinner) findViewById(R.id.spinner1);
    	//����������--����Դ--��ʾ��ʽ
    	this.proadapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,this.proname);
    	//����spinpro ����Դ
    	this.pro.setAdapter(proadapter);
    	this.editmark=(EditText) findViewById(R.id.addmarkedit);
    	this.butok=(Button) findViewById(R.id.addbutton);
    	this.butre=(Button) findViewById(R.id.resbutton);
    	
    }
	//��Ӱ�ť�¼�������
       public void addaction(){
    			//1.��ȡ�û��������Ϣ
    			String num=this.editnum.getText().toString();
//    			//nnΪ���ж�
//    			double nn=Integer.parseInt(num);
    			String name=this.editname.getText().toString();
    			String sex="��";
        		if(this.radiowomen.isChecked()){
        			//
        			sex="Ů";
        		}
        		String age=this.editage.getText().toString();
        		String pro=this.pro.getSelectedItem().toString();
    			String mark=this.editmark.getText().toString();
    			//2.������� �洢���ѧ����Ϣ
    			StudentInfo tem=new StudentInfo();
    			tem.setNum(num);
    			tem.setName(name);
    			tem.setSex(sex);
    			tem.setAge(age);
    			tem.setPro(pro);
    			tem.setMark(mark);
    			AddStudentInfoDao adao=new AddStudentInfoDao(this);
    			long n=adao.addStudentInfo(tem);
    			//3.���ݽ����ʾ
    			String mes="ѧ����Ϣ���ʧ��";
    			if(n>0){
    				mes="ѧ����Ϣ��ӳɹ�";
    			}
    			Toast.makeText(this, mes, Toast.LENGTH_LONG).show();
    	}
       //��հ�ť�¼�������
       public void qingkongaction(){
    	   this.editnum.setText("");
    	   this.editname.setText("");
    	   //�����Ա�Ĭ��Ϊ�н���ѡ����ʾ
    	   this.radiomen.setChecked(true);
    	   this.editage.setText("");
    	   //��������ѡ����רҵΪѡ���ĵ�һ��
    	   this.pro.setSelection(0);
    	   this.editmark.setText("");
       }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_info, menu);
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
