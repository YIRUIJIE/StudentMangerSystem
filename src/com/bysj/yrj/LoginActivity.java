package com.bysj.yrj;

import com.bysj.yrj.LoginActivity;
import com.bysj.yrj.RegisterActivity;
import com.bysj.yrj.bean.UserInfo;
import com.bysj.yrj.dao.ComData;
import com.bysj.yrj.dao.UserDao;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends Activity {
    //��Ա����
	EditText editname,editpass;
	Button butlogin,butreg;
	CheckBox checksave;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //���÷�����ʼ��
        this.init();
        
        //���÷��� ��ȡ������û���Ϣ
        this.readLoginInfo();
        
        //Ϊ�����û�ע�ᡱ ��Ӱ�ť�����¼�
        this.butreg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//�л����û�ע�������л�����	
				Intent abc=new Intent(LoginActivity.this,RegisterActivity.class);
				startActivity(abc);
				
			}
		});
        //�����¼��ť Ϊ��¼��ť�¼���Ӽ���
        this.butlogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//������ص�¼����
				loginAction();
			}
		});
    }
    /**
     * ��ʼ������
     */
    public void init(){
    	this.editname=(EditText) findViewById(R.id.loginditname);
    	this.editpass=(EditText) findViewById(R.id.logeditpass);
    	this.butlogin=(Button) findViewById(R.id.Logbutloging);
    	this.butreg=(Button) findViewById(R.id.logbutredister);
    	this.checksave=(CheckBox) findViewById(R.id.checkBox1);
    }
    
    /**
     * ��¼��ť���� 
     */
    public void loginAction(){
    	//1.��ȡ�û�������û���������
    	String name=this.editname.getText().toString();
    	String pass=this.editpass.getText().toString();
    	//2.�ж������Ƿ�Ϊ��
    	if(name.length()==0||pass.length()==0){
    		Toast.makeText(this, "�������벻���������޸�", Toast.LENGTH_LONG).show();
    		return;
    	}
    	//3.������ط����������û���Ϣ�����ѯ���ݿ�
    	UserDao udao=new UserDao(this);
    	UserInfo utem=udao.checkUser(name, pass);
    	//4.������
    	if(utem==null){
    		//����û���Ϣ�����û���ϢΪ��--��¼ʧ��
    		Toast.makeText(this, "�û���������������޸ģ�", Toast.LENGTH_LONG).show();
    	}else{
    		//����û���Ϣ�������û���Ϣ--��¼�ɹ�--�Ϸ��û�
    		Toast.makeText(this, "�û���¼�ɹ���", Toast.LENGTH_LONG).show();
    		SharedPreferences sharesave=getSharedPreferences("loginfo",Context.MODE_PRIVATE);
			Editor editor=sharesave.edit();
    		if(this.checksave.isChecked()){
    			//�û�ѡ���˱����û���Ϣ ��ʾ�û���������
    			editor.putString("uname", name);
    			editor.putString("upass", pass);
    			//�ύ
    			editor.commit();
    		}else{
    			//�û�δѡ�񱣴� ���---������� �Ա�������
    			editor.clear();
    			//ֻ������ʾ�û���¼�����û�������ʾ����
    			editor.putString("uname", name);
    			editor.commit();
    		}
    		//�Ϸ��û�--��½�ɹ�--��ת���û�ʹ�ý���
    		ComData.utem=utem;
    		Intent intent=new Intent(this,MainActivity.class);
    		startActivity(intent);
    		//���ٵ�½ע�����--����ֱ���˳�
    		this.finish();
    	}
    }
    /**
     * ��ȡ������û��� �� ����
     */
    public void readLoginInfo(){
    	SharedPreferences sharelogin=getSharedPreferences("loginfo",Context.MODE_PRIVATE);
    	//                           �����ݼ������ж�ȡ��   ��δ��ȡ����ʾĬ�ϵĿ�ֵ��  Context.private
    	String uname=sharelogin.getString("uname", "");
    	String upass=sharelogin.getString("upass", "");
    	//���û���������Ŀ��������ʾ��ȡ��������
    	this.editname.setText(uname);
    	this.editpass.setText(upass);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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








