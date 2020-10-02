package com.bysj.yrj;

import com.bysj.yrj.R;
import com.bysj.yrj.dao.ComData;
import com.bysj.yrj.dao.UserDao;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PassRepairActivity extends Activity {
    //��Ա����
	EditText editpass1,editpass2,editpass3;
	Button butsave,butres;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pass_repair);
		//��ʼ�� ���÷���
		this.init();
		
		//���水ť �¼�����
		this.butsave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//���ñ��水ť�¼�����
				saveAction();
			}
		});
		
		//���ð�ť �¼����� 
		this.butres.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//���� ���ð�ť�¼� ����
				resAction();
			}
		});
		
	}
	/**
	 * ��ʼ��
	 */
	private void init(){
		this.editpass1=(EditText) findViewById(R.id.paeditpass1);
		this.editpass2=(EditText) findViewById(R.id.paeditpass2);
		this.editpass3=(EditText) findViewById(R.id.paeditpass3);
		this.butsave=(Button) findViewById(R.id.psbutsave);
		this.butres=(Button) findViewById(R.id.psbutres);
	}
	/**
	 *���水ť���¼�����
	 */
	public void saveAction(){
		//1.��ȡ�û������ݣ�ԭ���£��ظ���
		String pass1=this.editpass1.getText().toString();
		String pass2=this.editpass2.getText().toString();
		String pass3=this.editpass3.getText().toString();
		//Ϊ���ж�  ʡ��
		//2.�ж�ԭ���� �Ƿ���ȷ  pass1 ��½ ������  ComData.utem ��
		String pass=ComData.utem.getUserpass();
		if(!pass1.equals(pass)){
			Toast.makeText(this, "ԭ���������д����޸�", Toast.LENGTH_LONG).show();
			return ;
		}
		//3.�ж����� ���� �Ƿ�һ��
		if(!pass2.equals(pass3)){
			Toast.makeText(this, "�������벻һ�£����޸�", Toast.LENGTH_LONG).show();
			return ;
		}
		//4.������ط����޸����ݿ�
		UserDao udao=new UserDao(this);
		int id=ComData.utem.getId();
		long n=udao.repairPassById(id, pass2);
		//�޸��û����������  ����֤
		if(n>0){
			//�û������޸ĳɹ�
			Toast.makeText(this, "�����޸ĳɹ�", Toast.LENGTH_LONG).show();
			//�������ø��� �޸�����
			ComData.utem.setUserpass(pass2);
			return ;
		}else{
			//�û������޸�ʧ��
			Toast.makeText(this, "�����޸�ʧ��", Toast.LENGTH_LONG).show();
			return ;
		}
	}
	/**
	 *���ð�ť���¼�����
	 */
	public void resAction(){
		this.editpass1.setText("");
		this.editpass2.setText("");
		this.editpass3.setText("");
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pass_repair, menu);
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
