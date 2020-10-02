package com.bysj.yrj;

import com.bysj.yrj.RepairStudentInfoActivity;
import com.bysj.yrj.R;
import com.bysj.yrj.bean.StudentInfo;
import com.bysj.yrj.dao.ComData;
import com.bysj.yrj.dao.AddStudentInfoDao;
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
import android.widget.TextView;
import android.widget.Toast;

public class RepairStudentInfoActivity extends Activity {
	//��Ա����
		TextView repaireditnum,repaireditname;
		EditText repaireditage,repaireditmark;
		RadioButton repairradiomen,repairradiowomen;
		ArrayAdapter<String> proadapter;
		String[] proname={"�����Ӧ��","���������","�ƶ���������","WEBǰ�˿���"};
		Spinner repairpro;
		Button butsave,butdel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_repair_student_info);

	//���÷��� ��ʼ�� �����ؼ�����
			this.init();
			//���÷���   ���������� ��ȡ���ݲ���ʾ
			this.showOldStudentData();
					//���水ť
					this.butsave.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							saveAction();
						}
					});
					//ɾ����ť
					this.butdel.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							deleteAction();
						}
					});
		}
		//��ʼ�� �����Ŀؼ� ����
		private void init(){
			this.repaireditnum=(TextView) findViewById(R.id.repairnumtext);
			this.repaireditname=(TextView) findViewById(R.id.repairnametext);
			this.repairradiomen=(RadioButton) findViewById(R.id.repairradiomen);
			this.repairradiowomen=(RadioButton) findViewById(R.id.repairradiowoman);
			this.repaireditage=(EditText) findViewById(R.id.repairageedit);
			this.repairpro=(Spinner) findViewById(R.id.spinner1);
			this.repaireditmark=(EditText) findViewById(R.id.repairmarkedit);
			this.butsave=(Button) findViewById(R.id.repairsavebutton);
			this.butdel=(Button) findViewById(R.id.repairdelbutton);
		}
		/**
		 * �ӹ��������� ��ȡ���ݲ���ʾ
		 * 
		 */
		private void showOldStudentData(){
			//��ȡ�洢������
			StudentInfo tem=ComData.item;
			//��ʾ��ȡ������
			this.repaireditnum.setText(tem.getNum());
			this.repaireditname.setText(tem.getName());
			
    		this.repaireditage.setText(tem.getAge());
    		if(tem.getSex().equals("��"))
    		{
    			repairradiomen.setChecked(true);
    		}
    		else
    		{
    			repairradiowomen.setChecked(true);
    		}
			int n=0;
			String pro=tem.getPro();
			if(pro.equals("�����Ӧ��")){
				n=0;
			}else if(pro.equals("���������")){
				n=1;
			}else if(pro.equals("�ƶ���������")){
				n=2;
			}else if(pro.equals("WEBǰ�˿���")){
				n=3;
			}else{
				n=4;
			}
			this.repairpro.setSelection(n);
			this.repaireditmark.setText(tem.getMark());
		}
		/**
		 * ɾ�� ѧ����Ϣ��ť�¼�����
		 */
		private void deleteAction(){
			String  num=ComData.item.getNum();
			//���÷��� ɾ�������Ϣ
			AddStudentInfoDao adao=new AddStudentInfoDao(this);
	    	long n=adao.deleteById(num);
	    	String mes="ѧ����Ϣɾ��ʧ��";
	    	if(n>0){
	    		mes="ѧ����Ϣɾ���ɹ�";
	    	}
	    	Toast.makeText(this, mes, Toast.LENGTH_LONG).show();
		}
		/**
		 * ����ѧ����Ϣ��ť�¼�����
		 */
		private void saveAction(){
	    	//1.��ȡ�û��������Ϣ���û��޸ĵ���Ϣ��
	    	String num=this.repaireditnum.getText().toString();
	    	String name=this.repaireditname.getText().toString();
			String sex="��";
    		if(this.repairradiowomen.isChecked()){
    			//
    			sex="Ů";
    		}
    		String age=this.repaireditage.getText().toString();
	    	String pro=this.repairpro.getSelectedItem().toString();
	    	String mark=this.repaireditmark.getText().toString();
	    	//�޸Ĺ������ݿ������Ϣ
	    	ComData.item.setNum(num);
	    	ComData.item.setName(name);
	    	ComData.item.setSex(sex);
	    	ComData.item.setAge(age);
	    	ComData.item.setPro(pro);
	    	ComData.item.setMark(mark);
	    	//���� ��ط��� �޸����ݿ�
	    	AddStudentInfoDao adao=new AddStudentInfoDao(this);
	    	long n=adao.updateById(ComData.item);
	    	//���ݽ����ʾ
	    	String mes="ѧ����Ϣ�޸�ʧ��";
	    	   if(n>0){
	    		   mes="ѧ����Ϣ�޸ĳɹ�";
	    	   }
	    	Toast.makeText(this, mes, Toast.LENGTH_LONG).show();
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.repair_student_info, menu);
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
