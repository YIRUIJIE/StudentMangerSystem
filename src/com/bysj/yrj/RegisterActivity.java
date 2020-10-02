package com.bysj.yrj;

import com.bysj.yrj.dao.UserDao;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
    //��Ա����
	EditText editname,editpass1,editpass2;
	Button butsave;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		//���÷�����ʼ��
		this.init();
		//���水ť����¼�����
		this.butsave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//���ñ��� ���� -- saveAction
				saveAction();
			}
		});
	}
	/**
	 * 
	 * ��ʼ��
	 */
	private void init(){
		this.editname=(EditText) findViewById(R.id.regeditname);
		this.editpass1=(EditText) findViewById(R.id.regditpass1);
		this.editpass2=(EditText) findViewById(R.id.regditpass2);
		this.butsave=(Button) findViewById(R.id.regbutsave);
	}
	/**
	 * ���水ť�����¼�������
	 */
	public void saveAction(){
		        //1.��ȡ
				String name=this.editname.getText().toString();
				String pass1=this.editpass1.getText().toString();
				String pass2=this.editpass2.getText().toString();
				//2.�ж������Ƿ�Ϊ��
				if(name.length()==0||pass1.length()==0||pass2.length()==0){
					Toast.makeText(this,"�������ݲ����������޸�",Toast.LENGTH_LONG).show();
					//����
					return;
				}
				//3.�ж����������Ƿ�һ��
				if(!pass1.equals(pass2)){
					//�������벻һ��
					Toast.makeText(this,"�������벻һ�£����޸�",Toast.LENGTH_LONG).show();
					//����
					return;
				}
				//4.������ط��� �޸� ���ݿ�����
				UserDao udao=new UserDao(this);
				long n=udao.addUser(name, pass1);
				if(n>0){
					//��� --ע��ɹ�
					Toast.makeText(this,"���û�ע��ɹ�",Toast.LENGTH_LONG).show();
//					//�����û�ע����������ת���û��ĵ�¼���� 
//					Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
//		    		startActivity(intent);
//		    		//��ת�� �����������û�ע�����
//		    		this.finish();
				}else{
					//���--ע��ʧ��
					Toast.makeText(this,"���û�ע��ʧ��",Toast.LENGTH_LONG).show();
				}
	}
	/**
	 * ���ð�ť�����¼�������
	 */
	public void resetAction(){
		this.editname.setText("");
		this.editpass1.setText("");
		this.editpass2.setText("");
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_exit) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
